package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.adapter.WaitPayAdapter;
import com.simple.android.jdmallsimple.bean.ROrderListBean;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.cons.OrderStatusCost;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitPayFragment extends OrderBaseFragment {


    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.WAIT_PAY_ACTION_RESULT:
                handleLoadLv((List<ROrderListBean>) msg.obj);
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wait_pay, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
        mController.sendAsyncMessage(IdiyMessage.WAIT_PAY_ACTION,
                OrderStatusCost.WAIT_PAY_ORDER);
    }

    @Override
    protected void initUI() {
        initXListView(R.id.wait_pay_lv,WaitPayAdapter.class);
    }

    //²»Í¬
    @Override
    public void onRefresh() {
        mController.sendAsyncMessage(IdiyMessage.WAIT_PAY_ACTION,
                OrderStatusCost.WAIT_PAY_ORDER);
    }
}
