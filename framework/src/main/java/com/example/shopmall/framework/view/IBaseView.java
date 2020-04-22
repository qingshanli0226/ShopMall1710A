package com.example.shopmall.framework.view;


import com.example.shopmall.common.ErrorBean;

public interface IBaseView<T> {
    void onHttpReceived(int requestCode, T data);
    void onHttpReceivedFailed(int requestCode, ErrorBean errorBean);
    //定义两个方法，去显示加载的loaing页面和数据返回后关闭loading页面的方法
    void showLoading();
    void hideLoading();
}
