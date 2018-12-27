package com.simple.android.jdmallsimple.controller;

import com.simple.android.jdmallsimple.listener.IModeChangeListener;

public abstract class BaseController {

    protected IModeChangeListener mListener;

    public void setIModeChangeListener(IModeChangeListener listener) {
        this.mListener = listener;
    }

    public void sendAsyncMessage(final int action, final Object... values) {
        new Thread() {
            public void run() {
                handleMessage(action, values);
            }
        }.start();
    }

    protected abstract void handleMessage(int action, Object... values);
}
