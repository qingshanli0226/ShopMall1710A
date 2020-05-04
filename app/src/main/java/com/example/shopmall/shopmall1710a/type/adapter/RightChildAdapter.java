package com.example.shopmall.shopmall1710a.type.adapter;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.entity.TypeBean;

import java.util.List;
import java.util.logging.Handler;

public class RightChildAdapter extends BaseQuickAdapter<TypeBean.ResultBean.ChildBean, BaseViewHolder> {
    private Context context;
    public RightChildAdapter(@Nullable List<TypeBean.ResultBean.ChildBean> data,Context context) {
        super(R.layout.item_right_child, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBean.ResultBean.ChildBean item) {
        Glide.with(context)
                .load(Constant.BASE_IMG+item.getPic())
                .into((ImageView) helper.getView(R.id.itemRightItemImage));
        helper.setText(R.id.itemRightItemName,item.getName());
    }
}
