package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.android.jdmallsimple.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductCommentFragment extends BaseFragment {


    public static final int ALL_COMMENT=0;
    public static final int GOOD_COMMENT=1;
    public static final int CENTER_COMMENT=2;
    public static final int BAD_COMMENT=3;
    public static final int HASIMAGE_COMMENT=4;

    public ProductCommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_comment, container, false);
    }

    @Override
    protected void initUI() {

    }
}
