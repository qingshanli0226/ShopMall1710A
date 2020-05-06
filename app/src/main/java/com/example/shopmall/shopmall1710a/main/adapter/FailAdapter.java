package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class FailAdapter extends BaseQuickAdapter<ShopCartBean.ResultBean, BaseViewHolder> {
    public FailAdapter(int layoutResId, @Nullable List<ShopCartBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean.ResultBean item) {
        helper.setText(R.id.fail_tv_name, item.getProductName());
        helper.setText(R.id.fail_tv_price, "￥" + item.getProductPrice());
        Glide.with(mContext).load(Constant.BASE_URL_IMG + item.getUrl())
                .into((ImageView) helper.getView(R.id.fail_imv));

        helper.addOnClickListener(R.id.fail_order);

    }
}
