package com.example.shopmall.framework.mvp.view;


import com.example.shopmall.framework.mvp.presenter.IPresenter;

import java.util.List;

public interface IBaseActivity {
    void initTitleBar();
    void initPresenter();
    int bindLayout();
    void initView();
    void inject();
    void initLoading();
    void initData();
    List<IPresenter> getPresenter();
}
