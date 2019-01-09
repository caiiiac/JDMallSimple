package com.simple.android.jdmallsimple.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.simple.android.jdmallsimple.bean.RCommentCount;
import com.simple.android.jdmallsimple.bean.RGoodComment;
import com.simple.android.jdmallsimple.bean.RProductComment;
import com.simple.android.jdmallsimple.bean.RProductInfo;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.NetworkConst;
import com.simple.android.jdmallsimple.fragment.ProductCommentFragment;
import com.simple.android.jdmallsimple.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDetailsController extends BaseController {

    public ProductDetailsController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.PRODUCT_INFO_ACTION:
                mListener.onModeChanged(IdiyMessage.PRODUCT_INFO_ACTION_RESULT,
                        loadProductInfo((Long) values[0]));
                break;
            case IdiyMessage.GOOD_COMMENT_ACTION:
                mListener.onModeChanged(IdiyMessage.GOOD_COMMENT_ACTION_RESULT,
                        loadGoodComment((Long) values[0]));
                break;
            case IdiyMessage.GET_COMMENT_COUNT_ACTION:
                mListener.onModeChanged(
                        IdiyMessage.GET_COMMENT_COUNT_ACTION_RESULT,
                        loadCommentCount((Long) values[0]));
                break;
            case IdiyMessage.GET_COMMENT_ACTION:
                List<RProductComment> dats = loadComment((Long) values[0], (Integer) values[1]);
                mListener.onModeChanged(IdiyMessage.GET_COMMENT_ACTION_RESULT, dats);
                break;
        }
    }

    private List<RProductComment> loadComment(long pid, int type) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        if (type == 4) {// 希望有图=全部评论+hasImgCom
            params.put("type", ProductCommentFragment.ALL_COMMENT + "");
            // hasImgCom: required (boolean) 如果需要有图的评论才添加 如果没有则不用添加 比如选中好评
            params.put("hasImgCom","true");
        } else {
            params.put("type", type + "");
        }
        String jsonStr = NetworkUtil.doPost(NetworkConst.COMMENTDETAIL_URL, params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(),RProductComment.class);
        }
        return new ArrayList<RProductComment>();
    }

    private RCommentCount loadCommentCount(long pid) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        String jsonStr = NetworkUtil.doPost(NetworkConst.COMMENTCOUNT_URL,
                params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON
                    .parseObject(resultBean.getResult(), RCommentCount.class);
        }
        return null;
    }

    private List<RGoodComment> loadGoodComment(long pid) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        params.put("type", "1");
        String jsonStr = NetworkUtil.doPost(NetworkConst.PRODUCTCOMMENT_URL,
                params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), RGoodComment.class);
        }
        return new ArrayList<RGoodComment>();
    }

    private RProductInfo loadProductInfo(long pid) {
        String jsonStr = NetworkUtil.doGet(NetworkConst.PRODUCTINFO_URL
                + "?id=" + pid);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), RProductInfo.class);
        }
        return null;
    }
}
