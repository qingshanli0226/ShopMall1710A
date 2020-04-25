package com.example.shopmall.shopmall1710a.main.view.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.shopmall.framework.base.bean.ButtomBean;
import com.example.shopmall.framework.base.view.CustomButtom;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.MyTab;
import com.example.shopmall.shopmall1710a.main.view.fragment.*;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager actMainFramel;
    private CustomButtom customButtom;
    //创建集合
    private ArrayList<Fragment> list = new ArrayList<>();
    private ArrayList<ButtomBean> blist = new ArrayList<>();

    private Fragment homeFrag;
    private Fragment typeFrag;
    private Fragment findFrag;
    private Fragment carFrag;
    private Fragment personFrag;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdd();

    }

    private void initView() {
        actMainFramel = (ViewPager) findViewById(R.id.act_main_framel);
        customButtom = (CustomButtom) findViewById(R.id.act_main_custbut);

    }

    private void initAdd() {
        list.clear();
        blist.clear();
        if (homeFrag == null) {
            homeFrag = new HomeFrag();
        }
        if (typeFrag == null) {
            typeFrag = new TypeFrag();
        }
        if (findFrag == null) {
            findFrag = new FindFrag();
        }
        if (carFrag == null) {
            carFrag = new CarFrag();
        }
        if (personFrag == null) {
            personFrag = new PersonalFrag();
        }

        list.add(homeFrag);
        list.add(typeFrag);
        list.add(findFrag);
        list.add(carFrag);
        list.add(personFrag);

        blist.add(new ButtomBean("首页", R.mipmap.main_home, R.mipmap.main_home_press));
        blist.add(new ButtomBean("分类", R.mipmap.main_type, R.mipmap.main_type_press));
        blist.add(new ButtomBean("发现", R.mipmap.main_community, R.mipmap.main_community_press));
        blist.add(new ButtomBean("购物车", R.mipmap.main_cart, R.mipmap.main_cart_press));
        blist.add(new ButtomBean("个人中心", R.mipmap.main_user, R.mipmap.main_user_press));

        actMainFramel.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });


        customButtom.setViewPager(actMainFramel);
        customButtom.setBottomBeans(blist);
        actMainFramel.setCurrentItem(0);
    }


}
