package com.example.shopmall.shopmall1710a.main.adapter;


import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.entity.ShopCarEntity;

import java.util.List;


public class ShortCarAdapter extends BaseQuickAdapter<ShopCarEntity, BaseViewHolder> {
    public ShortCarAdapter(@Nullable List<ShopCarEntity> data) {
        super(R.layout.item_short_car , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCarEntity item) {
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getUrl())
                .into((ImageView) helper.getView(R.id.itemShortCarIv));
    }
}
