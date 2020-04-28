package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.bean.ShopCartBean;
import com.example.shopmall.framework.service.ShopMallService;

import java.util.LinkedList;
import java.util.List;

//单例，负责整个应用的存储功能
public class CacheManager {
    private IHomeDataListener iHomeDataListener;
    private ShopMallService shopMallService;
    private ShopCartBean shopCartBean;

    private List<IShopcarDataRecevedLisener> shopCountRecevedLisenerList = new LinkedList<>();

    private static CacheManager instance;
    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    public void registerShopCountListener(IShopcarDataRecevedLisener listener) {
        if (!shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.add(listener);
        }
    }

    public void unRegisterShopCountListener(IShopcarDataRecevedLisener listener) {
        if (shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.remove(listener);
        }
    }

    public ShopCartBean getShopCartBean() {
        return shopCartBean;
    }


    //初始化函数
    public void init(final Context context) {
                //在初始化方法里去启动并且绑定service
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
                        SpUtil.saveHomeData(context, homeJsonStr);
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

        //监听用户登录的状态，当登录成功后，获取购物车产品数量，并且把数量存储起来,多个页面可以获取这个数量
        ShopUserManager.getInstance().registerUserLoginListener(new ShopUserManager.IUserLoginListener() {
            @Override
            public void onLoginSuccess() {//登录成功后，获取购物车数据。
                shopMallService.getShopcarCount(SpUtil.getTpken(context), new ShopMallService.IShopcarDataListener() {
                    @Override
                    public void onReceiveShopcarData(int count, ShopCartBean shopCartBeanResult) {
                        SpUtil.saveShopcarCount(context, count);//把购物车的产品数量存起来
                        shopCartBean = shopCartBeanResult;
                        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
                            lisener.onShopcarDataReceived(count,shopCartBean);
                        }
                    }
                });
            }

            @Override
            public void onLogoutSuccess() {

            }
        });
    }

    public void addNewShopcardata(Context context, int addNum, ShopCartBean.ShopcarData shopcarData) {
        //更新缓存的数据
        int sum = SpUtil.getShopcarCount(context) + addNum;
        SpUtil.saveShopcarCount(context, sum);
        shopCartBean.getResult().add(shopcarData);

        //去通知UI刷新数据
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(sum,shopCartBean);
        }
    }

    public void updateShopcarProductNum(Context context,String productId, ShopCartBean.ShopcarData newShopcarData) {
        for(ShopCartBean.ShopcarData shopcarData:CacheManager.getInstance().getShopCartBean().getResult()) {
            if (productId.equals(shopcarData.getProductId())) {
                shopcarData.setProductNum(newShopcarData.getProductNum());
            }
        }
        //去通知UI刷新数据
        int sum = SpUtil.getShopcarCount(context);
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(sum,shopCartBean);
        }
    }

    public void saveShopCount(Context context, int count) {
        SpUtil.saveShopcarCount(context, count);
    }

//获取购物车产品数量
    public int getShopcarCount(Context context) {
        return SpUtil.getShopcarCount(context);
    }

    public interface IShopcarDataRecevedLisener {
        void onShopcarDataReceived(int conunt, ShopCartBean shopCartBean);
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
    public String getHomeData(Context context) {
        return SpUtil.getHomeData(context);
    }
}
