package com.example.shopmall.shopmall1710a.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;


import java.util.List;

public class ChannelAdapter extends BaseQuickAdapter<ResultBean.ChannelInfoBean, BaseViewHolder> {

    public ChannelAdapter(int layoutResId, @Nullable List<ResultBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultBean.ChannelInfoBean item) {
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getImage()).into((ImageView) helper.getView(R.id.pic));
        helper.setText(R.id.title,item.getChannel_name());
    }
}
