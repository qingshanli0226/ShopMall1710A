package com.example.shopmall.net;

import com.example.shopmall.common.util.SpUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

//定义添加token的拦截器，
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request.newBuilder().addHeader("token", SpUtil.getTpken(NetModule.context)).build();

        return chain.proceed(newRequest);
    }
}
