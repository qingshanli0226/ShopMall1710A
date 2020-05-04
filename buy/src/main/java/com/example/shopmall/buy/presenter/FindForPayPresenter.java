package com.example.shopmall.buy.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.buy.entity.NoPayEntity;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FindForPayPresenter extends BasePresenter<NoPayEntity, IBaseView<NoPayEntity>> {
    public FindForPayPresenter(IBaseView<NoPayEntity> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "findForPay";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<List<NoPayEntity>>>(){}.getType();
    }

}
