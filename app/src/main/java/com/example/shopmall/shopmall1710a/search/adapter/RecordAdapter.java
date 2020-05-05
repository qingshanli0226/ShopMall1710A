package com.example.shopmall.shopmall1710a.search.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class RecordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RecordAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.recordName,item);
    }
}
