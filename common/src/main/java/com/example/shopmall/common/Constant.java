package com.example.shopmall.common;

import com.blankj.utilcode.util.SPUtils;

public class Constant {
    //-----------------------Error-------------------------------
    public static final int HTTP_REQUEST_ERROR = -1;
    public static final String HTTP_REQUEST_ERROR_MESSAGE = "网络错误, 请检查网络情况";//403
    public static final int JSON_PARSE_ERROR = -2;
    public static final String JSON_PARSE_ERROR_MESSAGE = "Json数据解析错误";
    public static final int NULL_PARSE_ERROR = -3;
    public static final String NULL_PARSE_ERROR_MESSAGE = "空数据错误";
    public static final int REGISTER_ERROR = -4;
    public static final String REGISTER_ERROR_MESSAGE = "注册错误";

    //-----------------------BaseUrl--------------------------------
    public static final String BASE_URL = "http://49.233.93.155:8080";
    public static final String BASE_IMG = "http://49.233.93.155:8080/atguigu/img";


    //-----------------------SP-------------------------------------
    public static final String SP_TOKEN = "token";
    public static final String SP_USER_LOGIN_INFO = "user_msg"; // 登录
    public static final String SP_CACHE_HOME_DATA_INFO = "cache_home_data"; // 缓存首页数据
    public static final String SP_SHOP_COUNT = "count"; // 购物车数量
    public static final String SP_TOTAL_MONEY = "money"; // 总金额

    //----------------------ARouter Path-----------------------
    // Activity
    public static final String ROUTER_PATH_MAIN_ACTIVITY = "/app_module/MainActivity";
    public static final String ROUTER_PATH_DETAILS_PAGE_ACTIVITY= "/app_module/DetailsPageActivity";
    public static final String ROUTER_PATH_LOGIN_ACTIVITY = "/app_module/LoginActivity";
    // Fragment
    public static final String ROUTER_PATH_HOME_FRAGMENT= "/app_module/HomeFragment";
    public static final String ROUTER_PATH_USER_FRAGMENT = "/app_module/UserFragment";

}
