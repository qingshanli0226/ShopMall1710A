package com.example.shopmall.shopmall1710a.type.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.SeckillAdapter;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.example.shopmall.shopmall1710a.type.mode.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private DataBean dataBean;
    private LayoutInflater layoutInflater;
    int currentType=0;
    public DataAdapter(Context context, DataBean dataBean) {
        this.context = context;
        this.dataBean = dataBean;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1){
            return new TypeViewHolder(layoutInflater.inflate(R.layout.type_layout,null),context,dataBean);
        }
        if (i==0){
            return new HotProViewHolder(layoutInflater.inflate(R.layout.hot_layout,null),context,dataBean);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch(position) {
            case 0:
                currentType = 0;
                break;
            case 1:
                currentType = 1;
                break;
        }
        return currentType;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(getItemViewType(i)==1){
            TypeViewHolder typeViewHolder = (TypeViewHolder) viewHolder;
            typeViewHolder.setData(dataBean.getChild());
        }else if (getItemViewType(i)==0){
            HotProViewHolder hotProViewHolder = (HotProViewHolder) viewHolder;
            hotProViewHolder.setData(dataBean.getHot_product_list());
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class HotProViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public Context mContext;
        private DataBean dataBean;

        public HotProViewHolder(@NonNull View itemView, Context mContext, DataBean dataBean) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.hotProShow);
            this.mContext = mContext;
            this.dataBean = dataBean;
        }

        public void setData(List<DataBean.HotProductListBean> hotProductListBeans){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new HotProAdapter(R.layout.hot_item,hotProductListBeans));
        }
    }

    class TypeViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public Context mContext;
        private DataBean dataBean;

        public TypeViewHolder(@NonNull View itemView, Context mContext, DataBean dataBean) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.typeShow);
            this.mContext = mContext;
            this.dataBean = dataBean;
        }

        public void setData(List<DataBean.ChildBean> childBeans){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new MyTypeAdapter(R.layout.type_item,childBeans));
        }
    }
}
