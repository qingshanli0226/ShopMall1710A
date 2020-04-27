package com.example.shopmall.common;

public class Constant {

    public static final int HTTP_REQUEST_ERROR = -1;
    public static final String HTTP_REQUEST_ERROR_MESSAGE = "网络错误, 请检查网络情况";//403

    public static final int JSON_PARSE_ERROR = -2;
    public static final String JSON_PARSE_ERROR_MESSAGE = "Json数据解析错误";

    public static final int NULL_PARSE_ERROR = -3;
    public static final String NULL_PARSE_ERROR_MESSAGE = "空数据错误";

    public static final int REGISTER_ERROR = -4;
    public static final String REGISTER_ERROR_MESSAGE = "注册错误";

    public static final String BASE_URL_KS = "http://172.81.227.127:8055/";
    public static final String BASE_URL = "http://49.233.93.155:8080/";
    //public static final String BASE_URL = "http://192.168.115.1:8080/";

    public static final String BASE_IMAGE_URL = BASE_URL+"atguigu/img";

    public static final boolean IS_KS = false;
}
