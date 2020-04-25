package com.example.shopmall.shopmall1710a.home.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.entity.HomeEntity;

import java.util.List;

public class ChannelAdapter extends BaseQuickAdapter<HomeEntity.ResultBean.ChannelInfoBean, BaseViewHolder> {
    public ChannelAdapter(@Nullable List<HomeEntity.ResultBean.ChannelInfoBean> data) {
        super(R.layout.item_item_channel, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity.ResultBean.ChannelInfoBean item) {
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getImage())
                .into((ImageView) helper.getView(R.id.item_item_channel_iv));
        helper.setText(R.id.item_item_channel_tv,item.getChannel_name());
    }
}
