package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.framework.base.presenter.IBasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.main.bean.TypeEntity;
import com.example.shopmall.shopmall1710a.main.view.fragment.TypeFrag;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TypePresenter extends BasePresenter<TypeEntity> implements IBasePresenter {
    private List<String> list = new ArrayList<>();
    private int flag = 0;

    @Override
    protected String getPath() {
        list = TypeFrag.slist;
        flag = TypeFrag.num;
        return list.get(flag);
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<TypeEntity>>() {}.getType();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void postBodyHttpData(int code) {

    }
}
