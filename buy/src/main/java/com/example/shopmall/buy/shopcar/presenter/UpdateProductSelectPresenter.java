package com.example.shopmall.buy.shopcar.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.framework.entity.ShopCartBean;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UpdateProductSelectPresenter extends BasePresenter<String> {
    @Override
    protected String getPath() {
        return "updateProductSelected";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addParams(ShopCartBean.ResultBean shopcarData, boolean productSelected) {
        JSONObject object = new JSONObject();
        try {
            object.put("productId", shopcarData.getProductId());
            object.put("productNum", shopcarData.getProductNum());
            object.put("productName", shopcarData.getProductName());
            object.put("productPrice", shopcarData.getProductPrice());
            object.put("productSelected",productSelected);
            object.put("url", shopcarData.getUrl());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //因为是json的方式，向服务端传递参数，所以生成requestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        setRequestBody(requestBody);
    }
}
