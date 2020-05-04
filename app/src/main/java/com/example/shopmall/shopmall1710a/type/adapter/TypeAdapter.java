package com.example.shopmall.shopmall1710a.type.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.mode.TypeBean;

import java.util.List;

public class TypeAdapter extends BaseQuickAdapter<TypeBean, BaseViewHolder> {
    public TypeAdapter(int layoutResId, @Nullable List<TypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBean item) {
        helper.setText(R.id.typeName,item.getName());
    }
}
