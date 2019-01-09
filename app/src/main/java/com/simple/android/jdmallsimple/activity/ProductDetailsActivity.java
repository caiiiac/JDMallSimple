package com.simple.android.jdmallsimple.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.fragment.ProductCommentFragment;
import com.simple.android.jdmallsimple.fragment.ProductDetailsFragment;
import com.simple.android.jdmallsimple.fragment.ProductIntroduceFragment;

import java.util.ArrayList;

public class ProductDetailsActivity extends BaseActivity
        implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public long mProductId;
    private View mDetailsIndicator;
    private View mIntroduceIndicator;
    private View mCommentIndicator;
    private ViewPager mContainerVp;
    private ContainerAdapter mContainerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initData();
        initUI();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mProductId = intent.getLongExtra(ProductListActivity.TODETAILSKEY, 0);
        if (mProductId==0) {
            tip("数据异常");
            finish();
        }
    }

    @Override
    protected void initUI() {
        findViewById(R.id.introduce_ll).setOnClickListener(this);
        findViewById(R.id.details_ll).setOnClickListener(this);
        findViewById(R.id.comment_ll).setOnClickListener(this);

        mDetailsIndicator = findViewById(R.id.details_view);
        mIntroduceIndicator = findViewById(R.id.introduce_view);
        mCommentIndicator = findViewById(R.id.comment_view);

        mContainerVp =(ViewPager) findViewById(R.id.container_vp);
        mContainerAdapter = new ContainerAdapter(getSupportFragmentManager());
        mContainerVp.setAdapter(mContainerAdapter);
        mContainerVp.setOnPageChangeListener(this);
    }

    public class ContainerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments=new ArrayList<Fragment>();

        public ContainerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.add(new ProductIntroduceFragment());
            mFragments.add(new ProductDetailsFragment());
            mFragments.add(new ProductCommentFragment());
        }


        @Override
        public Fragment getItem(int arg0) {
            return mFragments.get(arg0);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }

    @Override
    public void onClick(View v) {
        defaultIndicator();
        switch (v.getId()) {
            case R.id.introduce_ll://商品简介
                mIntroduceIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(0);
                break;
            case R.id.details_ll://商品详情
                mDetailsIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(1);
                break;
            case R.id.comment_ll://商品评论
                mCommentIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(2);
                break;
        }
    }

    private void defaultIndicator() {
        mDetailsIndicator.setVisibility(View.INVISIBLE);
        mIntroduceIndicator.setVisibility(View.INVISIBLE);
        mCommentIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        defaultIndicator();
        switch (position) {
            case 0:
                mIntroduceIndicator.setVisibility(View.VISIBLE);
                break;
            case 1:
                mDetailsIndicator.setVisibility(View.VISIBLE);
                break;
            case 2:
                mCommentIndicator.setVisibility(View.VISIBLE);
                break;
        }
    }


}
