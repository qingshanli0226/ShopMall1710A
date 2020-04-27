package com.example.shopmall.net;

//定义presenter实现了那些业务功能
public interface IPresenter {
    //requestCode区分不同的网络请求的。如果在一个页面中，使用多个presenter，要使用requestCode来区分
    void getHttpData(int requestCode);
    void postHttpData(int requestCode);
    void postHttpDataWithJson(int requestCode);
    void destroy();
}
