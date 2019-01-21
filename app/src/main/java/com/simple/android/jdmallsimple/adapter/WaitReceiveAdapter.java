package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.ROrderListBean;
import com.simple.android.jdmallsimple.listener.IConfirmReceiverOrderListener;

public class WaitReceiveAdapter extends OrderBaseAdapter{
    private IConfirmReceiverOrderListener mListener;

    public WaitReceiveAdapter(Context c) {
        super(c);
    }

    public void setIConfirmReceiverOrderListener(IConfirmReceiverOrderListener listener ) {
        mListener=listener;
    }

    class ViewHolder {
        TextView orderNOTv;
        TextView orderStatusTv;
        LinearLayout pContainerLl;
        TextView priceTv;
        Button doBtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holer = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.order_list_item, parent,
                    false);
            holer = new ViewHolder();
            holer.orderNOTv = (TextView) convertView
                    .findViewById(R.id.order_no_tv);
            holer.orderStatusTv = (TextView) convertView
                    .findViewById(R.id.order_state_tv);
            holer.pContainerLl = (LinearLayout) convertView
                    .findViewById(R.id.p_container_ll);
            holer.priceTv = (TextView) convertView.findViewById(R.id.price_tv);
            holer.doBtn = (Button) convertView.findViewById(R.id.do_btn);
            holer.doBtn.setText("确认收货");
            holer.doBtn.setOnClickListener(this);
            convertView.setTag(holer);
        } else {
            holer = (ViewHolder) convertView.getTag();
        }
        ROrderListBean bean = mDatas.get(position);
        holer.orderNOTv.setText("订单编号:" + bean.getOrderNum());
        showOrderStatus(holer.orderStatusTv, bean.getStatus());
        holer.priceTv.setText("¥ " + bean.getTotalPrice());
        initProductContainer(holer.pContainerLl, bean.getItems());
        holer.doBtn.setTag(bean.getOid());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        // 确认收货 mController发送一个网络请求
//        if (mListener!=null) {
//            mListener.onOrderReceived((Long) v.getTag());
//        }
    }
}
