package com.example.shopmall.shopmall1710a.application;

import android.app.Application;

import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.net.NetModule;

public class MyApplication extends Application {
    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        NetModule.init(this);
        CacheManager.getInstance().init(this);
        ShopUserManager.getInstance().init(this);
    }
}
