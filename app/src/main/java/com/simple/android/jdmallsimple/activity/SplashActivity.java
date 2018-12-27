package com.simple.android.jdmallsimple.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.util.ActivityUtil;

public class SplashActivity extends BaseActivity {

    private ImageView logoIV;

    @Override
    protected void initUI() {
        logoIV = findViewById(R.id.logo_iv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initUI();
        alphaAnim();
    }

    private void alphaAnim(){
        AlphaAnimation anim=new AlphaAnimation(0.2f, 1.0f);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                ActivityUtil.start(SplashActivity.this, LoginActivity.class,true);
            }
        });
        anim.setDuration(3000);
        anim.setFillAfter(true);
        logoIV.startAnimation(anim);
    }

}
