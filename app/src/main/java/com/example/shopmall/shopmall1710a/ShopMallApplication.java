package com.example.shopmall.shopmall1710a;

import android.app.Application;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.net.NetModule;

public class ShopMallApplication extends Application {


    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        NetModule.init(this);
        CacheManager.getInstance().init(this);
        ShopUserManager.getInstance().init(this);
    }
}
