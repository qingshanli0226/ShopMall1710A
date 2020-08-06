package com.example.shopmall.buy.shopcar.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ConfirmResultPresenter extends BasePresenter<Boolean> {
    @Override
    protected String getPath() {
        return "confirmServerPayResult";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<Boolean>>(){}.getType();
    }

    public void addParams(String outTradeNo,String result) {
        JSONObject object = new JSONObject();
        try {
            object.put("outTradeNo", outTradeNo);
            object.put("result", result);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //因为是json的方式，向服务端传递参数，所以生成requestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        setRequestBody(requestBody);
    }
}
