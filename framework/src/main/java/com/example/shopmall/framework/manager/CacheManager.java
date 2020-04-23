package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.shopmall.common.StringUtil;
import com.example.shopmall.framework.service.ShopMoreService;

public class CacheManager {
    public SpCache spCache;
    private IHomeDataListener iHomeDataListener;
    private static  CacheManager instance;
    private CacheManager(){
    }
    public static CacheManager getInstance(){
        if (instance==null){
            instance=new CacheManager();
        }
        return instance;
    }

    public void init(Context context){
        spCache=new SpCache(context);
        Intent intent = new Intent();
        intent.setClass(context, ShopMoreService.class);

        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                ShopMoreService.ShowMoreBinder showMoreBinder = (ShopMoreService.ShowMoreBinder) iBinder;
                ShopMoreService shopMoreService = showMoreBinder.getServices();
                shopMoreService.getHomeData(new ShopMoreService.IHomeDataReceiveListener() {
                    @Override
                    public void onHomeDataReceived(String json) {
                        spCache.saveHomeData(json);
                        if (iHomeDataListener==null){
                            return;
                        }
                        iHomeDataListener.onHomeData(json);

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        },Context.BIND_AUTO_CREATE);

    }
    public interface IHomeDataListener{
        void onHomeData(String json);
    }
    public void registerIHomeListener(IHomeDataListener iHomeDataListener){
        this.iHomeDataListener=iHomeDataListener;
    }
    public void unRegisterIHomeListener(){
        iHomeDataListener=null;
    }
    public String getHomeData(){
        return spCache.getHomeData();
    }
}
