package com.simple.android.jdmallsimple.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.simple.android.jdmallsimple.bean.ROrderDetails;
import com.simple.android.jdmallsimple.bean.ROrderListBean;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.NetworkConst;
import com.simple.android.jdmallsimple.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderController extends UserController {

    public OrderController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.WAIT_PAY_ACTION:
                mListener.onModeChanged(IdiyMessage.WAIT_PAY_ACTION_RESULT,
                        getOrderByStatus((Integer) values[0]));
                break;
            case IdiyMessage.WAIT_RECEIVE_ACTION:
                mListener.onModeChanged(IdiyMessage.WAIT_RECEIVE_ACTION_RESULT,
                        getOrderByStatus((Integer) values[0]));
                break;
            case IdiyMessage.COMPLETE_ORDER_ACTION:
                mListener.onModeChanged(IdiyMessage.COMPLETE_ORDER_ACTION_RESULT,
                        getOrderByStatus((Integer) values[0]));
                break;
            case IdiyMessage.CONFIRM_ORDER_ACTION:
                mListener.onModeChanged(IdiyMessage.CONFIRM_ORDER_ACTION_RESULT,
                        confirmOrder((Long) values[0]));
                break;
            case IdiyMessage.GET_ORDER_DETIALS_ACTION:
                mListener.onModeChanged(IdiyMessage.GET_ORDER_DETIALS_ACTION_RESULT,
                        loadOrderDetails((Long) values[0]));
                break;
            case IdiyMessage.ALL_ORDER_ACTION:
                mListener.onModeChanged(IdiyMessage.ALL_ORDER_ACTION_RESULT,
                        getOrderByStatus((Integer) values[0]));
                break;
        }
    }

    /**
     * 订单详情接口
     */
    private ROrderDetails loadOrderDetails(long oid){
        HashMap<String, String> params=new HashMap<String, String>();
        params.put("userId", mUserId+"");
        params.put("id", oid+"");
        String jsonStr = NetworkUtil.doPost(NetworkConst.GETORDERDETAIL_URL, params);
        RResult resultBean = JSON.parseObject(jsonStr,RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(),ROrderDetails.class);
        }
        return null;
    }

    private RResult confirmOrder(long oid){
        HashMap<String, String> params=new HashMap<String, String>();
        params.put("userId", mUserId+"");
        params.put("oid", oid+"");
        String jsonStr = NetworkUtil.doPost(NetworkConst.CONFIRMORDER_URL, params);
        return JSON.parseObject(jsonStr,RResult.class);
    }

    /**
     * 根据订单状态获取订单列表
     */
    private List<ROrderListBean> getOrderByStatus(int status){
        HashMap<String, String> params=new HashMap<String, String>();
        if (status!=-2) {
            params.put("status", status+"");
        }
        params.put("userId", mUserId+"");
        String jsonStr = NetworkUtil.doPost(NetworkConst.GETORDERBYSTATUS_URL, params);
        RResult resultBean = JSON.parseObject(jsonStr,RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(),ROrderListBean.class);
        }
        return new ArrayList<ROrderListBean>();
    }
}
