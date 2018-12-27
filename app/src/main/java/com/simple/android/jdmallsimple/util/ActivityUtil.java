package com.simple.android.jdmallsimple.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.simple.android.jdmallsimple.activity.BaseActivity;

public class ActivityUtil {

    public static void start(Context context, Class<? extends BaseActivity> activity, boolean ifFinishSelf) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
        if (ifFinishSelf) {
            ((Activity)context).finish();
        }
    }
}
