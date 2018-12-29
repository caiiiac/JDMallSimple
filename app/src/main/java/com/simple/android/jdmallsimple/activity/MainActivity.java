package com.simple.android.jdmallsimple.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.fragment.CategoryFragment;
import com.simple.android.jdmallsimple.fragment.HomeFragment;
import com.simple.android.jdmallsimple.fragment.MyJDFragment;
import com.simple.android.jdmallsimple.fragment.ShopcarFragment;
import com.simple.android.jdmallsimple.listener.IBottomBarClickListener;
import com.simple.android.jdmallsimple.ui.BottomBar;

public class MainActivity extends BaseActivity implements IBottomBarClickListener {

    private BottomBar mBottomBar;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        initUI();
    }

    @Override
    protected void initUI() {
        // 初始化底部栏
        mBottomBar = findViewById(R.id.bottom_bar);
        mBottomBar.setIBottomBarClickListener(this);
        // 初始化Fragment
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.top_bar, new HomeFragment());
        transaction.commit();
    }

    // 切换Fragment
    @Override
    public void onItemClick(int action) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (action) {
            case R.id.frag_main_ll:
                transaction.replace(R.id.top_bar, new HomeFragment());
                break;
            case R.id.frag_category_ll:
                transaction.replace(R.id.top_bar, new CategoryFragment());
                break;
            case R.id.frag_shopcar_ll:
                transaction.replace(R.id.top_bar, new ShopcarFragment());
                break;
            case R.id.frag_mine_ll:
                transaction.replace(R.id.top_bar, new MyJDFragment());
                break;
        }
        transaction.commit();
    }
}
