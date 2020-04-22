package com.example.shopmall.framework.presenter;

public interface IPresenter {
    // requestCode 区分不同返回 数据类型
    void getHttpData(int requestCode);
    void postHttpData(int requestCode);
    void postHttpDataWithJson(int requestCode);

    void destroy();
}
