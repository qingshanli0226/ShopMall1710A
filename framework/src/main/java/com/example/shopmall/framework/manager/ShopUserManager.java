package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.bean.LoginBean;
import com.example.shopmall.framework.service.ShopMallService;

import java.util.LinkedList;
import java.util.List;

//存储用户信息的
public class ShopUserManager {
    private Context applicationContext;
    private static ShopUserManager instance;
    private LoginBean.ResultBean loginBean;
    private ShopMallService shopMallService;

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
        this.applicationContext= context;
        //通过Service完成后台的自动登录功能
        //在初始化方法里去启动并且绑定service
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);

        context.startService(intent);

        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder)service;
                shopMallService = shopMallBinder.getService();
                String token = SpUtil.getTpken(context);
                //判断当前网络连接情况，如果网络没有连接的话，就不会进行自动登录
                if (ConnectManager.getInstance().isConnected() && token!=null) {
                    autoLogin();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

        //注册，监听当前网络连接情况
        ConnectManager.getInstance().registerConnectChangeListener(new ConnectManager.IConnectChangeListener() {
            //注册，监听当前网络连接情况,当网络连接由没有连接转向已连接时，需要实现自动登录功能
            @Override
            public void onConnected() {
                if (shopMallService!=null) {
                    autoLogin();
                }
            }

            //当从连接转向没有连接，将用户登录状态由登录状态变为未登录状态,并且通知UI刷新用户信息,把用户信息清空
            @Override
            public void onDisconnect() {
                loginBean = null;//登录状态改成未登录
                for(IUserLoginListener listener:userLoginListenerList) {
                    listener.onLogout();
                }
            }
        });
    }

    private void autoLogin() {
        String token = SpUtil.getTpken(applicationContext);
        shopMallService.autoLogin(token, new ShopMallService.IAutoLoginListener() {
            @Override
            public void onAutoLoginSuccess(LoginBean loginBean) {
                ShopUserManager.this.loginBean = loginBean.getResult();//在内存中缓存一份loginBean
                SpUtil.saveToken(applicationContext, loginBean.getResult().getToken());//更新，新的token.自动登录后，token会刷新
                for (IUserLoginListener listener : userLoginListenerList) {
                    listener.onLoginSuccess();
                }
            }
        });
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
        void onLogout();
    }

    public void saveToken(Context context,String token) {
        SpUtil.saveToken(context, token);
    }

    public String getToken(Context context) {
        return SpUtil.getTpken(context);
    }
}
