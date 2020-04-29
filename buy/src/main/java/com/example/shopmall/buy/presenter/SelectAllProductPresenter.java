package com.example.shopmall.buy.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.lang.reflect.Type;

public class SelectAllProductPresenter extends BasePresenter<String, IBaseView<String>> {
    public SelectAllProductPresenter(IBaseView<String> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "selectAllProduct";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addParams(boolean selected){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("selected",selected);
        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=UTF-8"),jsonObject.toString());
        setRequestBody(body);
    }
}
