package com.example.shopmall.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//使用单例来统一管理网络连接
public class ConnectManager {
    private Context applicationContext;

    private static ConnectManager instance;
    private ConnectivityManager connectivityManager;//系统管理网络连接
    private boolean connected;//当前手机的网络连接状态
    private List<IConnectChangeListener> iConnectChangeListenerList = new LinkedList<>();

    private ConnectManager() {
    }

    public static ConnectManager getInstance() {
        if (instance == null) {
            instance = new ConnectManager();
        }
        return instance;
    }

    public void init(Context applicationContext) {
        this.applicationContext = applicationContext;
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();//需要设置当前网络连接状况的权限
        if (networkInfo!=null&&networkInfo.isConnected()) {//判断当前手机的网络是否正在连接
            setConnected(true);
        } else {
            setConnected(false);
        }


        //注册一个广播，监听当前网络连接的变化
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        applicationContext.registerReceiver(connectReceiver, intentFilter);
    }


    private BroadcastReceiver connectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                return;
            }

            //检测到当前手机网络连接状态改变了，立刻去获取改变后的网络连接状态
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();//需要设置当前网络连接状况的权限
            if (networkInfo!=null&&networkInfo.isConnected()) {//判断当前手机的网络是否正在连接
                setConnected(true);
            } else {
                setConnected(false);
            }
            notifyConnectChanged();//通知监听者，当前网络连接状态已经改变,并且把网络连接的情况的值也传递给监听者.
        }
    };

    private void notifyConnectChanged() {
         for(IConnectChangeListener listener:iConnectChangeListenerList) {
             if (isConnected()) {
                 listener.onConnected();
             } else {
                 listener.onDisconnect();
             }
         }
    }


    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void registerConnectChangeListener(IConnectChangeListener listener) {
        if (!iConnectChangeListenerList.contains(listener)) {
            iConnectChangeListenerList.add(listener);
        }
    }

    public void unRegisterConnectChangeListener(IConnectChangeListener listener) {
        if (iConnectChangeListenerList.contains(listener)) {
            iConnectChangeListenerList.remove(listener);
        }
    }

    public interface IConnectChangeListener {
        void onConnected();
        void onDisconnect();
    }
}
