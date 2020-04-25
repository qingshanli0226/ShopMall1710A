package com.example.shopmall.shopmall1710a;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.framework.manager.CacheManager;

public class ShoppingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.getInstance().init(this);
        CacheManager.getInstance().init(this);
    }
}
