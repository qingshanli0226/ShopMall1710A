package com.example.shopmall.shopmall1710a.main.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.SearchBean;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<SearchBean.ResultBean.HotProductListBean, BaseViewHolder> {
    public SearchAdapter(int layoutResId, @Nullable List<SearchBean.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.ResultBean.HotProductListBean item) {
        helper.setText(R.id.item_search_title, item.getName());
        helper.setText(R.id.item_search_price, "￥" + item.getCover_price());
        Glide.with(mContext).load(Constant.BASE_URL_IMG + item.getFigure())
                .into((ImageView) helper.getView(R.id.item_search_imv));
    }
}
