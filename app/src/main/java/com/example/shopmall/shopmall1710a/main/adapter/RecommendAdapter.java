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

import static com.example.shopmall.shopmall1710a.app.ShopMallApplication.getContext;

public class RecommendAdapter extends BaseQuickAdapter<Goods.RecommendInfoBean, BaseViewHolder> {
    public RecommendAdapter(int layoutResId, @Nullable List<Goods.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Goods.RecommendInfoBean recommendInfoBean) {
        holder.setText(R.id.item_home_recommend_tv_title, recommendInfoBean.getName());
        holder.setText(R.id.item_home_recommend_tv_price, "￥" + recommendInfoBean.getCover_price());
        Glide.with(mContext).load(Constant.BASE_URL_IMG + recommendInfoBean.getFigure())
                .into((ImageView) holder.getView(R.id.item_home_recommend_imtv));
    }
}
