package com.example.shopmall.shopmall1710a.type.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.mode.DataBean;

import java.util.List;

public class MyTypeAdapter extends BaseQuickAdapter<DataBean.ChildBean, BaseViewHolder> {

    public MyTypeAdapter(int layoutResId, @Nullable List<DataBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean.ChildBean item) {
        helper.setText(R.id.typeTv,item.getName());
        Glide.with(mContext).load("http://49.233.93.155:8080/atguigu/img"+item.getPic()).into((ImageView) helper.getView(R.id.typePic));
    }
}
