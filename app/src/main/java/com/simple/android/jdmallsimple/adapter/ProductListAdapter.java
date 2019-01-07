package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RProductList;

public class ProductListAdapter extends JDBaseAdapter<RProductList> {
    public ProductListAdapter(Context c) {
        super(c);
    }

    class ViewHolder{
        SimpleDraweeView smIv;
        TextView nameTv;
        TextView priceTv;
        TextView commentrateTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holer = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_lv_item, parent,false);
            holer =new ViewHolder();
            holer.smIv = convertView.findViewById(R.id.product_iv);
            holer.nameTv = convertView.findViewById(R.id.name_tv);
            holer.priceTv = convertView.findViewById(R.id.price_tv);
            holer.commentrateTv = convertView.findViewById(R.id.commrate_tv);
            convertView.setTag(holer);
        } else {
            holer=(ViewHolder) convertView.getTag();
        }
        RProductList bean = mDatas.get(position);

        holer.smIv.setImageURI(bean.getIconUrl().replace("http","https"));
        holer.nameTv.setText(bean.getName());
        holer.priceTv.setText("¥ "+bean.getPrice());
        holer.commentrateTv.setText(bean.getCommentCount()+"条评价  好评"+bean.getFavcomRate()+"%");
        return convertView;
    }
}
