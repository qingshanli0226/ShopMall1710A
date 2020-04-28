package com.example.shopmall.buy.presenter;



import com.example.shopmall.BaseBean;
import com.example.shopmall.buy.entity.ShopCarEntity;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
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
