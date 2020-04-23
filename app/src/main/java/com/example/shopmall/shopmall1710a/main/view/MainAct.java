package com.example.shopmall.shopmall1710a.main.view;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.MyTab;
import com.example.shopmall.shopmall1710a.main.view.fragment.*;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainAct extends AppCompatActivity implements OnTabSelectListener {

    private FrameLayout actMainFramel;
    private CommonTabLayout actMainTabl;


    private ArrayList<CustomTabEntity> flist = new ArrayList<>();

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
    }

    private void initView() {
        initAdd();
        actMainFramel = (FrameLayout) findViewById(R.id.act_main_framel);
        actMainTabl = (CommonTabLayout) findViewById(R.id.act_main_tabl);

        actMainTabl.setTabData(flist);

        hideAll();
        showOne(homeFrag);

        actMainTabl.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        switch (position) {
            case 0:
                hideAll();
                showOne(homeFrag);
                break;
            case 1:
                hideAll();
                showOne(typeFrag);
                break;
            case 2:
                hideAll();
                showOne(findFrag);
                break;
            case 3:
                hideAll();
                showOne(carFrag);
                break;
            case 4:
                hideAll();
                showOne(personFrag);
                break;
        }
    }

    @Override
    public void onTabReselect(int position) {

    }

    public void showOne(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .show(fragment).commit();
    }

    private void initAdd() {
        flist.clear();

        flist.add(new MyTab("首页", R.drawable.home, R.drawable.home_no));
        flist.add(new MyTab("分类", R.drawable.type, R.drawable.type_no));
        flist.add(new MyTab("发现", R.drawable.find, R.drawable.find_no));
        flist.add(new MyTab("购物车", R.drawable.shop_car, R.drawable.shop_car_no));
        flist.add(new MyTab("个人中心", R.drawable.person, R.drawable.person_no));


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

        getSupportFragmentManager().beginTransaction()
                .add(R.id.act_main_framel, homeFrag)
                .add(R.id.act_main_framel, typeFrag)
                .add(R.id.act_main_framel, findFrag)
                .add(R.id.act_main_framel, carFrag)
                .add(R.id.act_main_framel, personFrag)
                .commit();


        hideAll();

    }

    private void hideAll() {
        getSupportFragmentManager().beginTransaction()
                .hide(homeFrag)
                .hide(typeFrag)
                .hide(findFrag)
                .hide(carFrag)
                .hide(personFrag)
                .commit();
    }

}
