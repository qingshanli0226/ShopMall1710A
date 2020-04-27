package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.example.shopmall.shopmall1710a.main.entity.ShopCarEntity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GetShortCarPresenter extends BasePresenter<List<ShopCarEntity>, IBaseView<List<ShopCarEntity>>> {

    public GetShortCarPresenter(IBaseView<List<ShopCarEntity>> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "getShortcartProducts";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<List<ShopCarEntity>>>(){}.getType();
    }

}
