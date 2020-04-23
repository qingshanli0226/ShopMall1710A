package com.example.shopmall.framework.base.presenter;

public interface IBasePresenter {
    void destroy();
    void getHttpData(int code);
    void postHttpData(int code);
    void postBodyHttpData(int code);
}
