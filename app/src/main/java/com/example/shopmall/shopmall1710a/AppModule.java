package com.example.shopmall.shopmall1710a;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.example.shopmall.framework.manager.ShopServiceManager;
import com.example.shopmall.shopmall1710a.main.MainActivity;

public class AppModule {

    public static ShopServiceManager.IAppServieInterface iAppServieInterface = new ShopServiceManager.IAppServieInterface() {
        @Override
        public void openMainActivity(Activity activity, int index) {
            Intent intent = new Intent();
            intent.putExtra("index", index);
            intent.setClass(activity, MainActivity.class);
            activity.startActivity(intent);
        }
    };
    //初始化方法，注册该接口实现的功能
    public static void init(Context applicationContext) {
        ShopServiceManager.getInstance().registerAppServiceInterface(iAppServieInterface);
    }

}
