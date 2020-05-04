package com.example.shopmall.buy.presenter;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class RemoveManyProductPresenter extends BasePresenter<String, IBaseView<String>> {
    public RemoveManyProductPresenter(IBaseView<String> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "removeManyProduct";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addParams(List<ShortCarEntity.ResultBean> list){
        JSONArray jsonArray = new JSONArray();
        for (ShortCarEntity.ResultBean resultBean : list) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId",resultBean.getProductId());
                jsonObject.put("productNum",resultBean.getProductNum());
                jsonObject.put("productName",resultBean.getProductName());
                jsonObject.put("url",resultBean.getUrl());
                jsonObject.put("productPrice",resultBean.getProductPrice());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        Log.i("boss", "addParams: "+jsonArray.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),jsonArray.toString());
        setRequestBody(body);
    }
}
