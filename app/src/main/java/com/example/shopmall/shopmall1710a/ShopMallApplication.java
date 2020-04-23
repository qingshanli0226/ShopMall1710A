package com.example.shopmall.shopmall1710a;

import android.app.Application;
import com.example.shopmall.framework.manager.CacheManager;

public class ShopMallApplication extends Application {

    public static Application instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;

        CacheManager.getInstance().init(this);
    }
}
