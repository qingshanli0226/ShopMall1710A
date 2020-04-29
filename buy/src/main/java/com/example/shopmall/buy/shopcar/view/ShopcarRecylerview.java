package com.example.shopmall.buy.shopcar.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.shopcar.IShopcarEventListener;
import com.example.shopmall.framework.entity.ShopCartBean;
import java.util.ArrayList;
import java.util.List;


public class ShopcarRecylerview extends RecyclerView implements IShopcarEventListener {
    private IShopcarEventListener iShopcarEventListener;
    private ShopcarAdapter shopcarAdapter;

    private List<ShopCartBean.ResultBean> shopcarData = new ArrayList<>();

    public void addShopcarData(List<ShopCartBean.ResultBean> shopcardataList) {
        shopcarData.clear();
        shopcarData.addAll(shopcardataList);
        shopcarAdapter.notifyDataSetChanged();
    }


    public ShopcarRecylerview(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ShopcarRecylerview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShopcarRecylerview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        shopcarAdapter = new ShopcarAdapter();
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(shopcarAdapter);
    }

    public void setiShopcarEventListener(IShopcarEventListener iShopcarEventListener) {
        this.iShopcarEventListener = iShopcarEventListener;
    }

    @Override
    public void onEditChange(boolean isEdit) {

    }

    @Override
    public void onProductSelectChanged(boolean isSelected, float productPrice) {

    }

    @Override
    public void onProductCountChanged(int count) {

    }

    @Override
    public void onAllSelectChanged(boolean isAllSelected) {

    }

    @Override
    public void onPayEventChanged(float payValue) {

    }

    @Override
    public void onProductDeleted() {

    }

    @Override
    public void onProductSaved() {

    }

    private class ShopcarAdapter extends RecyclerView.Adapter<ShopcarViewHolder> {

        @NonNull
        @Override
        public ShopcarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_car, viewGroup,false);

            return new ShopcarViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull ShopcarViewHolder shopcarViewHolder, final int i) {
             shopcarViewHolder.productCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     shopcarData.get(i).setProductSelected(isChecked);
                     iShopcarEventListener.onProductSelectChanged(isChecked, Float.valueOf(shopcarData.get(i).getProductPrice()));
                 }
             });

             shopcarViewHolder.productCount.setText(shopcarData.get(i).getProductNum());
            Glide.with(shopcarViewHolder.productImageView.getContext()).load("http://49.233.93.155:8080/atguigu/img/"+
            shopcarData.get(i).getUrl()).into(shopcarViewHolder.productImageView);

        }

        @Override
        public int getItemCount() {
            return shopcarData.size();
        }
    }

    public static class ShopcarViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImageView;
        public CheckBox productCheckBox;
        public TextView productCount;
        public ShopcarViewHolder(View rootView) {
            super(rootView);
            productCheckBox = rootView.findViewById(R.id.productSelect);
            productImageView = rootView.findViewById(R.id.productImage);
            productCount = rootView.findViewById(R.id.productCount);
        }
    }
}