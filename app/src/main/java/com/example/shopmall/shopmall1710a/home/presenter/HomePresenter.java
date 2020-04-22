package com.example.shopmall.shopmall1710a.home.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.home.model.HomeEntity;
import com.example.shopmall.shopmall1710a.login.mode.BetterLoginBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HomePresenter extends BasePresenter<List<HomeEntity>> {
    @Override
    protected String getPath() {
        return "getRecommend";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<List<HomeEntity>>>(){}.getType();
    }
}
