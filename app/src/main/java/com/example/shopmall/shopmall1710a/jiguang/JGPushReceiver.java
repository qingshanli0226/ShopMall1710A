package com.example.shopmall.shopmall1710a.jiguang;

import android.content.Context;
import android.util.Log;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.message.Constant;
import com.example.shopmall.framework.message.ShopMallMessage;
import org.json.JSONException;
import org.json.JSONObject;

//获取极光推送的消息类
public class JGPushReceiver extends JPushMessageReceiver {

    //获取通知信息
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);

        Log.d("LQS", notificationMessage.notificationTitle);
        Log.d("LQS", notificationMessage.notificationContent);
        Log.d("LQS", notificationMessage.notificationBigText);
    }

    //获取用户自定义消息
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);

        final ShopMallMessage shopMallMessage = new ShopMallMessage();
        Log.d("LQS", customMessage.toString());
        try {
            JSONObject jsonObject = new JSONObject(customMessage.extra);
            Log.d("LQS", jsonObject.getString("productId"));
            Log.d("LQS", String.valueOf(jsonObject.getDouble("productPrice")));

            //基础消息添加
            shopMallMessage.setIsRead(false);
            shopMallMessage.setType(Constant.MESSAGE_TYPE_PUSH);
            shopMallMessage.setContent(customMessage.message);
            shopMallMessage.setTitle(customMessage.title);
            shopMallMessage.setTime(System.currentTimeMillis());
            //推送消息的字段
            shopMallMessage.setProductId(jsonObject.getString("productId"));
            shopMallMessage.setProductName(jsonObject.getString("productName"));
            shopMallMessage.setProductPrice(jsonObject.getString("productPrice"));
            shopMallMessage.setProductImageUrl(jsonObject.getString("productImageUrl"));

            CacheManager.getInstance().executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //向消息数据库中添加一条PUSH消息
                    //对数据库的操作需要放到子线程中.
                    CacheManager.getInstance().addShopMessage(shopMallMessage);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
