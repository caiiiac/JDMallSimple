package com.simple.android.jdmallsimple.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.listener.IBottomBarClickListener;

public class BottomBar extends LinearLayout implements View.OnClickListener {

    private ImageView mHomeIv;
    private TextView mHomeTv;
    private TextView mCategoryTv;
    private ImageView mCategoryIv;
    private ImageView mShopcarIv;
    private TextView mShopcarTv;
    private ImageView mMyJDIv;
    private TextView mMyJDTv;
    private IBottomBarClickListener mListener;
    private int mCurrenTabId;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIBottomBarClickListener(IBottomBarClickListener listener) {
        mListener=listener;
    }

    // onFinishInflate 当所有的控件测量排布后 会调用该方法
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 1.渲染界面 当界面渲染完成
        // 2.实现与用户的交互
        findViewById(R.id.frag_main_ll).setOnClickListener(this);
        findViewById(R.id.frag_category_ll).setOnClickListener(this);
        findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
        findViewById(R.id.frag_mine_ll).setOnClickListener(this);

        mHomeIv = findViewById(R.id.frag_main_iv);
        mHomeTv = findViewById(R.id.frag_main);
        mCategoryIv = findViewById(R.id.frag_category_iv);
        mCategoryTv = findViewById(R.id.frag_category);
        mShopcarIv = findViewById(R.id.frag_shopcar_iv);
        mShopcarTv = findViewById(R.id.frag_shopcar);
        mMyJDIv = findViewById(R.id.frag_mine_iv);
        mMyJDTv = findViewById(R.id.frag_mine);

        setFontType(mHomeTv);
        setFontType(mCategoryTv);
        setFontType(mShopcarTv);
        setFontType(mMyJDTv);

        //模拟用户第一次点击了首页
        findViewById(R.id.frag_main_ll).performClick();
    }

    private void setFontType(TextView tv){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font.ttf");
        tv.setTypeface(tf);
    }

    @Override
    public void onClick(View v) {
        if (mCurrenTabId==v.getId()) {
            return;
        }
        // 1.让所有的控件设置为false
        mHomeIv.setSelected(v.getId() == R.id.frag_main_ll);
        mHomeTv.setSelected(v.getId() == R.id.frag_main_ll);
        mCategoryIv.setSelected(v.getId() == R.id.frag_category_ll);
        mCategoryTv.setSelected(v.getId() == R.id.frag_category_ll);
        mShopcarIv.setSelected(v.getId() == R.id.frag_shopcar_ll);
        mShopcarTv.setSelected(v.getId() == R.id.frag_shopcar_ll);
        mMyJDIv.setSelected(v.getId() == R.id.frag_mine_ll);
        mMyJDTv.setSelected(v.getId() == R.id.frag_mine_ll);
        if (mListener!=null) {
            mListener.onItemClick(v.getId());
            mCurrenTabId=v.getId();
        }
    }
}
