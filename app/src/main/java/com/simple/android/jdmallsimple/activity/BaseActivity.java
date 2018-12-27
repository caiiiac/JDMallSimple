package com.simple.android.jdmallsimple.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import com.simple.android.jdmallsimple.controller.BaseController;
import com.simple.android.jdmallsimple.listener.IModeChangeListener;


public abstract class BaseActivity extends FragmentActivity implements IModeChangeListener {

    protected BaseController mController;
    protected Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            handlerMessage(message);
        }
    };

    protected void handlerMessage(Message message) {
        // default Empty implementn
    }

    protected void initController() {
        // default Empty implementn
    }

    protected abstract void initUI();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void onModeChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    public void tip(String tipStr) {
        Toast.makeText(this, tipStr, 0).show();
    }

    protected boolean ifValueWasEmpty(String... values) {
        for (String value : values) {
            if (TextUtils.isEmpty(value)) {
                return true;
            }
        }
        return false;
    }
}
