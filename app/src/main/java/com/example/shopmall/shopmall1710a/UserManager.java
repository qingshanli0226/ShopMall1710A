package com.example.shopmall.shopmall1710a;


import com.blankj.utilcode.util.SPUtils;

import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.main.entity.LoginEntity;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private static UserManager userManager;
    // 存储 监听(方便销毁)
    private List<IUserInfoListener> userInfoListeners = new LinkedList<>();

    private UserManager(){}

    public static UserManager getInstance(){
        if (userManager == null){
            userManager = new UserManager();
        }
        return userManager;
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
