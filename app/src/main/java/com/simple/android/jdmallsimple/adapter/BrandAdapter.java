package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.bean.RBrand;

public class BrandAdapter extends JDBaseAdapter<RBrand> {

    public int mCurrentTabPosition=-1;

    public BrandAdapter(Context c) {
        super(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView brandBtn;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.brand_lv_item_layout, parent,false);
            brandBtn = convertView.findViewById(R.id.brand_tv);
            convertView.setTag(brandBtn);
        } else {
            brandBtn = (TextView) convertView.getTag();
        }
        RBrand bean = mDatas.get(position);
        brandBtn.setText(bean.getName());
        //2.修改样式
        brandBtn.setSelected(position==mCurrentTabPosition);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getId() : 0;
    }
}
