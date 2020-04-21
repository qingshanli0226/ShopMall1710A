package com.example.shopmall.common;

//封装错误信息
public class ErrorBean {
    private int errorCode; //错误码
    private String errorMessage;//错误信息

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
