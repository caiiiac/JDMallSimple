package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RReceiver;

public class ReceiverListAdapter extends JDBaseAdapter<RReceiver> {

    public ReceiverListAdapter(Context c) {
        super(c);
    }

    class ViewHolder{
        ImageView defaultIv;
        TextView nameTv;
        TextView phoneTv;
        TextView addressDetailsTv;
        TextView deleteBtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holer=null;
        if (convertView==null) {
            convertView=mInflater.inflate(R.layout.choose_address_item_view, parent,false);
            holer=new ViewHolder();
            holer.defaultIv=(ImageView) convertView.findViewById(R.id.isDeafult_iv);
            holer.nameTv=(TextView) convertView.findViewById(R.id.name_tv);
            holer.phoneTv=(TextView) convertView.findViewById(R.id.phone_tv);
            holer.addressDetailsTv=(TextView) convertView.findViewById(R.id.address_tv);
            holer.deleteBtn=(TextView) convertView.findViewById(R.id.delete_tv);
            convertView.setTag(holer);
        }else {
            holer=(ViewHolder) convertView.getTag();
        }
        RReceiver bean = mDatas.get(position);
        holer.defaultIv.setVisibility(bean.isDefault()?View.VISIBLE:View.INVISIBLE);
        holer.nameTv.setText(bean.getReceiverName());
        holer.phoneTv.setText(bean.getReceiverPhone());
        holer.addressDetailsTv.setText(bean.getReceiverAddress());
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }
}
