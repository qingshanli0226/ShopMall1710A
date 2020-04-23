package com.example.shopmall.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SpCache {

    private String name = "token";
    private SharedPreferences sharedPreferences;
    private final String lastOpen = "lastOpen";
    private final String adrTimeName = "adrTime";
    private final String homeData="homeData";
    public SpCache(Context context) {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString(name, token);
        editor.commit();
    }

    public String getTpken() {
        return sharedPreferences.getString(name, "");
    }


    public void saveAdrTime(long time) {
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putLong(adrTimeName, time);
        editor.commit();
    }

    public long getAdrTime() {
        return sharedPreferences.getLong(adrTimeName, -1);
    }
    public void saveHomeData(String data) {
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString(homeData, data);
        editor.commit();
    }

    public String getHomeData() {
        return sharedPreferences.getString(homeData,null);
    }
    public long getlastOpen() {
        return sharedPreferences.getLong(lastOpen, 0);
    }

    public void savetlastOpen(long time) {
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putLong(lastOpen, time);
        editor.commit();
    }
}
