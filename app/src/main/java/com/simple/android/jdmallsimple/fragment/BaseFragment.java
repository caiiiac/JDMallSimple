package com.simple.android.jdmallsimple.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.simple.android.jdmallsimple.controller.BaseController;
import com.simple.android.jdmallsimple.listener.IModeChangeListener;

public abstract class BaseFragment extends Fragment implements IModeChangeListener {

    protected BaseController mController;
    protected Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            handlerMessage(msg);
        }

    };

    protected abstract void initUI();

    protected void handlerMessage(Message msg) {
        // default Empty implementn
    }

    protected void initController() {
        // default Empty implementn
    }

    @Override
    public void onModeChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}
