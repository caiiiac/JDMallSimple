package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.adapter.CompleteOrderAdapter;
import com.simple.android.jdmallsimple.bean.ROrderListBean;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.OrderStatusCost;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedOrderFragment extends OrderBaseFragment {


    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.COMPLETE_ORDER_ACTION_RESULT:
                handleLoadLv((List<ROrderListBean>) msg.obj);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_completed_order, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
        mController.sendAsyncMessage(IdiyMessage.COMPLETE_ORDER_ACTION,
                OrderStatusCost.COMPLETE_ORDER);
    }

    @Override
    protected void initUI() {
        initXListView(R.id.complete_order_lv, CompleteOrderAdapter.class);
    }

    @Override
    public void onRefresh() {

    }
}
