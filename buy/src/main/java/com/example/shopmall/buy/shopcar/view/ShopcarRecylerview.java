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
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.bean.ShopCartBean;
import okhttp3.Interceptor;

import java.util.ArrayList;
import java.util.List;

public class ShopcarRecylerview extends RecyclerView implements IShopcarEventListener {
    private IShopcarEventListener iShopcarEventListener;
    private ShopcarAdapter shopcarAdapter;

    private List<ShopCartBean.ShopcarData> shopcarDataList = new ArrayList<>();

    public void addShopcarData(List<ShopCartBean.ShopcarData> shopcardataList) {
        shopcarDataList.clear();
        shopcarDataList.addAll(shopcardataList);
        shopcarAdapter.notifyDataSetChanged();
    }

    //局部刷新列表数据
    public void updateOneData(ShopCartBean.ShopcarData shopcarData, int index) {
        ShopCartBean.ShopcarData item = shopcarDataList.get(index);
        item.setProductNum(shopcarData.getProductNum());
        item.setProductSelected(shopcarData.isProductSelected());
        shopcarAdapter.notifyItemChanged(index);
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
    public void onProductSelectChanged(boolean isSelected, ShopCartBean.ShopcarData shopcarData) {

    }

    @Override
    public void onProductCountChanged(ShopCartBean.ShopcarData shopcarData, int count) {

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
             shopcarViewHolder.productCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iShopcarEventListener.onProductSelectChanged(!shopcarDataList.get(i).isProductSelected(),
                            shopcarDataList.get(i));
                }
            });

             shopcarViewHolder.productCount.setText(shopcarDataList.get(i).getProductNum());
             Glide.with(shopcarViewHolder.productImageView.getContext()).load(Constant.BASE_IMAGE_URL+
             shopcarDataList.get(i).getUrl()).into(shopcarViewHolder.productImageView);
             shopcarViewHolder.productPrice.setText(shopcarDataList.get(i).getProductPrice());
             shopcarViewHolder.productName.setText(shopcarDataList.get(i).getProductName());

             //如果判断该产品选择值为true,需要做什么
             if (shopcarDataList.get(i).isProductSelected()) {
                 shopcarViewHolder.productCheckBox.setChecked(true);
             } else {
                 shopcarViewHolder.productCheckBox.setChecked(false);
             }


             shopcarViewHolder.btnAdd.setOnClickListener(new OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int num = Integer.valueOf(shopcarDataList.get(i).getProductNum());
                     //当等于1时，值不能再改变

                     int newNum = num +1;
                     iShopcarEventListener.onProductCountChanged(shopcarDataList.get(i),newNum);
                 }
             });

             shopcarViewHolder.btnSub.setOnClickListener(new OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int num = Integer.valueOf(shopcarDataList.get(i).getProductNum());
                     //当等于1时，值不能再改变
                      if (num <= 1) {
                          return;
                      }

                      int newNum = num -1;
                      iShopcarEventListener.onProductCountChanged(shopcarDataList.get(i),newNum);
                 }
             });

        }

        @Override
        public int getItemCount() {
            return shopcarDataList.size();
        }
    }

    public static class ShopcarViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImageView;
        public CheckBox productCheckBox;
        public TextView productCount;
        public TextView productPrice;
        public TextView productName;
        private ImageView btnAdd;
        private ImageView btnSub;
        public ShopcarViewHolder(View rootView) {
            super(rootView);
            productCheckBox = rootView.findViewById(R.id.productSelect);
            productImageView = rootView.findViewById(R.id.productImage);
            productCount = rootView.findViewById(R.id.productCount);
            productPrice = rootView.findViewById(R.id.productPrice);
            productName = rootView.findViewById(R.id.productName);
            btnAdd = rootView.findViewById(R.id.btnAdd);
            btnSub = rootView.findViewById(R.id.btnSub);
        }
    }
}
