package com.example.shopmall.shopmall1710a.register.presenter;



import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.shopmall1710a.register.mode.KSRegisterBean;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class KSRegisterPresenter extends BasePresenter<KSRegisterBean> {
    @Override
    protected String getPath() {
        return "videouser/register";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<KSRegisterBean>(){}.getType();
    }

    public void addParmas(String name, String password) {
        //添加签名信息
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phoneNum", name);
            jsonObject.put("userPassWord", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //将参数生成json字符串
        String jsonStr =jsonObject.toString();
        //通过json字符串生成RequestBody
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),jsonStr);
        //把requestBody写入BasePresenter中
        setRequestBody(body);
    }
}
