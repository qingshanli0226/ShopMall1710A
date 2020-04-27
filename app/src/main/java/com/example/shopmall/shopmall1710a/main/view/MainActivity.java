package com.example.shopmall.shopmall1710a.main.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomBottomBar;
import com.example.shopmall.framework.customView.bean.BottomBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.framework.service.ShopMallService;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.MyPagerAdapter;
import com.example.shopmall.shopmall1710a.main.view.fragment.*;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ROUTER_PATH_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements CacheManager.IHomeDataListener,CacheManager.IShopCountRecevedLisener {
    private ViewPager viewPager;
    private CustomBottomBar bottomBar;
    private List<BottomBean> lists;
    private MyPagerAdapter myPagerAdapter;
    private List<Fragment> fragments;
    private TextView mainShopCarNum;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mainShopCarNum.setText(msg.what+"");
        }
    };
    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        // 注册监听
        CacheManager.getInstance().registerIHomeDataListener(this);
        CacheManager.getInstance().registerCountLisenner( this);
        viewPager = findViewById(R.id.viewPager);
        bottomBar = findViewById(R.id.bottomBar);
        mainShopCarNum = findViewById(R.id.mainShopCarNum);
        lists = new ArrayList<>();
        fragments = new ArrayList<>();

        mainShopCarNum.setText(SPUtils.getInstance().getInt(Constant.SP_SHOP_COUNT)+"");
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



    @Override
    public void onHomeDataReceived(String homeDataJson) {

    }


    @Override
    public void onShopcarCountReceived(final int conunt) {
        Message message = new Message();
        message.what = conunt;
        handler.sendMessage(message);
        Log.i("boss", "onShopcarCountReceived: 主页面购物车数量"+conunt);
    }
}
