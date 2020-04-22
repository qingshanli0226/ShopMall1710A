package com.example.shopmall;

import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.common.Constant;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

//定义添加token的拦截器，
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request.newBuilder().addHeader("token", SPUtils.getInstance().getString(Constant.SP_TOKEN))
                .addHeader("appversoin", "v1.0").build();
        return chain.proceed(chain.request());
    }
}
