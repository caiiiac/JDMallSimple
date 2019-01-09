package com.simple.android.jdmallsimple.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdAdapter extends PagerAdapter {

    private List<String> mImageUrList;
    private List<SimpleDraweeView> mSmartImageViews;

    public void setDatas(Context c, List<String> imageUrList) {
        mImageUrList=imageUrList;
        //当数据有了之后 就有对应的图片
        mSmartImageViews=new ArrayList<SimpleDraweeView>();
        for (int i = 0; i < imageUrList.size(); i++) {
//			创建控件 设置宽高 设置数据源 添加到View容器中
//			如果创建的控件看不见: 添加到容器中被调用   控件不可见 控件大小没设置--->可以给控件一个颜色值
            SimpleDraweeView smiv=new SimpleDraweeView(c);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            smiv.setLayoutParams(params);
            smiv.setImageURI(mImageUrList.get(i).replace("http", "https"));
            mSmartImageViews.add(smiv);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView smiv = mSmartImageViews.get(position);
        container.addView(smiv);
        return smiv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        SimpleDraweeView smiv = mSmartImageViews.get(position);
        container.removeView(smiv);
    }

    @Override
    public int getCount() {
        return mSmartImageViews!=null?mSmartImageViews.size():0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }


}
