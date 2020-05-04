package com.example.shopmall.shopmall1710a.type.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.entity.TypeBean;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class RightAdapter extends BaseQuickAdapter<TypeBean.ResultBean, BaseViewHolder> {
    private RightHotAdapter rightHotAdapter;
    private RightChildAdapter rightChildAdapter;
    private List<TypeBean.ResultBean.ChildBean> childBeans = new ArrayList<>();
    private List<TypeBean.ResultBean.HotProductListBean> hotProductListBeans = new ArrayList<>();
    private Context context;
    public RightAdapter(@Nullable List<TypeBean.ResultBean> data,Context context) {
        super(R.layout.item_right_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBean.ResultBean item) {
        childBeans.clear();
        hotProductListBeans.clear();
        childBeans.addAll(item.getChild());
        hotProductListBeans.addAll(item.getHot_product_list());
        RecyclerView hotRecyclerView = helper.getView(R.id.hotRecyclerView);
        hotRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        RecyclerView childRecyclerView = helper.getView(R.id.childRecyclerView);
        childRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        if (rightChildAdapter == null){
            rightChildAdapter = new RightChildAdapter(childBeans,context);
            childRecyclerView.setAdapter(rightChildAdapter);
        }else {
            rightChildAdapter.notifyDataSetChanged();
        }
        if (rightHotAdapter == null){
            rightHotAdapter = new RightHotAdapter(hotProductListBeans,context);
            hotRecyclerView.setAdapter(rightHotAdapter);
        }else {
            rightHotAdapter.notifyDataSetChanged();
        }
    }
}
