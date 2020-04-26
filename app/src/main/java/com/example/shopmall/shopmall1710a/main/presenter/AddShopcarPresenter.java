package com.example.shopmall.shopmall1710a.main.presenter;

import android.util.Log;
import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class AddShopcarPresenter extends BasePresenter<String, IBaseView<String>> {
    public AddShopcarPresenter(IBaseView<String> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "addOneProduct";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();    }


    public void addParams() {
        JSONObject object = new JSONObject();
        try {
            Log.i("boss", "addParams: Id"+ShopUserManager.getInstance().getUserInfo().getId());
            object.put("productId", "2");
            object.put("productNum", 1);
            object.put("productName", "衬衫");
            object.put("productPrice", "20");
            object.put("productSelected",true);
            object.put("url", "http://www.baidu.com");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //因为是json的方式，向服务端传递参数，所以生成requestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        setRequestBody(requestBody);
    }
}

