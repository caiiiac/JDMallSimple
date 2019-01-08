package com.simple.android.jdmallsimple.ui.pop;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.listener.IProductSortChanegListener;

public class ProductSortPopWindow extends IPopupWindowProtocal implements OnClickListener {


    private IProductSortChanegListener mListener;

    public ProductSortPopWindow(Context mContext) {
        super(mContext);
    }

    public void setListener(IProductSortChanegListener listener) {
        this.mListener = listener;
    }

    @Override
    protected void initUI() {

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.product_sort_pop_view, null, false);
        contentView.findViewById(R.id.left_v).setOnClickListener(this);
        contentView.findViewById(R.id.all_sort).setOnClickListener(this);
        contentView.findViewById(R.id.new_sort).setOnClickListener(this);
        contentView.findViewById(R.id.comment_sort).setOnClickListener(this);

        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.update();
    }

    @Override
    public void onClick(View v) {

        if (mListener != null) {
            switch (v.getId()) {
                case R.id.all_sort:
                    mListener.onSortChanged(IProductSortChanegListener.ALLSORT);
                    break;
                case R.id.new_sort:
                    mListener.onSortChanged(IProductSortChanegListener.NEWSSORT);
                    break;
                case R.id.comment_sort:
                    mListener.onSortChanged(IProductSortChanegListener.COMMENTSORT);
                    break;
            }
        }
        onDismiss();
    }

    @Override
    public void onShow(View anchor) {

        if (mPopupWindow != null) {

            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            mPopupWindow.setHeight(height);
            mPopupWindow.showAsDropDown(anchor,0,0);
        }
    }
}
