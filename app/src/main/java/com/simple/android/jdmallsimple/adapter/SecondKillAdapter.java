package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RSecondKill;


public class SecondKillAdapter extends JDBaseAdapter<RSecondKill> {

    public SecondKillAdapter(Context c) {
        super(c);
    }

    class ViewHolder{
        SimpleDraweeView smIv;
        TextView pointPriceTv;
        TextView allPriceTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holer=null;
        if (convertView==null) {
            convertView=mInflater.inflate(R.layout.home_seckill_item, parent,false);
            holer=new ViewHolder();
            holer.smIv= convertView.findViewById(R.id.image_iv);
            holer.pointPriceTv= convertView.findViewById(R.id.nowprice_tv);
            holer.allPriceTv= convertView.findViewById(R.id.normalprice_tv);
            convertView.setTag(holer);
        }else {
            holer=(ViewHolder) convertView.getTag();
        }
        RSecondKill bean = mDatas.get(position);
        holer.smIv.setImageURI(bean.getIconUrl().replace("http","https"));
        holer.pointPriceTv.setText("¥ "+bean.getPointPrice());
        holer.allPriceTv.setText(" ¥ "+bean.getAllPrice()+" ");
        holer.allPriceTv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        return convertView;
    }

}
