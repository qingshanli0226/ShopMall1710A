package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.shopmall1710a.main.bean.SearchBean;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class SearchPresenter extends BasePresenter {
    @Override
    protected String getPath() {
        return "search";
    }


    public void addParams(String string) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", string);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        setRequestBody(requestBody);

    }

    @Override
    public Type getBeanType() {
        return new TypeToken<SearchBean>() {
        }.getType();
    }

    @Override
    public void destroy() {

    }
}
