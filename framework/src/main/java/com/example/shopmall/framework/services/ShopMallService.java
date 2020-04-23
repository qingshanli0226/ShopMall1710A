package com.example.shopmall.framework.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.example.shopmall.net.RetrofitCreator;
import io.reactivex.Observer;
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
        public ShopMallService getServices() {
            return ShopMallService.this;
        }
    }


    public void getHomeData(final IHomeDataReceiveListener listener) {
        String path = "atguigu/json/HOME_URL.json";
        RetrofitCreator.getNetAPIService()
                .getData(path, new HashMap<String, String>())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())//因为获取到数据后存储，不刷新ui，所有不切换的主线程
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //数据存储
                        try {
                            listener.onHomwDataReceived(responseBody.string());
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


    //定义接口  获取到数据后通知manager
    public interface IHomeDataReceiveListener {
        void onHomwDataReceived(String homeJsonStr);
    }

}
