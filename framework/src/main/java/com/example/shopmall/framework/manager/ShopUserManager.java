package com.example.shopmall.framework.manager;



import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.entity.LoginBean;
import com.example.shopmall.framework.service.ShopMallService;

import java.util.LinkedList;
import java.util.List;

//存储用户信息的
public class ShopUserManager {

    private static ShopUserManager instance;
    private LoginBean.ResultBean loginBean;
    //维护一个监听用户是否登录成功的链表。因为有多个模块监听当前用户的登录状态
    private List<IUserLoginListener> userLoginListenerList = new LinkedList<>();
    private ShopUserManager() {

    }
    public static ShopUserManager getInstance() {
        if (instance == null) {
            instance = new ShopUserManager();
        }
        return instance;
    }

    //初始化方法
    public void init(final Context context) {
        //通过Service完成后台的自动登录功能
        //在初始化方法里去启动并且绑定service
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder)service;
                ShopMallService shopMallService = shopMallBinder.getService();
                String token = SpUtil.getToken(context);
                if (token!=null)
                shopMallService.autoLogin(token, new ShopMallService.IAutoLoginListener() {
                    @Override
                    public void onAutoLoginSuccess(LoginBean loginBean) {
                        ShopUserManager.this.loginBean = loginBean.getResult();//在内存中缓存一份loginBean
                        SpUtil.saveToken(context, loginBean.getResult().getToken());//更新，新的token.自动登录后，token会刷新
                        for (IUserLoginListener listener:userLoginListenerList) {
                            listener.onLoginSuccess();
                        }
                    }
                });

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    //提供接口，存储用户登录信息
    public void saveUserLoginBeanAndNotify(LoginBean.ResultBean resultBean) {
        this.loginBean = resultBean;
        for (IUserLoginListener listener:userLoginListenerList) {
            listener.onLoginSuccess();
        }
    }

    //判断用户是否登录成功
    public boolean isUserLogin() {
        if (loginBean!=null) {
            return true;
        }
        return false;
    }

    public void registerUserLoginListener(IUserLoginListener listener) {
        if (!userLoginListenerList.contains(listener)) {
            userLoginListenerList.add(listener);
        }
    }

    public void unRegisterUserLoginListener(IUserLoginListener listener) {
        if (userLoginListenerList.contains(listener)) {
            userLoginListenerList.remove(listener);
        }
    }


    //定义接口，其他模块可以去监听，当前用户是否登录成功，或者退出登录
    public interface IUserLoginListener{
        void onLoginSuccess();
        void onLogoutSuccess();
    }
}
