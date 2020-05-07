package com.example.shopmall.common.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public static String getTpken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }

    public static void saveAdrTime(Context context,long time) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putLong("advTime", time);
        editor.commit();
    }

    public static long getAdrTime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("advTime", -1);
    }

    public static void saveHomeData(Context context, String data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("homeData", data);
        editor.commit();
    }

    public static String getHomeData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        return sharedPreferences.getString("homeData", null);
    }

    public static void saveShopcarCount(Context context, int count) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putInt("shopcarCount", count);
        editor.commit();
    }
    public static int getShopcarCount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("shopcarCount", -1);
    }

    public static void saveHistoryRecord(Context context, String json) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("historyRecord",json);
        editor.commit();
    }

    public static String getHistoryRecord(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        return sharedPreferences.getString("historyRecord",null);
    }

    public static void saveEmail(Context context, String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("email",email);
        editor.commit();
    }

    public static String getEmile(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("myInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getString("email",null);
    }

    public static void savePhone(Context context, String phone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("phone",phone);
        editor.commit();
    }

    public static String getPhone(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("myInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getString("phone",null);
    }
}
