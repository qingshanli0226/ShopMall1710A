package com.example.shopmall.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.example.shopmall.RetrofitManager;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.io.IOException;
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

    //获取首页数据
    public void getHomeData(final IHomeDataReceiveListener iHomeDataReceiveListener) {
        Log.i("boss", "getHomeData: 获取数据方法被调用");
        String path = "atguigu/json/HOME_URL.json";
        RetrofitManager.getNetAPIService()
                .getData(path, new HashMap<String, Object>())
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())//因为获取到数据后，实现数据存储，所以不能切换到主线程
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i("boss", "onNext: next");
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


    //定义接口，实现获取数据后通知Manager

    public interface IHomeDataReceiveListener{
        void onHomeDataReceived(String homeJsonStr);
    }
}
