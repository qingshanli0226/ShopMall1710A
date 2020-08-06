package com.example.shopmall.buy.shopcar.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.framework.entity.ShopCartBean;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RemoveProductsPresenter extends BasePresenter<String> {
    @Override
    protected String getPath() {
        return "removeManyProduct";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }


    public void addParams(List<ShopCartBean.ResultBean> shopcarDataList) {
        JSONArray array = new JSONArray();
        for(int i = 0; i < shopcarDataList.size(); i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("productId", shopcarDataList.get(i).getProductId());
                object.put("productNum", shopcarDataList.get(i).getProductNum());
                object.put("productName", shopcarDataList.get(i).getProductName());
                object.put("productPrice", shopcarDataList.get(i).getProductPrice());
                object.put("url", shopcarDataList.get(i).getUrl());
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //创建一个requestbody，存放json字符串。
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), array.toString());
        setRequestBody(body);
    }
}
