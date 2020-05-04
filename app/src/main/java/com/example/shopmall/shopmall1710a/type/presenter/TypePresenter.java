package com.example.shopmall.shopmall1710a.type.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.type.mode.TypeBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TypePresenter extends BasePresenter<TypeBean> {
    @Override
    protected String getPath() {
        return "getRecommend";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<List<TypeBean>>>(){}.getType();
    }
}
