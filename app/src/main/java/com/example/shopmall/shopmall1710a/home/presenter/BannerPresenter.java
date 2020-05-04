package com.example.shopmall.shopmall1710a.home.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class BannerPresenter extends BasePresenter<Object> {
    @Override
    protected String getPath() {
        return "/atguigu/json/HOME_URL.json";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<ResultBean>>(){}.getType();
    }
}
