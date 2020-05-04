package com.example.shopmall.buy.presenter;

import com.example.shopmall.BaseBean;
import com.example.shopmall.buy.entity.OrderEntity;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONArray;

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
        JSONArray jsonArray = new JSONArray();
        for (ShortCarEntity.ResultBean resultBean : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("productId",resultBean.getProductId());
            jsonObject.addProperty("productNum",resultBean.getProductNum());
            jsonObject.addProperty("productName",resultBean.getProductName());
            jsonArray.put(jsonObject);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("subject","buy");
        jsonObject.addProperty("totalPrice",totalPrice);
        jsonObject.addProperty("body",jsonArray.toString());

        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=UTF-8"),jsonObject.toString());
        setRequestBody(body);
    }
}
