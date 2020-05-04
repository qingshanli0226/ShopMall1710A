package com.example.shopmall.net;


import android.util.Log;

import com.example.shopmall.common.exception.BusinessException;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;

//定义一个类，实现类型转换,R代表输入类型，T代表输出类型
public class NetFunction<R extends ResponseBody,T> implements Function<R,T> {
    private Type type;
    public NetFunction(Type type) {
        this.type = type;
    }
    @Override
    public T apply(R r) throws Exception {
        try {

            BaseBean<T> data = new Gson().fromJson(r.string(), type);
            Log.e("TAG", "apply: "+data.toString() );
            if (data.getCode()!=200) {
                //在这个方法中抛出去的异常，在onError中处理异常
                throw new BusinessException(data.getCode(), data.getMsg());
            }
            return data.getResult();
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonIOException("类型解析错误");
        }
    }
}
