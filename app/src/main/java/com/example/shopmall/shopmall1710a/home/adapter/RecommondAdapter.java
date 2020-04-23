package com.example.shopmall.shopmall1710a.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.mode.ResultBean;

import java.util.List;

public class RecommondAdapter extends BaseQuickAdapter<ResultBean.RecommendInfoBean, BaseViewHolder> {
    public RecommondAdapter(int layoutResId, @Nullable List<ResultBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultBean.RecommendInfoBean item) {
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getFigure()).into((ImageView) helper.getView(R.id.Recommmond_pic));
        helper.setText(R.id.Recommond_name,item.getName());
        helper.setText(R.id.Recommmond_price,"￥"+item.getCover_price());
    }
}
