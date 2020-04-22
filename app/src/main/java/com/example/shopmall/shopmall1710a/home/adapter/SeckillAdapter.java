package com.example.shopmall.shopmall1710a.home.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;

import java.util.List;

public class SeckillAdapter extends BaseQuickAdapter<ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {

    public SeckillAdapter(int layoutResId, @Nullable List<ResultBean.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultBean.SeckillInfoBean.ListBean item) {
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getFigure()).into((ImageView) helper.getView(R.id.pic));

        helper.setText(R.id.title,"￥"+item.getCover_price());

        helper.setText(R.id.title1,"￥"+item.getOrigin_price());
        TextView view = helper.getView(R.id.title1);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
