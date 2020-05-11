package com.example.shopmall.shopmall1710a;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ConnectManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.net.NetModule;


public class ShopMallApplication extends Application {


    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        AppModule.init(this);
        NetModule.init(this);

        ConnectManager.getInstance().init(this);

        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
