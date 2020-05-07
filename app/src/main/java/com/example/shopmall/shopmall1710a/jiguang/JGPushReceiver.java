package com.example.shopmall.shopmall1710a.jiguang;

import android.content.Context;
import android.util.Log;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class JGPushReceiver extends JPushMessageReceiver {

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);

        Log.d("LQS", notificationMessage.notificationTitle);
        Log.d("LQS", notificationMessage.notificationContent);
        Log.d("LQS", notificationMessage.notificationBigText);
    }


}
