package com.simple.android.jdmallsimple.ui.pop;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public abstract class IPopupWindowProtocal {

    protected PopupWindow mPopupWindow;
    protected Context mContext;

    public IPopupWindowProtocal(Context mContext) {
        this.mContext = mContext;
        initUI();
    }

    protected abstract void initUI();

    public abstract void onShow(View anchor);

    public void onDismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
