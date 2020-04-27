package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.shopmall1710a.R;
import io.reactivex.annotations.Nullable;

import java.util.List;

public class CartAdapter extends BaseQuickAdapter<ShopCartBean.ResultBean, BaseViewHolder> {
    public CartAdapter(int layoutResId, @Nullable List<ShopCartBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean.ResultBean item) {
        helper.setText(R.id.goods_name, item.getProductName());
        helper.setText(R.id.goods_num, item.getProductNum() + "");
        helper.setText(R.id.goods_price, "￥" + item.getProductPrice());
        Glide.with(getContext()).load(Constant.BASE_URL_IMG + item.getUrl()).into((ImageView) helper.getView(R.id.goods_pic));
    }
}
