package com.example.shopmall.shopmall1710a.user.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.buy.entity.CollectEntitiy;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class CollectAdapter extends BaseQuickAdapter<CollectEntitiy, BaseViewHolder> {

    public CollectAdapter(@Nullable List<CollectEntitiy> data) {
        super(R.layout.item_collect , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectEntitiy item) {
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getImg())
                .into((ImageView) helper.getView(R.id.itemCollectImage));
        helper.setText(R.id.itemCollectName,item.getName());
        helper.setText(R.id.itemCollectPrice,item.getPrice());
    }
}
