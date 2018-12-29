package com.simple.android.jdmallsimple.controller;

import android.content.Context;

import com.simple.android.jdmallsimple.listener.IModeChangeListener;

public abstract class BaseController {

    protected IModeChangeListener mListener;
    protected Context mContext;

    public void setIModeChangeListener(IModeChangeListener listener) {
        this.mListener = listener;
    }

    public BaseController(Context c) {
        mContext = c;
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
