package com.simple.android.jdmallsimple.controller;

import com.alibaba.fastjson.JSON;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.NetworkConst;
import com.simple.android.jdmallsimple.util.NetworkUtil;

import java.util.HashMap;

public class UserController extends BaseController {

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
                // 登录的请求
                RResult rResult = loginOrRegist(NetworkConst.LOGIN_URL,
                        (String) values[0], (String) values[1]);
                // 通知Activity,数admin据加载完毕了
                mListener.onModeChanged(IdiyMessage.LOGIN_ACTION_RESULT, rResult);
                break;
            case IdiyMessage.REGIST_ACTION:
                RResult loginOrRegist = loginOrRegist(NetworkConst.REGIST_URL, (String) values[0],
                        (String) values[1]);
                mListener.onModeChanged(IdiyMessage.REGIST_ACTION_RESULT, loginOrRegist);
                break;
        }
    }

    private RResult loginOrRegist(String url, String name, String pwd) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", name);
        params.put("pwd", pwd);
        String jsonStr = NetworkUtil.doPost(url, params);
        return JSON.parseObject(jsonStr, RResult.class);
    }
}
