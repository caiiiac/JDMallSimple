package com.simple.android.jdmallsimple;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.simple.android.jdmallsimple.bean.RLoginResult;

public class JDApplication extends Application {

    public RLoginResult mRLoginResult;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }

    public void setRLoginResult(RLoginResult bean) {
        mRLoginResult = bean;
    }
}
