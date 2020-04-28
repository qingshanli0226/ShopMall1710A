package com.example.shopmall.shopmall1710a.fragment.home.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.fragment.home.entity.HomeBean;
import com.example.shopmall.shopmall1710a.product.ProductDetailActivity;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeBean.ResultBean.HotInfoBean, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.HotInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeBean.ResultBean.HotInfoBean item) {
        helper.setText(R.id.tv_shop,item.getName());
        ImageView view = helper.getView(R.id.iv_shop);
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img/"+item.getFigure()).into(view);
    }
}
