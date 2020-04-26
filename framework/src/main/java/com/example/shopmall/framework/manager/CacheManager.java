package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.BaseBean;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.service.ShopMallService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    private IHomeDataListener iHomeDataListener;
    private ShopMallService shopMallService;
    private static CacheManager instance;
    private List<IShopCountRecevedLisener> shopCountRecevedLisenerList = new LinkedList<>();

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }
    public void init(final Context context) {        //在初始化方法里去启动并且绑定service
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);

        context.startService(intent);

        context.bindService(intent, new ServiceConnection() {
            @Override
             public void onServiceConnected(ComponentName name, IBinder service) {
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder)service;
                shopMallService = shopMallBinder.getService();
                shopMallService.getHomeData(new ShopMallService.IHomeDataReceiveListener() {
                    @Override
                    public void onHomeDataReceived(String homeJsonStr) {
                        //代表数据已经返回
                        //1, 存储数据 2,通知MainActivity去刷新首页数据
                        SPUtils.getInstance().put(Constant.SP_CACHE_HOME_DATA_INFO,homeJsonStr);
                        if (iHomeDataListener == null) {
                            return;
                        }
                       iHomeDataListener.onHomeDataReceived(homeJsonStr);
                    }

                });

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);


        // 注册 监听 (登录状态)
        ShopUserManager.getInstance().registerListener(new ShopUserManager.IUserInfoListener() {
            @Override
            public void onLoginSuccess() { // 登录成功
                // 获取 购物车数据(数量)
                shopMallService.gitShopcarCount(SPUtils.getInstance().getString(Constant.SP_TOKEN), new ShopMallService.IShopcarCountListener() {
                    @Override
                    public void onReceiveCount(int count) {
                        Log.i("boss", "onReceiveCount: 数量"+count);
                        SPUtils.getInstance().put(Constant.SP_SHOP_COUNT,count);
                        for(IShopCountRecevedLisener lisener:shopCountRecevedLisenerList) { // 通知监听页面 跟新购物车数据
                            lisener.onShopcarCountReceived(count);
                        }
                    }
                });
            }

            @Override
            public void onLogout() {

            }
        });
    }

    // 提供方法 获取 购物车数据
    public int getShopcarCount() {
        return Integer.parseInt(SPUtils.getInstance().getString(Constant.SP_SHOP_COUNT));
    }

    public interface IShopCountRecevedLisener {
        void onShopcarCountReceived(int conunt);
    }

    public void registerCountLisenner(IShopCountRecevedLisener iShopCountRecevedLisener){
        shopCountRecevedLisenerList.add(iShopCountRecevedLisener);
    }


    //定义接口，通知homeData数据已经获取到。,Manager屏蔽了Service, Activity是直接和Manager进行通信
    public interface IHomeDataListener{
        void onHomeDataReceived(String homeDataJson);
    }

    public void registerIHomeDataListener(IHomeDataListener listener) {
       this.iHomeDataListener = listener;
    }
    public void unRegisterIHomeDataListener() {
        iHomeDataListener = null;
    }


    //提供方法，让MainActivity去获取首页数据
    public String getHomeData() {
        return SPUtils.getInstance().getString(Constant.SP_CACHE_HOME_DATA_INFO);
    }
}
