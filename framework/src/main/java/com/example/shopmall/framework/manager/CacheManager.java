package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.service.ShopMallService;

public class CacheManager {
    private IHomeDataListener iHomeDataListener;
    private static CacheManager instance;
    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    // 初始化
    public void init(final Context context){
        Log.i("myboss", "init: 注册");
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("myboss", "onServiceConnected: 绑定");
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder) service;
                ShopMallService shopMallService = shopMallBinder.getService();
                shopMallService.getHomeData(new ShopMallService.IHomeDataReceiveListener() {
                    @Override
                    public void onHomeDataReceived(String homeJsonStr) {
                        Log.i("myboss", "onHomeDataReceived: -----------");
                        // 数据请求成功 sp 存储 通知页面刷新
                        SPUtils.getInstance().put(Constant.SP_CACHE_HOME_DATA_INFO,homeJsonStr);
                        if (iHomeDataListener == null){ // 没有注册监听
                            return;
                        }
                        iHomeDataListener.onHomeDataReceived(homeJsonStr);
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        },Context.BIND_AUTO_CREATE);
    }

    //定义接口，通知homeData数据已经获取到。,Manager屏蔽了Service, Activity是直接和Manager进行通信
    public interface IHomeDataListener{
        void onHomeDataReceived(String homeDataJson);
    }

    public void registerIHomeDataListener(IHomeDataListener listener) {
        iHomeDataListener =  listener;
    }
    public void unRegisterIHomeDataListener() {
        iHomeDataListener = null;
    }

    //提供方法，让MainActivity去获取首页数据
    public String getHomeData() {
        return SPUtils.getInstance().getString(Constant.SP_CACHE_HOME_DATA_INFO);
    }
}
