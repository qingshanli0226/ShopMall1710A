package com.example.shopmall.shopmall1710a.search.presenter;

import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.search.mode.SearchResultBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class SearchPresenter extends BasePresenter<Object> {
    @Override
    protected String getPath() {
        return "search";
    }

    public void addParam(String name){
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        updateParamsMap(map);
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<BaseBean<List<SearchResultBean>>>(){}.getType();
    }
}
