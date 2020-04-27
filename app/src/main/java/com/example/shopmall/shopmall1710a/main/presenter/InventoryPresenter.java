package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class InventoryPresenter extends BasePresenter<String, IBaseView<String>> {


    public InventoryPresenter(IBaseView<String> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "checkOneProductInventory";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addparams(int productId,int productNum){
        HashMap<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productNum",productNum);

        updateParamsMap(map);
    }
}
