package com.example.shopmall.shopmall1710a.main.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.Goods;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeckillAdapter extends BaseQuickAdapter<Goods.SeckillInfoBean.ListBean, BaseViewHolder> {
    public SeckillAdapter(int layoutResId, @Nullable List<Goods.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Goods.SeckillInfoBean.ListBean seckillInfoBean) {
        holder.setText(R.id.item_home_seckill_tv_cover_price, "￥" + seckillInfoBean.getCover_price());
        holder.setText(R.id.item_home_seckill_tv_origin_price, "￥" + seckillInfoBean.getOrigin_price());
        TextView textView = holder.getView(R.id.item_home_seckill_tv_origin_price);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        Glide.with(getContext()).load(Constant.BASE_URL_IMG + seckillInfoBean.getFigure())
                .into((ImageView) holder.getView(R.id.item_home_seckill_imtv));
    }
}
