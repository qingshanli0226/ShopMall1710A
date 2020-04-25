package com.example.shopmall.shopmall1710a.main.presenter;




import com.example.shopmall.BaseBean;
import com.example.shopmall.framework.mvp.presenter.BasePresenter;
import com.example.shopmall.framework.mvp.view.IBaseView;
import com.example.shopmall.shopmall1710a.main.entity.LoginEntity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class LoginPresenter extends BasePresenter<LoginEntity, IBaseView<LoginEntity>> {
    public LoginPresenter(IBaseView<LoginEntity> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "login";
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<LoginEntity>>(){}.getType();
    }

    public void addParams(int name,String password){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        updateParamsMap(map);
    }
}
