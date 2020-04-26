package com.example.shopmall.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import com.example.shopmall.framework.entity.LoginBean;
import com.example.shopmall.framework.entity.ShopCartBean;
import com.example.shopmall.net.RetrofitCreator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

//负责后台任务,例如换出的处理,保活功能
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
        String path = "atguigu/json/HOME_URL.json";
        RetrofitCreator.getNetAPIService()
                .getData(path, new HashMap<String, String>())
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())//因为获取到数据后，实现数据存储，所以不能切换到主线程
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //获取到首页数据后，实现数据存储，并且
                        try {
                            iHomeDataReceiveListener.onHomeDataReceived(responseBody.string());
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

    //自动登录功能
    public void autoLogin(String token, @NonNull final IAutoLoginListener iAutoLoginListener) {
        String path = "autoLogin";
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("token", token);

        RetrofitCreator.getNetAPIService().postData(path, paramsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Type type  =  new TypeToken<LoginBean>(){}.getType();
                        try {
                            LoginBean loginBean = new Gson().fromJson(responseBody.string(), type);
                            iAutoLoginListener.onAutoLoginSuccess(loginBean);
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
    //获取服务端购物车产品信息的接口
    public void getShopcarCount(String token, final IShopcarCountListener iShopcarCountListener) {
        String path = "getShortcartProducts";
        //Header中的token在拦截器中添加的
        RetrofitCreator.getNetAPIService()
                .getData(path, new HashMap<String, String>())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Type type  =  new TypeToken<ShopCartBean>(){}.getType();
                        try {
                            ShopCartBean shopCartBean = new Gson().fromJson(responseBody.string(), type);
                            iShopcarCountListener.onReceiveCount(shopCartBean.getResult().size());
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



    public interface IShopcarCountListener {
        void onReceiveCount(int count);
    }

    //定义接口，实现获取数据后通知Manager
    public interface IHomeDataReceiveListener{
        void onHomeDataReceived(String homeJsonStr);
    }

    public interface IAutoLoginListener {
        void onAutoLoginSuccess(LoginBean loginBean);
        //失败了，暂时不用处理，下次点击购物车，会弹出登录界面，让用户登录
    }
}
