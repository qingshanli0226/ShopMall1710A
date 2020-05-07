package com.example.shopmall.shopmall1710a.main;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.shopmall.buy.shopcar.view.ShopcarFragment;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.framework.view.BottomBar;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity<Object> {

    private Fragment[] fragments = new Fragment[]{new HomeFragment(), new TypeFragment(), new ShopcarFragment(),new MineFragment()};
    private Fragment currentFragment;

    @Override
    protected void initData() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();//获取启动MainActivity的intent
        int index = intent.getIntExtra("index", -1);
        Log.d("LQS", "onCreate index......................................." + index);
        if (index == -1 || index > 3) {//代表着启动的该界面，并且进入默认HomeFragment
            switchFragment(0);//默认进入HomeFragment
        }else {//代表着启动该界面，并且要求MainActivity进入到相应的Fragment下
            switchFragment(index);
        }
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        BottomBar bottomBar= findViewById(R.id.bottomBar);
        bottomBar.setTabCheckedListener(new BottomBar.IBottomBarTabCheckedListener() {
            @Override
            public void onTabChecked(int index) {
                switchFragment(index);
            }
        });
        toolBar.setVisibility(View.GONE);
    }

    private void switchFragment(int index) {
        Fragment fragment = fragments[index];
        if (fragment == currentFragment) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment).commit();
        } else {
            fragmentTransaction.add(R.id.frameLayout, fragment).commit();
        }

        currentFragment  = fragment;
    }

    @Override
    public void onRightClick() {
        Toast.makeText(this,"点击了右侧按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void destroy() {
        //注销listener，避免内存泄漏
    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
    }

    //该方法，在Activity已经存在的情况下，并且Activity的启动模式不是standard,再次startActivity时会调用该方法，不会调用oncreate方法.
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int index = intent.getIntExtra("index", -1);
        Log.d("LQS", "onNewIntent index......................................." + index);

        if (index == -1 || index  > 3) {
            switchFragment(0);
        } else {
            switchFragment(index);
        }
    }
}
