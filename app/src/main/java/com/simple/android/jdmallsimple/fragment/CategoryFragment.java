package com.simple.android.jdmallsimple.fragment;


import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simple.android.jdmallsimple.R;
import com.simple.android.jdmallsimple.adapter.TopCategoryAdapter;
import com.simple.android.jdmallsimple.bean.RTopCategory;
import com.simple.android.jdmallsimple.cons.IdiyMessage;
import com.simple.android.jdmallsimple.controller.CategoryController;
import com.simple.android.jdmallsimple.ui.SubCategoryView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private ListView mTopCategoryLv;
    private TopCategoryAdapter mTopCategoryAdapter;
    private SubCategoryView mSubCategoryView;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.TOPCATEGORY_ACTION_RESULT:
                handleTopCategory((List<RTopCategory>) msg.obj);
                break;
        }
    }

    private void handleTopCategory(List<RTopCategory> datas) {
        mTopCategoryAdapter.setDatas(datas);
        mTopCategoryAdapter.notifyDataSetChanged();
        mTopCategoryLv.performItemClick(null, 0, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
        mController.sendAsyncMessage(IdiyMessage.TOPCATEGORY_ACTION, 0);
    }

    @Override
    protected void initController() {
        mController = new CategoryController(getActivity());
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mTopCategoryLv = getActivity().findViewById(R.id.top_lv);
        mTopCategoryAdapter = new TopCategoryAdapter(getActivity());
        mTopCategoryLv.setAdapter(mTopCategoryAdapter);
        mTopCategoryLv.setOnItemClickListener(this);

        mSubCategoryView = (SubCategoryView) getActivity().findViewById(R.id.subcategory);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
//		1.记录当前哪个位置被点击了
        mTopCategoryAdapter.mCurrentTabPosition = position;
//		2.刷新列表
        mTopCategoryAdapter.notifyDataSetChanged();
//		将数据添加到SubCategory
        RTopCategory topCategoryBean = (RTopCategory) mTopCategoryAdapter.getItem(position);
//		点击左边的列表 告诉右边的View说 你可以刷新界面
        mSubCategoryView.onShow(topCategoryBean);
    }
}