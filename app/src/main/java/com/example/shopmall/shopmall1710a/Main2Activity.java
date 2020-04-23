package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.SpCache;
import com.example.shopmall.shopmall1710a.home.HomeFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private ViewPager pager;
    private CommonTabLayout menu;
    private SpCache spCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CacheManager.getInstance().spCache.savetlastOpen(System.currentTimeMillis());
        initView();
        list.add(new TabEnitity("首页", R.drawable.main_home, R.drawable.main_home_press));
        list.add(new TabEnitity("分类", R.drawable.main_type, R.drawable.main_type_press));
        list.add(new TabEnitity("发现", R.drawable.main_community, R.drawable.main_community_press));
        list.add(new TabEnitity("购物车", R.drawable.main_cart, R.drawable.main_cart_press));
        list.add(new TabEnitity("个人中心", R.drawable.main_user, R.drawable.main_user_press));
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        menu.setTabData(list);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        menu.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                menu.setCurrentTab(i);
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void initView() {
        spCache = CacheManager.getInstance().spCache;
        pager = findViewById(R.id.pager);
        menu = findViewById(R.id.menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        long lastOpen = CacheManager.getInstance().spCache.getlastOpen();
        long l = System.currentTimeMillis();
        if (l-lastOpen>10000){
            Toast.makeText(this, "超过10秒", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,WelcomActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        CacheManager.getInstance().spCache.savetlastOpen(System.currentTimeMillis());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().spCache.savetlastOpen(0);
    }
}
