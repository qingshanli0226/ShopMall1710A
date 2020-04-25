package com.example.shopmall.framework.mvp.view;


import com.example.shopmall.common.ErrorBean;

public interface IBaseView<T> {
    // 数据请求成功
    void onHttpReceived(int requestCode, T data);
    // 数据请求失败
    void onHttpReceivedFailed(int requestCode, ErrorBean errorBean);
    // 显示loading
    void showLoading();
    // 隐藏loading
    void hideLoading();
    // 提示用户吐司
    void showToast(String str);
}
