package com.example.shopmall.shopmall1710a.type.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.type.mode.DataBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataPresenter extends BasePresenter<Object> {
    private String url;
    public DataPresenter(String url) {
        this.url = url;
    }

    @Override
    protected String getPath() {
        return "/atguigu/json/"+url;
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<List<DataBean>>>(){}.getType();
    }
}
