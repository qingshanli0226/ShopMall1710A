package com.example.shopmall.shopmall1710a.type.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.mode.DataBean;

import java.util.List;

public class HotProAdapter extends BaseQuickAdapter<DataBean.HotProductListBean, BaseViewHolder> {

    public HotProAdapter(int layoutResId, @Nullable List<DataBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean.HotProductListBean item) {
        helper.setText(R.id.hotProTv,"￥"+item.getCover_price());
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getFigure()).into((ImageView) helper.getView(R.id.hotProPic));
    }
}
