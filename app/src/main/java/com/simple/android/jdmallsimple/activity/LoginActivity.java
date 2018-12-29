package com.simple.android.jdmallsimple.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.simple.android.jdmallsimple.JDApplication;
import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RLoginResult;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.controller.UserController;
import com.simple.android.jdmallsimple.db.UserDao;
import com.simple.android.jdmallsimple.util.ActivityUtil;

public class LoginActivity extends BaseActivity {

    private EditText mNameEt;
    private EditText mPwdEt;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.LOGIN_ACTION_RESULT:
                handleLoginResult(msg);
                break;
            case IdiyMessage.SAVE_USERTODB_RESULT:
                handleSaveUser2Db((Boolean) msg.obj);
                break;
            case IdiyMessage.GET_USER_ACTION_RESULT:
                handlerGetUser(msg.obj);
                break;
        }
    }

    private void handleLoginResult(Message msg) {
        RResult rResult = (RResult) msg.obj;
        if (rResult.isSuccess()) {
            // 1.将用户的信息保存到Application里面
            RLoginResult bean = JSON.parseObject(rResult.getResult(),
                    RLoginResult.class);
            ((JDApplication) getApplication()).setRLoginResult(bean);
            // 2.将账号密码保存到数据库 传递一个账号密码
            String name = mNameEt.getText().toString();
            String pwd = mPwdEt.getText().toString();
            mController.sendAsyncMessage(IdiyMessage.SAVE_USERTODB, name, pwd);
        } else {
            tip("登录失败:" + rResult.getErrorMsg());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initController();
        initUI();
    }

    @Override
    protected void initController() {
        mController = new UserController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mNameEt = findViewById(R.id.name_et);
        mPwdEt = findViewById(R.id.pwd_et);
    }

    public void loginClick(View v) {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        if (ifValueWasEmpty(name, pwd)) {
            tip("请输入账号密码");
            return;
        }
        // 发送一个网络请求 去请求网络数据
        mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, name, pwd);
    }

    public void registClick(View v){
        ActivityUtil.start(this, RegistActivity.class, false);
    }

    private void handlerGetUser(Object c) {
        if (c != null) {
            UserDao.UserInfo userInfo = (UserDao.UserInfo) c;
            mNameEt.setText(userInfo.name);
            mPwdEt.setText(userInfo.pwd);
        }
    }

    private void handleSaveUser2Db(boolean ifSuccess) {
        if (ifSuccess) {
            ActivityUtil.start(this, MainActivity.class, true);
        } else {
            tip("登录异常");
        }
    }
}
