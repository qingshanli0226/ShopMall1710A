package com.example.shopmall.shopmall1710a.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomBottomBar;
import com.example.shopmall.framework.customView.bean.BottomBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.MyPagerAdapter;
import com.example.shopmall.shopmall1710a.main.view.fragment.*;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ROUTER_PATH_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {
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
}
