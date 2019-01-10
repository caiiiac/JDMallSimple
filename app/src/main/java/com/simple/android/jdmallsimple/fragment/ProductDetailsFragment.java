package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.activity.ProductDetailsActivity;
import com.simple.android.jdmallsimple.cons.NetworkConst;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends BaseFragment {


    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_details,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    @Override
    protected void initUI() {
        mWebView =(WebView) getActivity().findViewById(R.id.webview);
        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        mWebView.loadUrl(NetworkConst.PRODUCTDETAIL_URL+"?productId="+activity.mProductId);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
    }
}
