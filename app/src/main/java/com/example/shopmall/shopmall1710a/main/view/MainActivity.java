package com.example.shopmall.shopmall1710a.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.BaseBean;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomBottomBar;
import com.example.shopmall.framework.customView.bean.BottomBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.MyPagerAdapter;
import com.example.shopmall.shopmall1710a.main.entity.LoginEntity;
import com.example.shopmall.shopmall1710a.main.view.fragment.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ROUTER_PATH_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements CacheManager.IHomeDataListener {
    private ViewPager viewPager;
    private CustomBottomBar bottomBar;
    private List<BottomBean> lists;
    private MyPagerAdapter myPagerAdapter;
    private List<Fragment> fragments;
    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        // 注册监听
        CacheManager.getInstance().registerIHomeDataListener(this);
        viewPager = findViewById(R.id.viewPager);
        bottomBar = findViewById(R.id.bottomBar);

        lists = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    @Override
    public void initData() {
        lists.add(new BottomBean("首页",R.mipmap.main_home,R.mipmap.main_home_press));
        lists.add(new BottomBean("分类",R.mipmap.main_type,R.mipmap.main_type_press));
        lists.add(new BottomBean("发现",R.mipmap.main_community,R.mipmap.main_community_press));
        lists.add(new BottomBean("购物车",R.mipmap.main_cart,R.mipmap.main_type_press));
        lists.add(new BottomBean("个人中心",R.mipmap.main_user,R.mipmap.main_user_press));
//
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new FindFragment());
        fragments.add(new ShoppFragment());
        fragments.add(new UserFragment());

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myPagerAdapter);
        bottomBar.setViewPager(viewPager);
        bottomBar.setBottomBeans(lists);
    }

    @Override
    public List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("xxx", "onNewIntent: xxxxxxxxxxxxxxxxxxxxx");
        viewPager.setCurrentItem(0);
    }



    @Override
    public void onHomeDataReceived(String homeDataJson) {

    }

    // 自动登录成功 : 返回 数据
    @Override
    public void onAutoLoginDataReceived(String autoLoginDataJson) {
        BaseBean<LoginEntity> object = (BaseBean<LoginEntity>) new Gson().fromJson(autoLoginDataJson, new TypeToken<BaseBean<LoginEntity>>() {
        }.getRawType());
        LoginEntity result = object.getResult();
        Log.i("boss", "onAutoLoginDataReceived: "+autoLoginDataJson);
        SPUtils.getInstance().put(Constant.SP_TOKEN,result.getToken());
        Log.i("boss", "onAutoLoginDataReceived: "+result.getToken());
    }
}
