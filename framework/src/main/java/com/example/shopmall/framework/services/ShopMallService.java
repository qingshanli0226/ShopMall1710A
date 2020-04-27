package com.example.shopmall.framework.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.example.shopmall.framework.base.bean.LoginBean;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.net.RetrofitCreator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

    public void getHomeData(final IHomeDataReceiveListener iHomeDataReceiveListener) {
        String path = "atguigu/json/HOME_URL.json";
        RetrofitCreator.getNetAPIService()
                .getData(path, new HashMap<String, String>())
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
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

    public void autoLogin(String token, @NonNull final IAutoLoginListener iAutoLoginListener) {
        String path = "autoLogin";
        HashMap<String, String> paramsMap = new HashMap<>();
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
                        Type type = new TypeToken<LoginBean>() {
                        }.getType();
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

    public void getShopcarCount(String token, final IShopcarCountListener iShopcarCountListener) {
        String path = "getShortcartProducts";

        RetrofitCreator.getNetAPIService().getData(path, new HashMap<String, String>())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Type type = new TypeToken<ShopCartBean>() {
                        }.getType();
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


    public interface IHomeDataReceiveListener {
        void onHomeDataReceived(String homeJsonStr);
    }

    public interface IAutoLoginListener {
        void onAutoLoginSuccess(LoginBean loginBean);
    }


}
