package com.example.shopmall.buy.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.lang.reflect.Type;

public class UpdateProductSelectedPresenter extends BasePresenter<String, IBaseView<String>> {
    public UpdateProductSelectedPresenter(IBaseView<String> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "updateProductSelected";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addParams(String productId,boolean productSelected,String productName,String url,String productPrice){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("productId",productId);
        jsonObject.addProperty("productSelected",productSelected);
        jsonObject.addProperty("productName",productName);
        jsonObject.addProperty("url",url);
        jsonObject.addProperty("productPrice",productPrice);

        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=UTF-8"), jsonObject.toString());
        setRequestBody(body);
    }
}
