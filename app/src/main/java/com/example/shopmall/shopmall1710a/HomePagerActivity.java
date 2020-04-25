package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.SpCache;
import com.example.shopmall.framework.view.BottomBar;
import com.example.shopmall.framework.view.ButtonInfo;
import com.example.shopmall.shopmall1710a.car.CarFragment;
import com.example.shopmall.shopmall1710a.find.FindFragment;
import com.example.shopmall.shopmall1710a.home.HomeFragment;
import com.example.shopmall.shopmall1710a.type.TypeFragment;
import com.example.shopmall.shopmall1710a.user.PersonageFragment;
import com.flyco.tablayout.CommonTabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomePagerActivity extends AppCompatActivity {
   // private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private ViewPager pager;
    private CommonTabLayout menu;
    private SpCache spCache;
    private Drawable drawable;
    private List<Fragment> list = new ArrayList<>();
    private BottomBar mBottomBar1;
    private List<ButtonInfo> list1=new ArrayList<>();
    private HomeFragment homeFragment=new HomeFragment();
    private TypeFragment typeFragment=new TypeFragment();
    private FindFragment findFragment=new FindFragment();
    private CarFragment carFragment=new CarFragment();
    private PersonageFragment personageFragment=new PersonageFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CacheManager.getInstance().spCache.savetlastOpen(System.currentTimeMillis());
        //使用ViewPager+Commtablayout实现页面切换
       // initCommTab();
        pager = findViewById(R.id.pager);
        CacheManager.getInstance().spCache.savetlastOpen(System.currentTimeMillis());
        list.add(homeFragment);
        list.add(typeFragment);
        list.add(findFragment);
        list.add(carFragment);
        list.add(personageFragment);
        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }

        });
        initButtomBar();


    }
    private void initButtomBar() {
        list1.add(new ButtonInfo("首页", R.drawable.main_home, R.drawable.main_home_press));
        list1.add(new ButtonInfo("分类", R.drawable.main_type, R.drawable.main_type_press));
        list1.add(new ButtonInfo("发现", R.drawable.main_community, R.drawable.main_community_press));
        list1.add(new ButtonInfo("购物车", R.drawable.main_cart, R.drawable.main_cart_press));
        list1.add(new ButtonInfo("个人中心", R.drawable.main_user, R.drawable.main_user_press));
        mBottomBar1 = findViewById(R.id.bottomBar1);
        mBottomBar1.setBtnData(list1,true);
        mBottomBar1.setOnCheckedChangeListener();
        mBottomBar1.withPager(pager);
        mBottomBar1.setBackground(R.drawable.home_bottom_parent_bg);

    }

//    private void initCommTab() {
//        list.add(new TabEnitity("首页", R.drawable.main_home, R.drawable.main_home_press));
//        list.add(new TabEnitity("分类", R.drawable.main_type, R.drawable.main_type_press));
//        list.add(new TabEnitity("发现", R.drawable.main_community, R.drawable.main_community_press));
//        list.add(new TabEnitity("购物车", R.drawable.main_cart, R.drawable.main_cart_press));
//        list.add(new TabEnitity("个人中心", R.drawable.main_user, R.drawable.main_user_press));
//        fragments.add(new HomeFragment());
//        fragments.add(new HomeFragment());
//        fragments.add(new HomeFragment());
//        fragments.add(new HomeFragment());
//        fragments.add(new HomeFragment());
//        menu.setTabData(list);
//        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int i) {
//                return fragments.get(i);
//            }
//
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//        });
//        menu.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                pager.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//            }
//            @Override
//            public void onPageSelected(int i) {
//                menu.setCurrentTab(i);
//            }
//            @Override
//            public void onPageScrollStateChanged(int i) {
//            }
//        });
//    }



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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mBottomBar1.setCheckInt(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().spCache.savetlastOpen(0);
    }
}
