package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.view.MyRadioGroup;
import com.example.shopmall.shopmall1710a.fragment.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRadioGroup.RadioGroupListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titlelist = new ArrayList<>();
    private MyRadioGroup myRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        myRadioGroup = (MyRadioGroup) findViewById(R.id.myRadioGroup);
        myRadioGroup.setRadioGroupListener(this);
        titlelist.add("首页");
        titlelist.add("分类");
        titlelist.add("发现");
        titlelist.add("购物车");
        titlelist.add("个人中心");
        fragmentList.add(new HomeFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new HomeFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return titlelist.get(position);
//            }
        });
//        tabLayout.setupWithViewPager(viewPager);
//        //添加条目的视图（图标）
//        tabLayout.getTabAt(0).setIcon(R.drawable.home);
//        tabLayout.getTabAt(1).setIcon(R.drawable.category);
//        tabLayout.getTabAt(2).setIcon(R.drawable.search);
//        tabLayout.getTabAt(3).setIcon(R.drawable.cart);
//        tabLayout.getTabAt(4).setIcon(R.drawable.personal);
    }

    @Override
    protected void onResume() {
        super.onResume();
        long lastOpen = CacheManager.getInstance().spCache.getAdrTime();
        long l = System.currentTimeMillis();
        if (l-lastOpen>10000){
            Toast.makeText(this, "超过10秒", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,WelcomeActivity.class);
            startActivity(intent);
        }
        Log.i("dd","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CacheManager.getInstance().spCache.saveAdrTime(System.currentTimeMillis());
        Log.i("dd","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().spCache.saveAdrTime(0);
        Log.i("dd","onDestroy");
    }

    @Override
    public void onRadioButtonOneClick() {
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onRadioButtonTwoClick() {
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onRadioButtonThreeClick() {
        viewPager.setCurrentItem(2);
    }

    @Override
    public void onRadioButtonFoneClick() {
        viewPager.setCurrentItem(3);
    }

    @Override
    public void onRadioButtonFiveClick() {
        viewPager.setCurrentItem(4);
    }
}
