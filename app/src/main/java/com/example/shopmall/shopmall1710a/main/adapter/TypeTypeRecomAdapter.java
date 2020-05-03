package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.TypeEntity;

import java.util.List;

public class TypeTypeRecomAdapter extends BaseQuickAdapter<TypeEntity.HotProductListBean, BaseViewHolder> {

    public TypeTypeRecomAdapter(int layoutResId, @Nullable List<TypeEntity.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeEntity.HotProductListBean item) {
        helper.setText(R.id.item_type_recom_tv, "￥" + item.getCover_price());
        Glide.with(mContext).load(Constant.BASE_URL_IMG + item.getFigure())
                .into((ImageView) helper.getView(R.id.item_type_recom_imv));
    }
}
