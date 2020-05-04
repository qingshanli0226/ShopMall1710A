package com.example.shopmall.buy.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.buy.entity.OrderEntity;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class OrderPresenter extends BasePresenter<OrderEntity, IBaseView<OrderEntity>> {
    public OrderPresenter(IBaseView<OrderEntity> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "getOrderInfo";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<OrderEntity>>(){}.getType();
    }

    public void addParames(String totalPrice, List<ShortCarEntity.ResultBean> list){
        Gson gson = new Gson();
        JSONArray jsonArray = new JSONArray();
        for (ShortCarEntity.ResultBean resultBean : list) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId",resultBean.getProductId());
                jsonObject.put("productNum",resultBean.getProductNum());
                jsonObject.put("productName",resultBean.getProductName());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonArray.put(jsonObject);
            jsonObject = null;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("subject","buy");
            jsonObject.put("totalPrice",totalPrice);
            jsonObject.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=UTF-8"),jsonObject.toString());
        setRequestBody(body);
    }
}
