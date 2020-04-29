package com.example.shopmall.buy.adapter;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.buy.R;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.manager.AppCore;

import java.util.List;


public class ShortCarAdapter extends BaseQuickAdapter<ShortCarEntity.ResultBean, BaseViewHolder> {

    public ShortCarAdapter(@Nullable List<ShortCarEntity.ResultBean> data) {
        super(R.layout.item_short_car , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShortCarEntity.ResultBean item) {
        CheckBox itemShortCarCheckBox = helper.getView(R.id.itemShortCarCheckBox);
        itemShortCarCheckBox.setChecked(item.isProductSelected());
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getUrl())
                .into((ImageView) helper.getView(R.id.itemShortCarImage));
        helper.setText(R.id.itemShortCarNum,item.getProductNum());
        helper.setText(R.id.itemShortCarMoney,item.getProductPrice());

        TextView itemShortCarRemove = helper.getView(R.id.itemShortCarRemove);
        TextView itemShortCarAdd = helper.getView(R.id.itemShortCarAdd);

    }
}
