package com.example.shopmall.framework.manager;

import android.app.Activity;

import io.reactivex.annotations.NonNull;

//单例，定义各个模块的接口，并且维护实现接口的实例,并且提供方法，让其他模块调用实现这些接口的实例.
public class ShopServiceManager {

    private IAppServieInterface iAppServieInterface;//维护实现该接口的实例

    private static ShopServiceManager instance;
    private ShopServiceManager() {
    }

    public static ShopServiceManager getInstance() {
        if (instance == null) {
            instance = new ShopServiceManager();
        }
        return instance;
    }

    //注册实现app interface 的实例
    public void registerAppServiceInterface(@NonNull IAppServieInterface iAppServieInterface) {
        this.iAppServieInterface = iAppServieInterface;
    }
    //获取实现App模块接口的实例
    public IAppServieInterface getAppServieInterface() {
        return iAppServieInterface;
    }

    //定义App 模块实现的接口
    public interface IAppServieInterface {
        //显示MainActivity，并且跳转到对应的Fragment。index定义跳转到那个Fragment.
        void openMainActivity(Activity activity, int index);
    }










}
