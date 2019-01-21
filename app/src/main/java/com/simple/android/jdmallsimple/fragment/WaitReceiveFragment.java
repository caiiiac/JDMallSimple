package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.adapter.WaitReceiveAdapter;
import com.simple.android.jdmallsimple.bean.ROrderListBean;
import com.simple.android.jdmallsimple.bean.RResult;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.OrderStatusCost;
import com.simple.android.jdmallsimple.listener.IConfirmReceiverOrderListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitReceiveFragment extends OrderBaseFragment implements IConfirmReceiverOrderListener {

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.WAIT_RECEIVE_ACTION_RESULT:
                handleLoadLv((List<ROrderListBean>) msg.obj);
                break;
            case IdiyMessage.CONFIRM_ORDER_ACTION_RESULT:
                handleConfirmOrder((RResult) msg.obj);
                break;
        }
    }

    private void handleConfirmOrder(RResult resultBean){
        if (resultBean.isSuccess()) {
            mController.sendAsyncMessage(IdiyMessage.WAIT_RECEIVE_ACTION,
                    OrderStatusCost.WAIT_RECEIVE_ORDER);
        }else {
            tip("数据错误:"+resultBean.getErrorMsg());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wait_receive, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
        mController.sendAsyncMessage(IdiyMessage.WAIT_RECEIVE_ACTION,
                OrderStatusCost.WAIT_RECEIVE_ORDER);
    }

    @Override
    protected void initUI() {
        initXListView(R.id.wait_receive_lv, WaitReceiveAdapter.class);
        ((WaitReceiveAdapter)mAdapter).setIConfirmReceiverOrderListener(this);
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onOrderReceived(long oid) {
        mController.sendAsyncMessage(IdiyMessage.CONFIRM_ORDER_ACTION,oid);
    }

}
