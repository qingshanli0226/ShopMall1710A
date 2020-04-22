package com.example.shopmall.shopmall1710a.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.classify.ClassifyFragment;
import com.example.shopmall.shopmall1710a.find.FindFragment;
import com.example.shopmall.shopmall1710a.home.entity.TabEntity;
import com.example.shopmall.shopmall1710a.mine.MineFragment;
import com.example.shopmall.shopmall1710a.shop.ShopFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout act_home_line;
    private CommonTabLayout act_home_tab;
    private Fragment mHomeFragment,mClassifyFragment,mFindFragment,mShopFragment,mMineFragment;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<Fragment> list_fragment = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        act_home_line = (LinearLayout) findViewById(R.id.act_home_line);
        act_home_tab = (CommonTabLayout) findViewById(R.id.act_home_tab);

        mHomeFragment = new HomeFragment();
        mClassifyFragment = new ClassifyFragment();
        mFindFragment = new FindFragment();
        mShopFragment = new ShopFragment();
        mMineFragment = new MineFragment();

        list_fragment.add(mHomeFragment);
        list_fragment.add(mClassifyFragment);
        list_fragment.add(mFindFragment);
        list_fragment.add(mShopFragment);
        list_fragment.add(mMineFragment);

        list.add(new TabEntity("首页",R.mipmap.home_seleck,R.mipmap.home_unseleck));
        list.add(new TabEntity("分类",R.mipmap.classify_seleck,R.mipmap.classify_unseleck));
        list.add(new TabEntity("发现",R.mipmap.find_seleck,R.mipmap.find_unseleck));
        list.add(new TabEntity("购物车",R.mipmap.shop_seleck,R.mipmap.shop_unseleck));
        list.add(new TabEntity("我的",R.mipmap.mine_seleck,R.mipmap.mine_unseleck));

        act_home_tab.setTabData(list,this,R.id.act_home_line,list_fragment);
    }
}
