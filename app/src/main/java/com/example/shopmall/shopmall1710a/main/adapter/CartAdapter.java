package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.shopmall1710a.R;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class CartAdapter extends BaseQuickAdapter<ShopCartBean.ResultBean, BaseViewHolder> {

    public CartAdapter(int layoutResId, @Nullable List<ShopCartBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ShopCartBean.ResultBean resultBean) {
        holder.setText(R.id.goods_name, resultBean.getProductName());
        holder.setText(R.id.goods_num, resultBean.getProductNum() + "");
        holder.setText(R.id.goods_price, "￥" + resultBean.getProductPrice());
        Glide.with(mContext).load(Constant.BASE_URL_IMG + resultBean.getUrl()).into((ImageView) holder.getView(R.id.goods_pic));

        holder.addOnClickListener(R.id.goods_add);
        holder.addOnClickListener(R.id.goods_reduce);
        holder.addOnClickListener(R.id.checkbox_goods);


    }
}

