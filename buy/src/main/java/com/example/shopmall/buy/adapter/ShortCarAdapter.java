package com.example.shopmall.buy.adapter;


import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.entity.ShopCarEntity;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;

import java.util.List;


public class ShortCarAdapter extends BaseQuickAdapter<ShopCarEntity, BaseViewHolder> {
    private List<Boolean> booleans;
    public ShortCarAdapter(@Nullable List<ShopCarEntity> data,List<Boolean> booleans) {
        super(R.layout.item_short_car , data);
        this.booleans = booleans;
    }

    @Override
    protected void convert(final BaseViewHolder helper, ShopCarEntity item) {
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getUrl())
                .into((ImageView) helper.getView(R.id.itemShortCarImage));
        helper.setText(R.id.itemShortCarNum,item.getProductNum());
        helper.setText(R.id.itemShortCarMoney,item.getProductPrice());

        CheckBox checkBox = helper.getView(R.id.itemShortCarCheckBox);
        checkBox.setChecked(booleans.get(helper.getAdapterPosition()));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (shortCarAdapterCheckedLisenner != null){
                        shortCarAdapterCheckedLisenner.isCheckedOk(helper.getAdapterPosition(),isChecked);
                    }
            }
        });
    }

    public interface ShortCarAdapterCheckedLisenner{
        void isCheckedOk(int position,boolean flag);
    }
    private ShortCarAdapterCheckedLisenner shortCarAdapterCheckedLisenner;

    public void setShortCarAdapterCheckedLisenner(ShortCarAdapterCheckedLisenner shortCarAdapterCheckedLisenner) {
        this.shortCarAdapterCheckedLisenner = shortCarAdapterCheckedLisenner;
    }
}
