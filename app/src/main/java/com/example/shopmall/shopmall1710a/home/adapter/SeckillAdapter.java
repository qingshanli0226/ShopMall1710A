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

public class SeckillAdapter extends BaseQuickAdapter<HomeEntity.ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    public SeckillAdapter(@Nullable List<HomeEntity.ResultBean.SeckillInfoBean.ListBean> data) {
        super(R.layout.item_item_seckill, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity.ResultBean.SeckillInfoBean.ListBean item) {
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getFigure())
                .into((ImageView) helper.getView(R.id.item_item_hot_iv));
        helper.setText(R.id.item_item_hot_tv,"$"+item.getCover_price());
    }
}
