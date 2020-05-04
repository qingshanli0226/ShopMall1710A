package com.example.shopmall.buy;


import com.example.shopmall.framework.base.ShopCartBean;

//定义事件,这些都是点击事件。当点击事件发生时，通过该接口可以通知其他模块。其他模块收到点击事件时，可以做出UI的改变
public interface IShopcarEventListener {
    void onEditChange(boolean isEdit);//编辑事件
    void onProductSelectChanged(boolean isSelected, ShopCartBean.ShopcarData shopcarData);//产品选择事件
    void onProductCountChanged(ShopCartBean.ShopcarData shopcarData, int count);
    void onAllSelectChanged(boolean isAllSelected);
    void onPayEventChanged(float payValue);
    void onProductDeleted();
    void onProductSaved();
}
