package com.example.shopmall.framework.manager;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

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
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(app);
    }


}
