package com.example.shopmall.shopmall1710a.fragment.type.persenter;

import com.example.shopmall.common.Constants;
import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TypeSelectPresenter extends BasePresenter<String> {
    private String select =  Constants.SKIRT_URL;
    @Override
    protected String getPath() {
        return select;
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void selectType(String select) {
        this.select = select;
    }
}
