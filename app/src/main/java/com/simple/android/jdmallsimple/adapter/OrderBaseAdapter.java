package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.simple.android.jdmallsimple.bean.ROrderListBean;

import java.util.List;

public abstract class OrderBaseAdapter extends JDBaseAdapter<ROrderListBean> implements View.OnClickListener {

    public OrderBaseAdapter(Context c) {
        super(c);
    }

    protected void initProductContainer(LinearLayout containerLl, String jsonStr) {
        // item:["zdfdsf","sdfdzfz"]; ====>jsonStr
        List<String> datas = JSON.parseArray(jsonStr, String.class);
        // 1.计算数据的长度
        int dataSize = datas.size();
        // 2.计算的容器子控件的大小
        int childCount = containerLl.getChildCount();
        // 3.取最小
        int realSize = Math.min(dataSize, childCount);
        // 4.清空之前数据
        for (int i = 0; i < childCount; i++) {
            ImageView iv = (ImageView) containerLl.getChildAt(i);
            iv.setImageDrawable(new BitmapDrawable());
            iv.setVisibility(View.INVISIBLE);
        }
        // 5.做一个for循环填充数据
        for (int i = 0; i < realSize; i++) {
            SimpleDraweeView iv = (SimpleDraweeView) containerLl.getChildAt(i);
            iv.setImageURI(datas.get(i).replace("http", "https"));
            iv.setVisibility(View.VISIBLE);
        }
    }

    protected void showOrderStatus(TextView tv, int status) {
        switch (status) {
            case -1:
                tv.setText("取消订单");
                break;
            case 0:
                tv.setText("待支付");
                break;
            case 1:
                tv.setText("待发货");
                break;
            case 2:
                tv.setText("待收货 ");
                break;
            case 3:
                tv.setText("完成交易 ");
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return mDatas!=null?mDatas.get(position).getOid():0;
    }
}
