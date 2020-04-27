package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.framework.base.presenter.IBasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.main.bean.AddBean;
import com.example.shopmall.shopmall1710a.main.bean.GoodsBean;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class AddShopCartPresenter extends BasePresenter<GoodsBean> implements IBasePresenter {
    @Override
    protected String getPath() {
        return "addOneProduct";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<AddBean>() {
        }.getType();
    }

    public void addParams(String productId, String productName, String imageUrl,String productPrice) {
        JSONObject object = new JSONObject();
        try {
            object.put("productId", productId);
            object.put("productNum", 1);
            object.put("productName", productName);
            object.put("productPrice", productPrice);
            object.put("productSelected",true);
            object.put("url", imageUrl);

//

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        setRequestBody(requestBody);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void postBodyHttpData(int code) {

    }
}
