package com.example.shopmall.framework;

import android.app.Application;

public class AppCore {

    private static AppCore appCore;

    private Application app;

    public static AppCore getInstance() {
        if (appCore == null) {
            appCore = new AppCore();
        }
        return appCore;
    }

    public Application getApp() {
        return app;
    }
    public void init(Application app) {
        this.app = app;
    }

}
