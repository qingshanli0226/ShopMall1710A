package com.example.shopmall.shopmall1710a.search.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.search.mode.SearchResultBean;
import com.example.shopmall.shopmall1710a.type.mode.DataBean;

import java.util.List;

public class SearchProAdapter extends BaseQuickAdapter<SearchResultBean.HotProductListBean, BaseViewHolder> {

    public SearchProAdapter(int layoutResId, @Nullable List<SearchResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResultBean.HotProductListBean item) {
        helper.setText(R.id.search_price,"￥"+item.getCover_price());
        helper.setText(R.id.search_name,item.getName()+"");
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getFigure()).into((ImageView) helper.getView(R.id.search_pic));
    }
}
