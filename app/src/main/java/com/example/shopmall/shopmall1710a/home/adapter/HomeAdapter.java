package com.example.shopmall.shopmall1710a.home.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.model.HomeEntity;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeEntity, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity item) {
        View view = helper.getView(R.id.home_rv_img);
        Glide.with(mContext).load(item.getUrl()).into((ImageView) view);
        helper.setText(R.id.home_rv_tv,item.getName());
    }
}
