package com.example.shopmall.shopmall1710a.product.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import io.reactivex.annotations.NonNull;

public class RecommendPresenter extends BasePresenter<String> {
    @Override
    protected String getPath() {
        return "checkOneProductInventory";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }
    public void addParms(@NonNull String productId){
        HashMap<String,String> params = new HashMap<>();
        params.put("productId", productId);
        params.put("productNum", "1");

        updateParamsMap(params);
    }
}
