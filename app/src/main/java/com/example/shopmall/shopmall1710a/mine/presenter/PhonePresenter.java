package com.example.shopmall.shopmall1710a.mine.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class PhonePresenter extends BasePresenter<Object> {
    @Override
    protected String getPath() {
        return "updatePhone";
    }

    public void addParam(String phoneNum){
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",phoneNum);
        updateParamsMap(map);
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }
}
