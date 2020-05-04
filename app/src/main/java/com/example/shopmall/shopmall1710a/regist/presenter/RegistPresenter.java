package com.example.shopmall.shopmall1710a.regist.presenter;




import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.TreeMap;

public class RegistPresenter extends BasePresenter<String> {
    @Override
    protected String getPath() {
        return "register";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }
    public void addParmas(String name, String password) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("name", name);
        params.put("password", password);
        updateParamsMap(params);//使用编码后的参数发起网络请求
    }
}
