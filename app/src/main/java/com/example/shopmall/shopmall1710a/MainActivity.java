package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.shopmall.buy.CarFragment;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.view.BottomBar;
import com.example.shopmall.framework.view.ButtonInfo;
import com.example.shopmall.shopmall1710a.find.FindFragment;
import com.example.shopmall.shopmall1710a.home.HomeFragment;
import com.example.shopmall.shopmall1710a.mine.MineFragment;
import com.example.shopmall.shopmall1710a.type.TypeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private CarFragment carFragment;
    private TypeFragment typeFragment;
    private FindFragment findFragment;
    private List<Fragment> list = new ArrayList<>();
    private BottomBar mBottomBar1;
    private List<ButtonInfo> list1=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment=new HomeFragment();
        typeFragment=new TypeFragment();
        findFragment=new FindFragment();
        carFragment=new CarFragment();
        mineFragment=new MineFragment();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        CacheManager.getInstance().spCache.savetlastOpen(System.currentTimeMillis());
        list.add(homeFragment);
        list.add(typeFragment);
        list.add(findFragment);
        list.add(carFragment);
        list.add(mineFragment);
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
        mBottomBar1.withPager(viewPager);
        mBottomBar1.setBackground(R.drawable.home_bottom_parent_bg);

    }
    @Override
    protected void onResume() {
        super.onResume();
        long lastOpen = CacheManager.getInstance().spCache.getlastOpen();
        long l = System.currentTimeMillis();
        if (l - lastOpen > 10000) {
            Toast.makeText(this, "超过十秒", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WelcomeActivity.class);
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
        Log.i("TAG", "onNewIntent: "+1);
       mBottomBar1.setCheckInt(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().spCache.savetlastOpen(0);

    }
}
