package com.example.shopmall.framework.view;

import android.view.View;
import androidx.annotation.IdRes;

public interface IBaseFragment {
    int bindLayout();
    void initView();
    void inject();
    <T extends View> T findViewById(@IdRes int id);
    void initPresenter();
    void initData();
}
