package com.example.shopmall.shopmall1710a;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shopmall.buy.shopcar.ShopcarFragment;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.view.RadioButtonBean;
import com.example.shopmall.shopmall1710a.fragment.home.HomeFragment;
import com.example.shopmall.shopmall1710a.fragment.type.TypeFragment;
import com.example.shopmall.shopmall1710a.fragment.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<Object> {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList = new ArrayList<>();
//    private List<String> titlelist = new ArrayList<>();
    private List<RadioButtonBean> list = new ArrayList<>();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("LQS", "onNewIntent.......................................");
        myRadioGroup.onCheckedChanged(myRadioGroup.radioGroup,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    @Override
    protected void initData() {
//        titlelist.add("首页");
//        titlelist.add("分类");
//        titlelist.add("发现");
//        titlelist.add("购物车");
//        titlelist.add("个人中心");
        fragmentList.add(new HomeFragment());
        fragmentList.add(new TypeFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ShopcarFragment());
        fragmentList.add(new UserFragment());
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
        list.add(new RadioButtonBean("首页",R.mipmap.main_bottom_tab_home_focus,R.mipmap.main_bottom_tab_home_normal));
        list.add(new RadioButtonBean("分类",R.mipmap.main_bottom_tab_category_focus,R.mipmap.main_bottom_tab_category_normal));
        list.add(new RadioButtonBean("发现",R.mipmap.main_bottom_tab_search_focus,R.mipmap.main_bottom_tab_search_normal));
        list.add(new RadioButtonBean("购物车",R.mipmap.main_bottom_tab_cart_focus,R.mipmap.main_bottom_tab_cart_normal));
        list.add(new RadioButtonBean("个人中心",R.mipmap.main_bottom_tab_personal_focus,R.mipmap.main_bottom_tab_personal_normal));
        myRadioGroup.setRadioButtonList(list,true);
        myRadioGroup.withPager(viewPager);
    }



    //网络请求操作 （没使用）
    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    protected void destroy() {

    }

    //关于 广告操作
    @Override
    protected void onResume() {
        super.onResume();
        long lastOpen = SpUtil.getAdrTime(this);
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
        SpUtil.saveAdrTime(this,System.currentTimeMillis());
        Log.i("dd","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpUtil.saveAdrTime(this,0);
        Log.i("dd","onDestroy");
    }
}
