package com.simple.android.jdmallsimple.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.fragment.AllOrderFragment;
import com.simple.android.jdmallsimple.fragment.BaseFragment;
import com.simple.android.jdmallsimple.fragment.CompletedOrderFragment;
import com.simple.android.jdmallsimple.fragment.WaitPayFragment;
import com.simple.android.jdmallsimple.fragment.WaitReceiveFragment;

import java.util.ArrayList;

public class OrderListActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private View mAllOrderView;
    private View mWaitPayView;
    private View mWaitReceiveView;
    private View mCompleteOrderView;
    private ViewPager mOrderVp;
    private OrderAdapter mOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        initUI();
    }

    @Override
    protected void initUI() {
        findViewById(R.id.all_order_ll).setOnClickListener(this);
        findViewById(R.id.wait_pay_ll).setOnClickListener(this);
        findViewById(R.id.wait_receive_ll).setOnClickListener(this);
        findViewById(R.id.complete_order_ll).setOnClickListener(this);
        mAllOrderView = findViewById(R.id.all_order_view);
        mWaitPayView=findViewById(R.id.wait_pay_view);
        mWaitReceiveView = findViewById(R.id.wait_receive_view);
        mCompleteOrderView = findViewById(R.id.complete_order_view);
        defaultIndicators();
        mOrderVp =(ViewPager) findViewById(R.id.order_vp);
        mOrderAdapter = new OrderAdapter(getSupportFragmentManager());
        mOrderVp.setAdapter(mOrderAdapter);
        mOrderVp.setOnPageChangeListener(this);
        findViewById(R.id.all_order_ll).performClick();
    }

    private class OrderAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mItems;

        public OrderAdapter(FragmentManager fm) {
            super(fm);

            mItems=new ArrayList<BaseFragment>();
            mItems.add(new AllOrderFragment());
            mItems.add(new WaitPayFragment());
            mItems.add(new WaitReceiveFragment());
            mItems.add(new CompletedOrderFragment());
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return mItems.get(arg0);
        }

    }

    @Override
    public void onClick(View v) {
        defaultIndicators();
        switch (v.getId()) {
            case R.id.all_order_ll:
                mAllOrderView.setVisibility(View.VISIBLE);
                mOrderVp.setCurrentItem(0, true);
                break;
            case R.id.wait_pay_ll:
                mWaitPayView.setVisibility(View.VISIBLE);
                mOrderVp.setCurrentItem(1, true);
                break;
            case R.id.wait_receive_ll:
                mWaitReceiveView.setVisibility(View.VISIBLE);
                mOrderVp.setCurrentItem(2, true);
                break;
            case R.id.complete_order_ll:
                mCompleteOrderView.setVisibility(View.VISIBLE);
                mOrderVp.setCurrentItem(3, true);
                break;
        }
    }

    private void defaultIndicators() {
        mAllOrderView.setVisibility(View.INVISIBLE);
        mWaitPayView.setVisibility(View.INVISIBLE);
        mWaitReceiveView.setVisibility(View.INVISIBLE);
        mCompleteOrderView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        defaultIndicators();
        switch (position) {
            case 0:
                mAllOrderView.setVisibility(View.VISIBLE);
                break;
            case 1:
                mWaitPayView.setVisibility(View.VISIBLE);
                break;
            case 2:
                mWaitReceiveView.setVisibility(View.VISIBLE);
                break;
            case 3:
                mCompleteOrderView.setVisibility(View.VISIBLE);
                break;
        }
    }
}
