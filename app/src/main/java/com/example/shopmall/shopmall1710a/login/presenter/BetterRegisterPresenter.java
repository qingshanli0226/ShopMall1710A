package com.example.shopmall.shopmall1710a.login.presenter;



import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.login.mode.BetterLoginBean;
import com.example.shopmall.shopmall1710a.login.mode.RegisterBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.TreeMap;

public class BetterRegisterPresenter extends BasePresenter<RegisterBean> {
    @Override
    protected String getPath() {
        return "register";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addParmas(String name, String password) {
        //添加签名信息
        TreeMap<String, String> params = new TreeMap<>();
        params.put("name", name);
        params.put("password", password);

       /* String sign = SignUtil.generateSign(params);
        params.put("sign", sign);//把签名信息放到map的后面 作用是保证数据传输过程中不可篡改

        Map<String,String> encryptParams = SignUtil.encryptParamsByBase64(params);//编码加密：保证数据以密文传输*/

        updateParamsMap(params);//使用编码后的参数发起网络请求
    }

    @Override
    public void destroy() {

    }
}
