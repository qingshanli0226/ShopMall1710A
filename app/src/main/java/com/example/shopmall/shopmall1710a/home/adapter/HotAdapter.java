package com.example.shopmall.shopmall1710a.home.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.model.HomeEntity;

import java.util.List;

public class HotAdapter extends BaseQuickAdapter<HomeEntity.ResultBean.HotInfoBean, BaseViewHolder> {
    public HotAdapter(@Nullable List<HomeEntity.ResultBean.HotInfoBean> data) {
        super(R.layout.item_item_hot , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity.ResultBean.HotInfoBean item) {
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getFigure())
                .into((ImageView) helper.getView(R.id.item_item_hot_iv));
        helper.setText(R.id.item_item_hot_tv,"$"+item.getCover_price());
    }
}
