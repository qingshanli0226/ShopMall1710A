package com.example.shopmall.shopmall1710a.product.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UpdateProductNumPresenter extends BasePresenter<String> {
    @Override
    protected String getPath() {
        return "updateProductNum";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addParams(String productId, String productName, String imageUrl,String productPrice, String productNum) {
        JSONObject object = new JSONObject();
        try {
            object.put("productId", productId);
            object.put("productNum", 1);
            object.put("productName", productName);
            object.put("productPrice", productPrice);
            object.put("productSelected",true);
            object.put("url", imageUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //因为是json的方式，向服务端传递参数，所以生成requestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        setRequestBody(requestBody);
    }
}
