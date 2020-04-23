package com.example.shopmall.shopmall1710a.login.presenter;

import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.login.mode.BetterLoginBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class BetterLogoutPresenter extends BasePresenter<BetterLoginBean> {
    @Override
    protected String getPath() {
        return "logout";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }
}
