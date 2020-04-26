package com.example.shopmall.framework.manager;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.BaseBean;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.service.ShopMallService;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class ShopUserManager {
    private static ShopUserManager userManager;
    // 存储 监听(方便销毁)
    private List<IUserInfoListener> userInfoListeners = new LinkedList<>();

    private ShopUserManager(){}

    public static ShopUserManager getInstance(){
        if (userManager == null){
            userManager = new ShopUserManager();
        }
        return userManager;
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
                String token = SPUtils.getInstance().getString(Constant.SP_TOKEN);
                Log.i("boss", "getHomeData: token"+token);
                if (token!=null) // 如果token 不为空 调用自动登录的方法
                    shopMallService.autoLogin(token, new ShopMallService.IAutoLoginListener() {
                        @Override
                        public void onAutoLoginSuccess(BossBean bossBean) {
                            SPUtils.getInstance().put(Constant.SP_TOKEN,bossBean.getResult().getToken()); // 存储 token
                                for (IUserInfoListener userInfoListener : userInfoListeners) {
                                    userInfoListener.onLoginSuccess(); // 通知登录成功
                                }
                        }
                    });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }




    // 判断用户登录状态
    public boolean isLogin(){
        return SPUtils.getInstance().contains(Constant.SP_USER_LOGIN_INFO);
    }
    // 退出登录
    public void loginOut(){
        SPUtils.getInstance().remove(Constant.SP_USER_LOGIN_INFO);
    }
    // 获取用户信息
    public LoginEntity getUserInfo(){
        return  new Gson()
                .fromJson(SPUtils.getInstance().getString(Constant.SP_USER_LOGIN_INFO),LoginEntity.class);
    }
    // sp存储用户信息
    public void setUserInfo(LoginEntity userInfo){
        SPUtils.getInstance().put(Constant.SP_USER_LOGIN_INFO
                ,new Gson().toJson(userInfo));
    }

    //通知用户登录成功 <存在问题>
    public void notifyUseLoginSuccess() {
        for (IUserInfoListener listener:userInfoListeners) {
            listener.onLoginSuccess();
        }
    }

    //通知用户，用户退出登录
    public void notifyUserLogout() {
        for(IUserInfoListener listener:userInfoListeners) {
            listener.onLogout();
        }
    }

    //注册接口。如果页面想监听当前登录状态，可以注册一个接口
    public void registerListener(IUserInfoListener listener) {
        if (!userInfoListeners.contains(listener)) {
            userInfoListeners.add(listener);
        }
    }

    //注销接口,防止内存泄漏
    public void unRegisterListener(IUserInfoListener listener) {
        if (userInfoListeners.contains(listener)) {
            userInfoListeners.remove(listener);
        }
    }

    //定义一个接口，去通知页面当前用户登录状态的改变
    public interface IUserInfoListener{
        void onLoginSuccess();//用户登录成功
        void onLogout();//用户退出登录
    }

}
