package com.example.shopmall.common.exception;

//业务异常信息，就是当code不等于200是，封装一个异常信息
public class BusinessException extends RuntimeException{
    private int code;
    private String message;

    public BusinessException(int code, String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
