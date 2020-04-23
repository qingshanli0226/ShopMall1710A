package com.example.shopmall.shopmall1710a;

import android.app.Application;

import com.example.shopmall.framework.manager.CacheManager;

public class MyApplication extends Application {
    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        CacheManager.getInstance().init(this);
    }
}
