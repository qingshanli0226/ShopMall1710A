package com.example.shopmall.shopmall1710a.main.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.view.HomeFragment;
import com.example.shopmall.shopmall1710a.main.adapter.MyTabEntitie;
import com.example.shopmall.shopmall1710a.main.view.frg.*;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.List;

public class MainAct extends BaseActivity {
    private CommonTabLayout main_tb;
    private ArrayList<CustomTabEntity> myTabEntities;
    private ArrayList<Fragment> fragments;

    @Override
    public int bindLayout() {
        return R.layout.act_main;
    }

    @Override
    public void initView() {
        main_tb = (CommonTabLayout) findViewById(R.id.main_tb);

        myTabEntities = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    @Override
    public void initData() {
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new FindFragment());
        fragments.add(new ShoppFragment());
        fragments.add(new UserFragment());

        myTabEntities.add(new MyTabEntitie("首页",R.mipmap.main_home,R.mipmap.main_home_press));
        myTabEntities.add(new MyTabEntitie("分类",R.mipmap.main_type,R.mipmap.main_type_press));
        myTabEntities.add(new MyTabEntitie("发现",R.mipmap.main_community,R.mipmap.main_community_press));
        myTabEntities.add(new MyTabEntitie("购物车",R.mipmap.main_cart,R.mipmap.main_type_press));
        myTabEntities.add(new MyTabEntitie("个人中心",R.mipmap.main_user,R.mipmap.main_user_press));

        main_tb.setTabData(myTabEntities,this,R.id.main_fl,fragments);
    }


    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
