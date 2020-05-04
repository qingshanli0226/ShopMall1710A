package com.example.shopmall.shopmall1710a.type.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class LeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public LeftAdapter(@Nullable List<String> data) {
        super(R.layout.item_left_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.itemLeftText,item);
    }
}
