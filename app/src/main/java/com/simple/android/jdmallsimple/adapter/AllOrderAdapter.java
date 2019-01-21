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

public class AllOrderAdapter extends OrderBaseAdapter {

    private IConfirmReceiverOrderListener mListener;


    public AllOrderAdapter(Context c) {
        super(c);
    }


    public void setIConfirmReceiverOrderListener(
            IConfirmReceiverOrderListener listener) {
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
            holer.doBtn.setOnClickListener(this);
            convertView.setTag(holer);
        } else {
            holer = (ViewHolder) convertView.getTag();
        }
        ROrderListBean bean = mDatas.get(position);
        holer.orderNOTv.setText("订单编号:" + bean.getOrderNum());
        showOrderStatus(holer.orderStatusTv,holer.doBtn,bean.getStatus());
        holer.priceTv.setText("¥ " + bean.getTotalPrice());
        initProductContainer(holer.pContainerLl, bean.getItems());
        holer.doBtn.setTag(bean);
        return convertView;
    }

    protected void showOrderStatus(TextView tv, Button btn, int status) {
        switch (status) {
            case -1:
                btn.setVisibility(View.INVISIBLE);
                tv.setText("取消订单");
                break;
            case 0:
                btn.setVisibility(View.VISIBLE);
                btn.setText("去支付");
                tv.setText("待支付");
                break;
            case 1:
                btn.setVisibility(View.INVISIBLE);
                tv.setText("待发货");
                break;
            case 2:
                btn.setVisibility(View.VISIBLE);
                btn.setText("确认收货");
                tv.setText("待收货 ");
                break;
            case 3:
                btn.setVisibility(View.INVISIBLE);
                tv.setText("完成交易 ");
                break;
        }
    }


    @Override
    public void onClick(View v) {
        //不同的订单状态有不同的实现
        ROrderListBean bean = (ROrderListBean) v.getTag();
        //1.拿到一个oid
        //2.拿到订单的状态 区别处理
        switch (bean.getStatus()) {
            case 0:

            case 2:
//				btn.setText("确认收货");
                break;
        }
    }

}
