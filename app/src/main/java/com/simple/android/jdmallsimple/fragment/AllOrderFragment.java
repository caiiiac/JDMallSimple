package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.adapter.AllOrderAdapter;
import com.simple.android.jdmallsimple.bean.ROrderListBean;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.OrderStatusCost;
import com.simple.android.jdmallsimple.listener.IConfirmReceiverOrderListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends OrderBaseFragment implements IConfirmReceiverOrderListener {


    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.ALL_ORDER_ACTION_RESULT:
                handleLoadLv((List<ROrderListBean>) msg.obj);
                break;
            case IdiyMessage.CONFIRM_ORDER_ACTION_RESULT:
                handleConfirmOrder((RResult) msg.obj);
                break;
        }
    }

    private void handleConfirmOrder(RResult resultBean){
        if (resultBean.isSuccess()) {
            mController.sendAsyncMessage(IdiyMessage.ALL_ORDER_ACTION,
                    OrderStatusCost.ALL_ORDER);
        }else {
            tip("数据异常:"+resultBean.getErrorMsg());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_order, container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
        mController.sendAsyncMessage(IdiyMessage.ALL_ORDER_ACTION,
                OrderStatusCost.ALL_ORDER);
    }

    @Override
    protected void initUI() {
        initXListView(R.id.all_order_lv, AllOrderAdapter.class);
        ((AllOrderAdapter)mAdapter).setIConfirmReceiverOrderListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onOrderReceived(long oid) {
        mController.sendAsyncMessage(IdiyMessage.CONFIRM_ORDER_ACTION,oid);
    }
}
