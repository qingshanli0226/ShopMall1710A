package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.Goods;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ActAdapter extends BaseQuickAdapter<Goods.ActInfoBean, BaseViewHolder> {
    public ActAdapter(int layoutResId, @Nullable List<Goods.ActInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Goods.ActInfoBean actInfoBean) {
        Glide.with(mContext).load(Constant.BASE_URL_IMG + actInfoBean.getIcon_url())
                .into((ImageView) holder.getView(R.id.item_home_act_imv));
    }
}
