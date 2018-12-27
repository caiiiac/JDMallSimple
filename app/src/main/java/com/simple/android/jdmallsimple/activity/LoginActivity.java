package com.simple.android.jdmallsimple.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.controller.UserController;
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
        }
    }

    private void handleLoginResult(Message msg) {
        RResult rResult=(RResult) msg.obj;
        if (rResult.isSuccess()) {
            ActivityUtil.start(this, MainActivity.class, true);
        } else {
            tip("登录失败:"+rResult.getErrorMsg());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initController();
        initUI();
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

    @Override
    protected void initController() {
        mController = new UserController();
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mNameEt = findViewById(R.id.name_et);
        mPwdEt = findViewById(R.id.pwd_et);
    }
}
