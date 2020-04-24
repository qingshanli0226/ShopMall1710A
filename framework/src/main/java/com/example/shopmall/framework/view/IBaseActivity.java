package com.example.shopmall.framework.view;

public interface IBaseActivity {
    int bindLayout();
    void initView();
    void inject();
    void initPresenter();
    void initData();
}
