package com.example.shopmall.buy.adapter;

import android.util.Log;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.entity.NoPayEntity;

import java.util.List;

public class NoPayAdapter extends BaseQuickAdapter<NoPayEntity, BaseViewHolder> {
    public NoPayAdapter(@Nullable List<NoPayEntity> data) {
        super(R.layout.item_nopay , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoPayEntity item) {
        Log.i("boss", "convert: "+item.getOrderInfo());
        helper.setText(R.id.itemNoPayMsg, (String) item.getOrderInfo());
    }
}
