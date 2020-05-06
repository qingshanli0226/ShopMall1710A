package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.main.bean.OrderInfoBean;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class OrderInfoPresenter extends BasePresenter<OrderInfoBean> {
    @Override
    protected String getPath() {
        return "getOrderInfo";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<OrderInfoBean>>(){}.getType();
    }

    //subject:支付的原因
    //price:支付的价格
    public void addParams(String subject, String totalPrice, List<ShopCartBean.ResultBean> shopcarDataList) {
        JSONArray jsonArray = new JSONArray();
        //用数组来存储购买的产品信息,使用JsonArray，是因为可能购买多个产品
        JSONObject object = new JSONObject();
        for(ShopCartBean.ResultBean shopcarData:shopcarDataList) {
            try {
                object.put("productName", shopcarData.getProductName());
                object.put("productId", shopcarData.getProductId());
                jsonArray.put(object);//把信息添加到列表中
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //存放购买的原因，价格，以及上面生成的产品信息
        JSONObject subjectObject = new JSONObject();
        try {
            subjectObject.put("subject", subject);
            subjectObject.put("totalPrice", totalPrice);
            subjectObject.put("body", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //因为是json的方式，向服务端传递参数，所以生成requestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), subjectObject.toString());
        setRequestBody(requestBody);
    }
    @Override
    public void destroy() {

    }
}
