package com.example.shopmall.shopmall1710a.home.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.mode.ResultBean;

import java.util.List;

public class RecommondAdapter extends BaseQuickAdapter<ResultBean.RecommendInfoBean, BaseViewHolder> {
    public RecommondAdapter(int layoutResId, @Nullable List<ResultBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ResultBean.RecommendInfoBean item) {
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getFigure()).into((ImageView) helper.getView(R.id.Recommmond_pic));
        helper.setText(R.id.Recommond_name,item.getName());
        helper.setText(R.id.Recommmond_price,"￥"+item.getCover_price());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowItemActivity.class);
                intent.putExtra("img","http://49.233.93.155:8080/atguigu/img"+item.getFigure());
                intent.putExtra("name",item.getName());
                intent.putExtra("price",item.getCover_price());
                view.getContext().startActivity(intent);
            }
        });
    }
}
