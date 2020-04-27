package com.example.shopmall.shopmall1710a.app;

import android.app.Application;
import android.content.Context;
import com.example.shopmall.framework.manager.CacheManager;

public class ShopMallApplication extends Application {

    public static Application instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;

        CacheManager.getInstance().init(this);
    }

    // 获取全局上下文
    public static Context getContext() {
        return instances;
    }
}
