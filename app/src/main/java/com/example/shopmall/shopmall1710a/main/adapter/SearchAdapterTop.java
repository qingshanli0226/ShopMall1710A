package com.example.shopmall.shopmall1710a.main.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class SearchAdapterTop extends BaseQuickAdapter<String, BaseViewHolder> {
    public SearchAdapterTop(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_search_top_tv, item);
    }
}
