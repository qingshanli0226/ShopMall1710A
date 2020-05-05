package com.example.shopmall.shopmall1710a.search;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.search.adapter.SearchProAdapter;
import com.example.shopmall.shopmall1710a.search.mode.SearchResultBean;
import com.example.shopmall.shopmall1710a.search.presenter.SearchPresenter;
import com.example.shopmall.shopmall1710a.type.presenter.DataPresenter;

import java.util.ArrayList;
import java.util.List;

public class HotSearchFragment extends BaseFragment<Object> {
    private List<SearchResultBean.HotProductListBean> list = new ArrayList<>();
    private SearchProAdapter searchProAdapter;
    private android.support.v7.widget.RecyclerView showResult;

    @Override
    protected void initData() {
        SearchPresenter searchPresenter = new SearchPresenter();
        searchPresenter.attachView(this);
        searchPresenter.addParam("aaaa");
        searchPresenter.getHttpData(7);
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        showResult = rootView.findViewById(R.id.showResult);
        searchProAdapter = new SearchProAdapter(R.layout.search_item,list);
        showResult.setLayoutManager(new LinearLayoutManager(getContext()));
        showResult.setAdapter(searchProAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hotsearch;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode==7){
            List<SearchResultBean> data1 = (List<SearchResultBean>) data;
            List<SearchResultBean.HotProductListBean> hot_product_list = data1.get(0).getHot_product_list();
            list.addAll(hot_product_list);
            Log.e("TAG", "guofeng: "+hot_product_list.size());
            searchProAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
