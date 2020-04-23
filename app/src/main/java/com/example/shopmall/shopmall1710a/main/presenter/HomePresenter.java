package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.framework.base.presenter.IBasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.main.bean.Goods;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HomePresenter extends BasePresenter<Goods> implements IBasePresenter {
    @Override
    protected String getPath() {
        return "/atguigu/json/HOME_URL.json";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<Goods>>() {}.getType();
    }


    @Override
    public void destroy() {

    }

    @Override
    public void postBodyHttpData(int code) {

    }
}
