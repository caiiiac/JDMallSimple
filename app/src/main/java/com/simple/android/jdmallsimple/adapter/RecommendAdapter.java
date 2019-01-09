package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RRecommndProduct;


public class RecommendAdapter extends JDBaseAdapter<RRecommndProduct> {

    public RecommendAdapter(Context c) {
        super(c);
    }

    class ViewHolder{
        SimpleDraweeView smIv;
        TextView nameTv;
        TextView priceTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holer=null;
        if (convertView==null) {
            convertView=mInflater.inflate(R.layout.recommend_gv_item, parent,false);
            holer=new ViewHolder();
            holer.smIv= convertView.findViewById(R.id.image_iv);
            holer.nameTv= convertView.findViewById(R.id.name_tv);
            holer.priceTv= convertView.findViewById(R.id.price_tv);
            convertView.setTag(holer);
        }else {
            holer=(ViewHolder) convertView.getTag();
        }
        RRecommndProduct bean = mDatas.get(position);
        holer.smIv.setImageURI(bean.getIconUrl().replace("http","https"));
        holer.nameTv.setText(bean.getName());
        holer.priceTv.setText("¥ "+bean.getPrice());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return mDatas!=null?mDatas.get(position).getProductId():0;
    }
}
