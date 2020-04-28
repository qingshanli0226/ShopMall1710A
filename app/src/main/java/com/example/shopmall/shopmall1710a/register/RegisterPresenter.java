package com.example.shopmall.shopmall1710a.register;



import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;


public class RegisterPresenter extends BasePresenter<String, IBaseView<String>> {


    public RegisterPresenter(IBaseView<String> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "register";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<String>>(){}.getType();
    }

    public void addParams(int name,String password){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        updateParamsMap(map);
    }


}
