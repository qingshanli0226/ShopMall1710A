package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.example.shopmall.common.util.SpUtils;
import com.example.shopmall.framework.services.ShopMallService;

public class CacheManager {

    private SpUtils spUtils;

    private IHomeDataListener iHomeDataListener;

    private static CacheManager instances;

    private CacheManager() {

    }

    public static CacheManager getInstance() {
        if (instances == null) {
            instances = new CacheManager();
        }
        return instances;
    }

    public void init(Context context) {
        spUtils = new SpUtils(context);

        //绑定service
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder) iBinder;
                ShopMallService shopMallService = shopMallBinder.getServices();
                shopMallService.getHomeData(new ShopMallService.IHomeDataReceiveListener() {
                    @Override
                    public void onHomwDataReceived(String homeJsonStr) {
                        //数据获取成功
                        //存储数据
                        spUtils.saveHomeData(homeJsonStr);
                        //通知刷新
                        iHomeDataListener.onHomeDataReceived(homeJsonStr);
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Context.BIND_AUTO_CREATE);


    }

    public interface IHomeDataListener {
        void onHomeDataReceived(String homeDataJson);
    }

    public void registerHomeDataListener(IHomeDataListener listener) {
        iHomeDataListener = listener;
    }

    public void unRegisterHomeDataListener(IHomeDataListener listener) {
        iHomeDataListener = null;
    }


    //提供方法获取数据
    public String getHomeData() {
        return spUtils.getHomeData();
    }

}
