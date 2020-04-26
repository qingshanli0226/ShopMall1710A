package com.example.shopmall.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SpCache {

    private String name = "token";
    private SharedPreferences sharedPreferences;
    private final String adrTimeName = "adrTime";
    private final String homeData = "homeData";

    public SpCache(Context context) {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void saveAdrTime(long time) {
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putLong(adrTimeName, time);
        editor.commit();
    }

    public long getAdrTime() {
        return sharedPreferences.getLong(adrTimeName, 0);
    }

    public void saveHomeData(String data) {
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString(homeData, data);
        editor.commit();
    }

    public String getHomeData() {
        return sharedPreferences.getString(homeData, null);
    }


}
