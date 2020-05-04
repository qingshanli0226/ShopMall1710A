package com.example.shopmall.shopmall1710a.type.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.entity.TypeBean;

import java.util.List;

public class RightHotAdapter extends BaseQuickAdapter<TypeBean.ResultBean.HotProductListBean, BaseViewHolder> {
    private Context context;
    public RightHotAdapter(@Nullable List<TypeBean.ResultBean.HotProductListBean> data, Context context) {
        super(R.layout.item_right_hot, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBean.ResultBean.HotProductListBean item) {
        Glide.with(context).load(Constant.BASE_IMG+item.getFigure())
                .into((ImageView) helper.getView(R.id.itemLeftItemImage));
        helper.setText(R.id.itemLeftItemPrice,item.getCover_price());
    }
}
