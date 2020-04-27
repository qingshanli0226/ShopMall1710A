package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.entity.HomeEntity;

import java.util.List;


public class RecommendAdapter extends BaseQuickAdapter<HomeEntity.ResultBean.RecommendInfoBean, BaseViewHolder> {
    public RecommendAdapter(@Nullable List<HomeEntity.ResultBean.RecommendInfoBean> data) {
        super(R.layout.item_item_recommend , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity.ResultBean.RecommendInfoBean item) {
        helper.setText(R.id.item_item_recommend_name,item.getName());
        helper.setText(R.id.item_item_recommend_m,item.getCover_price());
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getFigure())
                .into((ImageView) helper.getView(R.id.item_item_recommend_iv));
    }
}
