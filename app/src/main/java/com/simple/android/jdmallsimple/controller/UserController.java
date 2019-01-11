package com.simple.android.jdmallsimple.controller;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.simple.android.jdmallsimple.JDApplication;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.NetworkConst;
import com.simple.android.jdmallsimple.db.UserDao;
import com.simple.android.jdmallsimple.util.AESUtils;
import com.simple.android.jdmallsimple.util.NetworkUtil;

import java.util.HashMap;

public class UserController extends BaseController {

    private UserDao mUserDao;
    protected long mUserId;

    public UserController(Context c) {
        super(c);
        mUserDao=new UserDao(mContext);
        initUserId(c);
    }

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
            case IdiyMessage.SAVE_USERTODB:
                boolean saveUser2Db = saveUser2Db((String)values[0], (String)values[1]);
                mListener.onModeChanged(IdiyMessage.SAVE_USERTODB_RESULT, saveUser2Db);
                break;
            case IdiyMessage.GET_USER_ACTION:
                UserDao.UserInfo userInfo = aquireUser();
                mListener.onModeChanged(IdiyMessage.GET_USER_ACTION_RESULT, userInfo);
                break;
            case IdiyMessage.CLEAR_USER_ACTION:
                clearUser();
                mListener.onModeChanged(IdiyMessage.CLEAR_USER_ACTION_RESULT, 0);
                break;
        }
    }

    private void initUserId(Context c) {
        Activity activity=(Activity) c;
        JDApplication jdApplication=(JDApplication) activity.getApplication();
        if (jdApplication.mRLoginResult!=null) {
            mUserId=jdApplication.mRLoginResult.getId();
        }
    }

    private RResult loginOrRegist(String url, String name, String pwd) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", name);
        params.put("pwd", pwd);
        String jsonStr = NetworkUtil.doPost(url, params);
        return JSON.parseObject(jsonStr, RResult.class);
    }


    private void clearUser(){
        mUserDao.clearUsers();
    }

    private UserDao.UserInfo aquireUser(){
        UserDao.UserInfo userInfo = mUserDao.aquireLastestUser();
        if (userInfo!=null) {
//            try {
//                userInfo.name = AESUtils.decrypt(userInfo.name);
//                userInfo.pwd = AESUtils.decrypt(userInfo.pwd);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            return userInfo;
        }
        return null;
    }

    private boolean saveUser2Db(String name,String pwd){
        mUserDao.clearUsers();
        // 可逆性加密
//        try {
//            name=AESUtils.encrypt(name);
//            pwd=AESUtils.encrypt(pwd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return mUserDao.saveUser(name, pwd);
    }
}
