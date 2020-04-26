package com.example.shopmall.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.BaseBean;
import com.example.shopmall.BaseObserver;
import com.example.shopmall.RetrofitManager;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.BossBean;
import com.example.shopmall.framework.manager.LoginEntity;
import com.example.shopmall.framework.manager.ShopCarEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.HashMap;

public class ShopMallService extends Service {



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ShopMallBinder();
    }

    public class ShopMallBinder extends Binder {
        public ShopMallService getService() {
            return ShopMallService.this;
        }
    }
    //获取首页数据
    public void getHomeData(final IHomeDataReceiveListener iHomeDataReceiveListener) {
        RetrofitManager.getNetAPIService()
                .getData("atguigu/json/HOME_URL.json", new HashMap<String, Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())//因为获取到数据后，实现数据存储，所以不能切换到主线程
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i("boss", "onNext: next Home 数据获取成功!");
                        //获取到首页数据后，实现数据存储，并且
                        try {
                            iHomeDataReceiveListener.onHomeDataReceived(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("boss", "onError: 数据请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    // 提供 自动登录的功能
    public void autoLogin(String token, final IAutoLoginListener iAutoLoginListener){
        if (SPUtils.getInstance().getString(Constant.SP_TOKEN)!=null){
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",SPUtils.getInstance().getString(Constant.SP_TOKEN));
            RetrofitManager.getNetAPIService()
                    .postData("autoLogin",map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<ResponseBody>(){
                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Log.i("boss", "onNext: next 自动登录成功");
                            try {
                                String string = responseBody.string();
                                Log.i("boss", "onNext: next"+string);

                                BossBean bossBean = JSON.parseObject(string, BossBean.class);
                                if (bossBean == null){
                                        Log.i("boss", "onNext: 空对象");
                                    }else {
                                        Log.i("happy", "onNext: "+bossBean.getCode());
                                        iAutoLoginListener.onAutoLoginSuccess(bossBean);
                                    }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                        }
                    });
        }
    }

    // 提供 获取 购物车 商品数量的方法
    public  void gitShopcarCount(String token,final IShopcarCountListener iShopcarCountListener){
        RetrofitManager.getNetAPIService().getData("getShortcartProducts", new HashMap<String, Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            ShopCarEntity shopCarEntity = new Gson().fromJson(responseBody.string(), new TypeToken<ShopCarEntity>(){}.getType());
                            // 集合的长度 即我购物车数量
                            iShopcarCountListener.onReceiveCount(shopCarEntity.getResult().size());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //获取购物车商品count 的监听
    public interface IShopcarCountListener {
        void onReceiveCount(int count);
    }

    //定义接口，实现获取数据后通知Manager
    public interface IHomeDataReceiveListener{
        void onHomeDataReceived(String homeJsonStr);
    }


    // 自动登录成功!
    public interface IAutoLoginListener {
        void onAutoLoginSuccess(BossBean bossBean);
    }
}
