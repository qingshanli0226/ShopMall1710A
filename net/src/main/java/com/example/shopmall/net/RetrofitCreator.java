package com.example.shopmall.net;


import com.example.shopmall.common.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitCreator {

    private static NetApiInterface netApiInterface;

    //全局的网络框架
    public static NetApiInterface getNetAPIService () {
        if (netApiInterface == null) {
            netApiInterface = createNetApiService();
        }
        return netApiInterface;
    }

    private static NetApiInterface createNetApiService() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS) //设置超时
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())//添加自定义token拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        String baseUrl = Constant.IS_KS ? Constant.BASE_URL_KS : Constant.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//Gson解析器的创建工厂类
                .build();

        netApiInterface = retrofit.create(NetApiInterface.class);

        return netApiInterface;
    }

}
