package com.example.shopmall.shopmall1710a.fragment.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.fragment.home.entity.HomeBean;

import java.util.List;

public class ChannelInfoAdapter extends BaseQuickAdapter<HomeBean.ResultBean.ChannelInfoBean, BaseViewHolder> {
    public ChannelInfoAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.ChannelInfoBean item) {
        helper.setText(R.id.tv_shop_chan,item.getChannel_name());
        ImageView view = helper.getView(R.id.iv_shop_chan);
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img/"+item.getImage()).into(view);
    }
}
