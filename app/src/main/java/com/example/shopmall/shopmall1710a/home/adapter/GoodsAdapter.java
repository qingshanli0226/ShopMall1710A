package com.example.shopmall.shopmall1710a.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ResultBean.HotInfoBean, BaseViewHolder> {

    public GoodsAdapter(int layoutResId, @Nullable List<ResultBean.HotInfoBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ResultBean.HotInfoBean item) {
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getFigure()).into((ImageView) helper.getView(R.id.pic));

        helper.setText(R.id.title,item.getName());

        helper.setText(R.id.title1,"￥"+item.getCover_price());
    }
}
