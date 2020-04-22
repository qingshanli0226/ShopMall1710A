package com.example.shopmall.shopmall1710a.register.presenter;



import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.login.mode.BetterLoginBean;
import com.example.shopmall.shopmall1710a.register.mode.RegisterBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.TreeMap;

public class BetterRegisterPresenter extends BasePresenter<RegisterBean> {
    @Override
    protected String getPath() {
        return "register";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<RegisterBean>>(){}.getType();
    }

    public void addParmas(String name, String password) {
        //添加签名信息
        TreeMap<String, String> params = new TreeMap<>();
        params.put("name", name);
        params.put("password", password);
        updateParamsMap(params);//使用编码后的参数发起网络请求
    }
}
