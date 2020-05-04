package com.example.shopmall.framework.base;


import com.example.shopmall.common.ErrorBean;

//定义一个通用的接口，所有页面都可以通过该接口获取数据
public interface IBaseView<T> {
    void onHtttpReceived(int requestCode, T data); //获取到数据
    void onHttpReceivedFailed(int requestCode, ErrorBean errorBean);
    //定义两个方法，去显示加载的loaing页面和数据返回后关闭loading页面的方法
    void showLoading();
    void hideLoading();
}
