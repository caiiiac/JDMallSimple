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
public class ProductDetailsFragment extends BaseFragment {


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    protected void initUI() {

    }
}
