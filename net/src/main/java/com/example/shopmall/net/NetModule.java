package com.example.shopmall.net;

import android.app.Application;
import android.content.Context;

public class NetModule {
    public static Application context;
    public static void init(Application applicationContext) {
        context = applicationContext;
    }
}
