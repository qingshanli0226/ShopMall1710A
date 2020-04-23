package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.shopmall1710a.home.HomeFragment;
import com.example.shopmall.shopmall1710a.home.entity.MyTabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CommonTabLayout tabLayout;
    private HomeFragment homeFragment;
    ArrayList<CustomTabEntity> tabEntitys=new ArrayList<>();
    private List<Fragment> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (CommonTabLayout) findViewById(R.id.tab_layout);
        CacheManager.getInstance().spCache.savetlastOpen(System.currentTimeMillis());
        homeFragment=new HomeFragment();
        list.add(homeFragment);
        tabEntitys.add(new MyTabEntity("首页", R.drawable.main_home, R.drawable.main_home_press));
        tabEntitys.add(new MyTabEntity("分类", R.drawable.main_type, R.drawable.main_type_press));
        tabEntitys.add(new MyTabEntity("发现", R.drawable.main_community, R.drawable.main_community_press));
        tabEntitys.add(new MyTabEntity("购物车", R.drawable.main_cart, R.drawable.main_cart_press));
        tabEntitys.add(new MyTabEntity("个人中心", R.drawable.main_user, R.drawable.main_user_press));
        tabLayout.setTabData(tabEntitys);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }


        });
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        long lastOpen = CacheManager.getInstance().spCache.getlastOpen();
        long l = System.currentTimeMillis();
        if (l-lastOpen>10000){
            Toast.makeText(this, "超过十秒", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,WelcomeActivity.class);
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
