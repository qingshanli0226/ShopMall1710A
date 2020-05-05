package com.example.shopmall.shopmall1710a.main.presenter;

import com.example.shopmall.framework.base.presenter.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.main.bean.SearchBean;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPresenter extends BasePresenter<SearchBean> {


    @Override
    protected String getPath() {
        return "search";
    }


    public void addParams(String string) {
        Map<String, String> map = new HashMap<>();
        map.put("name", string);

        updateParamsMap(map);

    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<List<SearchBean>>>() {
        }.getType();
    }

    @Override
    public void destroy() {

    }
}
