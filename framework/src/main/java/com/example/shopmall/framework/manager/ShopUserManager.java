package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.example.shopmall.common.util.SpUtill;
import com.example.shopmall.framework.base.bean.LoginBean;
import com.example.shopmall.framework.services.ShopMallService;

import java.util.LinkedList;
import java.util.List;

public class ShopUserManager {

    private static ShopUserManager manager;
    private LoginBean resultBean;

    private List<IUserLoginListener> userLoginListenerList = new LinkedList<>();

    private ShopUserManager() {

    }

    public static ShopUserManager getInstance() {
        if (manager == null) {
            manager = new ShopUserManager();
        }
        return manager;
    }

    public void init(final Context context) {

        //绑定service
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder) iBinder;
                ShopMallService shopMallService = shopMallBinder.getService();
                String token = SpUtill.getTpken(context);
                if (token != null) {
                    shopMallService.autoLogin(token, new ShopMallService.IAutoLoginListener() {
                        @Override
                        public void onAutoLoginSuccess(LoginBean loginBean) {
                            resultBean = loginBean;

                            SpUtill.saveToken(context, loginBean.getToken());
                            for (IUserLoginListener loginListener : userLoginListenerList) {
                                loginListener.onLoginSuccess();
                            }
                        }
                    });
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Context.BIND_AUTO_CREATE);


    }

    public void saveUserLoginBeanAndNotify(LoginBean resultBean) {
        this.resultBean = resultBean;
        for (IUserLoginListener listener : userLoginListenerList) {
            listener.onLoginSuccess();
        }
    }

    public boolean isUserLogin() {
        if (resultBean != null) {
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


    public interface IUserLoginListener {
        void onLoginSuccess();

        void onLogoutSuccess();
    }
    public void saveToken(Context context,String token) {
        SpUtill.saveToken(context, token);
    }

    public String getToken(Context context) {
        return SpUtill.getTpken(context);
    }

}
