package com.example.shopmall;

public class BaseBean<T> { //使用泛型定义具体数据类型
    private int code; //代表网络请求是成功还是失败的
    private String msg; //代真表请求结果信息
    private T result; //是真正的请求的数据
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
