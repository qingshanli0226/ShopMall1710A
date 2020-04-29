package com.example.shopmall.buy.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.lang.reflect.Type;

public class UpdateProductNumPresenter extends BasePresenter<String, IBaseView<String>> {

    public UpdateProductNumPresenter(IBaseView<String> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "updateProductNum";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getRawType();
    }

    public void addParame(String productId,int productNum,String productName,String url,String productPrice){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("productId",productId);
        jsonObject.addProperty("productNum",productNum);
        jsonObject.addProperty("productName",productName);
        jsonObject.addProperty("url",url);
        jsonObject.addProperty("productPrice",productPrice);

        RequestBody body  = RequestBody.create(MediaType.get("application/json;charset=UTF-8"),jsonObject.toString());
        setRequestBody(body);
    }
}
