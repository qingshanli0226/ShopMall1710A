package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.example.shopmall.common.util.SpUtill;
import com.example.shopmall.framework.services.ShopMallService;

import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    private IHomeDataListener iHomeDataListener;
    private ShopMallService shopMallService;
    public SpUtill spUtill;

    private List<IShopCountRecevedLisener> shopCountRecevedLisenerList = new LinkedList<>();

    private static CacheManager instance;

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    public void registerShopCountListener(IShopCountRecevedLisener listener) {
        if (!shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.add(listener);
        }
    }

    public void unRegisterShopCountListener(IShopCountRecevedLisener listener) {
        if (shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.remove(listener);
        }
    }


    //鍒濆§嬪寲鍑芥暟
    public void init(final Context context) {
        spUtill = new SpUtill(context);
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);

        context.startService(intent);

        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder) service;
                shopMallService = shopMallBinder.getService();
                shopMallService.getHomeData(new ShopMallService.IHomeDataReceiveListener() {
                    @Override
                    public void onHomeDataReceived(String homeJsonStr) {
                        SpUtill.saveHomeData(context, homeJsonStr);
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

        ShopUserManager.getInstance().registerUserLoginListener(new ShopUserManager.IUserLoginListener() {
            @Override
            public void onLoginSuccess() {
                shopMallService.getShopcarCount(SpUtill.getTpken(context), new ShopMallService.IShopcarCountListener() {
                    @Override
                    public void onReceiveCount(int count) {
                        SpUtill.saveShopcarCount(context, count);
                        for (IShopCountRecevedLisener lisener : shopCountRecevedLisenerList) {
                            lisener.onShopcarCountReceived(count);
                        }
                    }
                });
            }

            @Override
            public void onLogoutSuccess() {

            }
        });
    }

    public void addShopcarCount(Context context, int addNum) {
        int sum = SpUtill.getShopcarCount(context) + addNum;
        SpUtill.saveShopcarCount(context, sum);

        for (IShopCountRecevedLisener lisener : shopCountRecevedLisenerList) {
            lisener.onShopcarCountReceived(sum);
        }
    }

    public void saveShopCount(Context context, int count) {
        SpUtill.saveShopcarCount(context, count);
    }

    public int getShopcarCount(Context context) {
        return SpUtill.getShopcarCount(context);
    }

    public interface IShopCountRecevedLisener {
        void onShopcarCountReceived(int conunt);
    }


    public interface IHomeDataListener {
        void onHomeDataReceived(String homeDataJson);
    }

    public void registerIHomeDataListener(IHomeDataListener listener) {
        iHomeDataListener = listener;
    }

    public void unRegisterIHomeDataListener() {
        iHomeDataListener = null;
    }


    public String getHomeData(Context context) {
        return SpUtill.getHomeData(context);
    }

}
