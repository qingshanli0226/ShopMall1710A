package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.Goods;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChannelAdapter extends BaseQuickAdapter<Goods.ChannelInfoBean, BaseViewHolder> {
    public ChannelAdapter(int layoutResId, @Nullable List<Goods.ChannelInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Goods.ChannelInfoBean channelInfoBean) {
        holder.setText(R.id.item_home_channel_tv, channelInfoBean.getChannel_name());
        Glide.with(getContext()).load(Constant.BASE_URL_IMG + channelInfoBean.getImage())
                .into((ImageView) holder.getView(R.id.item_home_channel_imv));
    }
}
