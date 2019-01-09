package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.activity.ProductDetailsActivity;
import com.simple.android.jdmallsimple.adapter.GoodCommentAdapter;
import com.simple.android.jdmallsimple.adapter.ProductAdAdapter;
import com.simple.android.jdmallsimple.adapter.TypeListAdapter;
import com.simple.android.jdmallsimple.bean.RGoodComment;
import com.simple.android.jdmallsimple.bean.RProductInfo;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.controller.ProductDetailsController;
import com.simple.android.jdmallsimple.ui.NumberInputView;
import com.simple.android.jdmallsimple.util.FixedViewUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductIntroduceFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private ViewPager mAdVp;
    private ProductAdAdapter mAdAdapter;
    private ProductDetailsActivity mActivity;
    private TextView mAdIndicator;
    private Timer mTimer;
    private TextView mProductNameTv;
    private TextView mSelfSaleTv;
    private TextView mRecommendProductTv;
    private ListView mTypeListLv;
    private TypeListAdapter mTypeListAdapter;
    private NumberInputView mNumberInputView;
    private TextView mGoodCommentRateTv;
    private TextView mGoodCommentTv;
    private ListView mGoodCommentLv;
    private GoodCommentAdapter mGoodCommentAdapter;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.PRODUCT_INFO_ACTION_RESULT:
                handleProductInfo(msg.obj);
                break;
            case IdiyMessage.GOOD_COMMENT_ACTION_RESULT:
                hansdleGoodComment((List<RGoodComment>)msg.obj);
                break;
        }
    }

    private void hansdleGoodComment(List<RGoodComment> datas){
        mGoodCommentAdapter.setDatas(datas);
        mGoodCommentAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mGoodCommentLv);
    }

    private void handleProductInfo(Object obj) {
        if (obj == null) {
            tip("数据异常");
            mActivity.finish();
            return;
        }
        RProductInfo bean = (RProductInfo) obj;
        handleAdBanner(bean.getImgUrls());
        mProductNameTv.setText(bean.getName());
        mSelfSaleTv.setVisibility(bean.isIfSaleOneself()?View.VISIBLE:View.INVISIBLE);
        mRecommendProductTv.setText(bean.getRecomProduct());
        handleTypeListLv(bean.getTypeList());
        mNumberInputView.setMax(bean.getStockCount());

        mGoodCommentRateTv.setText(bean.getFavcomRate()+"%好评");
        mGoodCommentTv.setText(bean.getCommentCount()+"人评论");
    }

    private void handleTypeListLv(String typeListJson) {
        List<String> datas = JSON.parseArray(typeListJson,String.class);
        mTypeListAdapter.setDatas(datas);
        mTypeListAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mTypeListLv);
    }

    /**
     * 处理广告图片信息
     */
    private void handleAdBanner(String imgUrls) {
        final List<String> imageUrList = JSON.parseArray(imgUrls, String.class);
        mAdAdapter.setDatas(getActivity(), imageUrList);
        mAdAdapter.notifyDataSetChanged();
        mAdIndicator.setText(1 + "/" + imageUrList.size());

        initAdBannerTimer(imageUrList);
    }

    private void initAdBannerTimer(final List<String> imageUrList) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                translateAdBannerItem(imageUrList);
            }

        }, 3 * 1000, 3 * 1000);
    }

    /**
     * 指定广告的item
     */
    private void translateAdBannerItem(final List<String> imageUrList) {
        if (imageUrList != null && imageUrList.size() != 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentItem = mAdVp.getCurrentItem();
                    currentItem++;
                    if (currentItem > imageUrList.size() - 1) {
                        currentItem = 0;
                    }
                    mAdVp.setCurrentItem(currentItem);
                    mAdIndicator.setText((currentItem+1)+"/"+imageUrList.size());
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_introduce, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
        mActivity = (ProductDetailsActivity) getActivity();
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_INFO_ACTION,
                mActivity.mProductId);
        mController.sendAsyncMessage(IdiyMessage.GOOD_COMMENT_ACTION,
                mActivity.mProductId);
    }

    @Override
    protected void initController() {
        mController = new ProductDetailsController(getActivity());
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mAdVp = (ViewPager) getActivity().findViewById(R.id.advp);
        mAdAdapter = new ProductAdAdapter();
        mAdVp.setAdapter(mAdAdapter);
        mAdIndicator = (TextView) getActivity().findViewById(R.id.vp_indic_tv);
        mProductNameTv = (TextView) getActivity().findViewById(R.id.name_tv);
        mSelfSaleTv = (TextView) getActivity().findViewById(R.id.self_sale_tv);
        mRecommendProductTv = (TextView) getActivity().findViewById(R.id.recommend_p_tv);
        mTypeListLv = (ListView) getActivity().findViewById(R.id.product_versions_lv);
        mTypeListAdapter = new TypeListAdapter(getActivity());
        mTypeListLv.setAdapter(mTypeListAdapter);
        mTypeListLv.setOnItemClickListener(this);
        mNumberInputView = (NumberInputView) getActivity().findViewById(R.id.number_input_et);
        mGoodCommentRateTv = (TextView) getActivity().findViewById(R.id.good_rate_tv);
        mGoodCommentTv = (TextView) getActivity().findViewById(R.id.good_comment_tv);
        mGoodCommentLv = (ListView) getActivity().findViewById(R.id.good_comment_lv);
        mGoodCommentAdapter = new GoodCommentAdapter(getActivity());
        mGoodCommentLv.setAdapter(mGoodCommentAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        mTypeListAdapter.mCurrentTabPosition=position;
        mTypeListAdapter.notifyDataSetChanged();
    }
}
