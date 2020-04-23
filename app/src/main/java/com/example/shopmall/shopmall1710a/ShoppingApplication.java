package com.example.shopmall.shopmall1710a;

import android.app.Application;
import com.example.shopmall.framework.manager.AppCore;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.getInstance().init(this);
    }
}
