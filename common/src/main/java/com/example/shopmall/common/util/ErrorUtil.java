package com.example.shopmall.common.util;

import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.exception.BusinessException;
import com.google.gson.JsonIOException;
import org.json.JSONException;
import retrofit2.HttpException;

//统一处理错误
public class ErrorUtil {
    //http网络请求错误 请求网络地址或者参数格式不对
    //Json解析错误  bean和Json字符串不匹配
    //业务错误 例如重复注册错误
    //空指针错误

    public static ErrorBean handleError(Throwable e) {
        ErrorBean errorBean = new ErrorBean();
        if (e instanceof HttpException) {//网络请求错误
            errorBean.setErrorCode(Constant.HTTP_REQUEST_ERROR);
            errorBean.setErrorMessage(Constant.HTTP_REQUEST_ERROR_MESSAGE);
        } else if (e instanceof JSONException) {
            errorBean.setErrorCode(Constant.JSON_PARSE_ERROR);
            errorBean.setErrorMessage(Constant.JSON_PARSE_ERROR_MESSAGE);
        } else if (e instanceof BusinessException) {//在这个地方处理业务错误
            BusinessException businessException = (BusinessException)e;
            errorBean.setErrorCode(businessException.getCode());
            errorBean.setErrorMessage(businessException.getMessage());
        } else if (e instanceof JsonIOException) {//处理json解析错误
            JsonIOException jsonIOException = (JsonIOException) e;
            errorBean.setErrorCode(Constant.JSON_PARSE_ERROR);
            errorBean.setErrorMessage(jsonIOException.getMessage());
        }

        else {
            errorBean.setErrorCode(Constant.NULL_PARSE_ERROR);
            errorBean.setErrorMessage(Constant.NULL_PARSE_ERROR_MESSAGE);
        }

        return errorBean;
    }

    public static ErrorBean handleError(int errorCode, String errorMessge) {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setErrorCode(errorCode);
        errorBean.setErrorMessage(errorMessge);
        return errorBean;
    }
}
