package com.example.shopmall.framework.presenter;

public interface IPresenter {
    // requestCode 区分不同返回 数据类型
    void getHttpData(int requestCode); // get请求
    void postHttpData(int requestCode); // post请求 表单
    void postHttpDataWithJson(int requestCode); // post请求 json
    void destroy(); // 销毁
}
