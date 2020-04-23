package com.example.shopmall.framework.manager;

import android.content.Context;
import android.content.Intent;

public class CacheManager {

    private static CacheManager instance;
    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    // 初始化
    public void init(final Context context){
        Intent intent = new Intent();
//        intent.setClass(context,)
    }
}
