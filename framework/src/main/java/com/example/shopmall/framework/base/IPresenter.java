package com.example.shopmall.framework.base;

public interface IPresenter<T>{

    //定义通用方法，所有的get请求都可以通过该方法获取数据
    void getHttpData(int requestCode);

    //定义通用方法，所有的post请求,参数以表单形式发送的,都可以通过该方法获取数据
    void postHttpData(int requestCode);

    //定义通用方法，所有的post请求参数以Json形式发送都可以通过该方法获取数据
    //异步方法
    void postHttpDataWithJson(int requestCode);
    void attachView(IBaseView<T> iBaseView);
    void detachView();
}
