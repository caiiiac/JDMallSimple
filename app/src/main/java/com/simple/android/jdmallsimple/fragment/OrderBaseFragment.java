package com.simple.android.jdmallsimple.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.simple.android.jdmallsimple.adapter.OrderBaseAdapter;
import com.simple.android.jdmallsimple.bean.ROrderListBean;
import com.simple.android.jdmallsimple.controller.OrderController;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.maxwin.view.XListView;

public abstract class OrderBaseFragment extends BaseFragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {

    protected XListView mListView;
    protected OrderBaseAdapter mAdapter;

    protected void handleLoadLv(List<ROrderListBean> datas) {
        mAdapter.setDatas(datas);
        mAdapter.notifyDataSetChanged();
        // 告诉列表刷新的时间
        mListView.setRefreshTime(getCurrenTime());
        // 告诉列表停止刷新
        mListView.stopRefresh();
    }

    protected String getCurrenTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd hh:mm",Locale.CHINESE);
        return formatter.format(new Date());
    }

    @Override
    protected void initController() {
        mController = new OrderController(getActivity());
        mController.setIModeChangeListener(this);
    }

    protected void initXListView(int resId,
                                 Class<? extends OrderBaseAdapter> clazz) {
        mListView = (XListView) getActivity().findViewById(resId);
        mListView.setOnItemClickListener(this);
        // 启动下拉刷新
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(false);
        // 设置下拉刷新监听器
        mListView.setXListViewListener(this);
        // 1 通过子类来回调IOC 通过反射
        // clazz.newInstance();//通过空参构造器创建一个对象
        try {
            Constructor<? extends OrderBaseAdapter> constructor = clazz
                    .getDeclaredConstructor(Context.class);
            mAdapter = (OrderBaseAdapter) constructor
                    .newInstance(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // mAdapter = new WaitPayAdapter(getActivity());
        mListView.setAdapter(mAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        //1.获取OID
//        long oid = mAdapter.getItemId(position-1);
//        //2.启动订单详情的Activity
//        Intent intent=new Intent(getActivity(),OrderDetailsActivity.class);
//        intent.putExtra("OID", oid);
//        startActivity(intent);
    }

    @Override
    public void onLoadMore() {
        //default Empty implements
    }
}
