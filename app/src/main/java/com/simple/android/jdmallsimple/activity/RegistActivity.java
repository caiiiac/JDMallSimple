package com.simple.android.jdmallsimple.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.controller.UserController;

public class RegistActivity extends BaseActivity {

    private EditText mNameEt;
    private EditText mPwdEt;
    private EditText mSurePwdEt;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.REGIST_ACTION_RESULT:
                handleRegistResult((RResult) msg.obj);
                break;
        }
    }

    private void handleRegistResult(RResult resultBean) {
        tip(resultBean.isSuccess()?"注册成功!":resultBean.getErrorMsg());
        if (resultBean.isSuccess()) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initController();
        initUI();
    }

    @Override
    protected void initController() {
        mController = new UserController();
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mNameEt = findViewById(R.id.username_et);
        mPwdEt = findViewById(R.id.pwd_et);
        mSurePwdEt = findViewById(R.id.surepwd_et);
    }

    public void registClick(View v) {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        String surePwd = mSurePwdEt.getText().toString();
        if (ifValueWasEmpty(name, pwd, surePwd)) {
            tip("请输入完整的信息!");
            return;
        }
        if (!pwd.equals(surePwd)) {
            tip("两次密码不一致!");
            return;
        }
        // 注册用户
        mController.sendAsyncMessage(IdiyMessage.REGIST_ACTION, name, pwd);
    }
}
