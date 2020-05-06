package com.example.shopmall.shopmall1710a.main.view;

import com.example.shopmall.framework.base.bean.ShopCartBean;

//定义事件,这些都是点击事件。当点击事件发生时，通过该接口可以通知其他模块。其他模块收到点击事件时，可以做出UI的改变
public interface IShopcarEventListener {
    void onEditChange(boolean isEdit);//编辑事件
    void onProductSelectChanged(boolean isSelected, ShopCartBean.ResultBean shopcarData);//产品选择事件
    void onProductCountChanged(ShopCartBean.ResultBean shopcarData, int count);
    void onAllSelectChanged(boolean isAllSelected, int viewType);
    void onPayEventChanged(float payValue);
    void onProductDeleted();
    void onProductSaved();
}
