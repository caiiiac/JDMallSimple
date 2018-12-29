package com.simple.android.jdmallsimple.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.simple.android.jdmallsimple.bean.Banner;
import com.simple.android.jdmallsimple.bean.RRecommndProduct;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.bean.RSecondKill;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.NetworkConst;
import com.simple.android.jdmallsimple.util.NetworkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeController extends BaseController {

    public HomeController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.GET_BANNER_ACTION:
                mListener.onModeChanged(IdiyMessage.GET_BANNER_ACTION_RESULT,
                        loadBanner((Integer) values[0]));
                break;
            case IdiyMessage.SECOND_KILL_ACTION:
                mListener.onModeChanged(IdiyMessage.SECOND_KILL_ACTION_RESULT,
                        loadSecondKill());
                break;
            case IdiyMessage.RECOMMEND_PRODUCT_ACTION:
                mListener.onModeChanged(
                        IdiyMessage.RECOMMEND_PRODUCT_ACTION_RESULT,
                        loadRecommendProduct());
                break;
        }
    }

    private List<RRecommndProduct> loadRecommendProduct() {
        String jsonStr = NetworkUtil.doGet(NetworkConst.GETYOURFAV_URL);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            try {
                JSONObject jsonObj = new JSONObject(resultBean.getResult());
                String rowsJson = jsonObj.getString("rows");
                return JSON.parseArray(rowsJson, RRecommndProduct.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RRecommndProduct>();
    }

    private List<RSecondKill> loadSecondKill() {
        String jsonStr = NetworkUtil.doGet(NetworkConst.SECKILL_URL);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            try {
                JSONObject jsonObj = new JSONObject(resultBean.getResult());
                String rowsJSON = jsonObj.getString("rows");
                return JSON.parseArray(rowsJSON, RSecondKill.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RSecondKill>();
    }

    private List<Banner> loadBanner(int type) {
        ArrayList<Banner> result = new ArrayList<Banner>();
        // 开始一个网络请求
        String urlPath = NetworkConst.BANNER_URL + "?adKind=" + type;
        String jsonStr = NetworkUtil.doGet(urlPath);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), Banner.class);
        }
        return result;
    }

}
