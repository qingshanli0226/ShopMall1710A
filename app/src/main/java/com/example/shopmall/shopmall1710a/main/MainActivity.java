package com.example.shopmall.shopmall1710a.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.view.BottomBar;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class MainActivity extends BaseActivity<Object> implements CacheManager.IHomeDataListener {

    private TextView contentTV;

    private Fragment[] fragments = new Fragment[]{new HomeFragment(), new TypeFragment(), new MineFragment()};
    private Fragment currentFragment;

    @Override
    protected void initData() {
        //获取首页数据,将获取的数据展示出来
        String homeDataStr = CacheManager.getInstance().getHomeData();
        if (homeDataStr == null) {
            showLoading();
        } else {
            /*contentTV.setText(homeDataStr);*/
        }

        CacheManager.getInstance().registerIHomeDataListener(this);
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        /*contentTV = findViewById(R.id.content);*/

        switchFragment(0);//默认进入HomeFragment
        BottomBar bottomBar= findViewById(R.id.bottomBar);
        bottomBar.setTabCheckedListener(new BottomBar.IBottomBarTabCheckedListener() {
            @Override
            public void onTabChecked(int index) {
                switchFragment(index);
            }
        });
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
    public void onRightImgClick() {
        Toast.makeText(this,"点击了右侧按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void destroy() {
        //注销listener，避免内存泄漏
        CacheManager.getInstance().unRegisterIHomeDataListener();
    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onHomeDataReceived(final String homeDataJson) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                /*contentTV.setText(homeDataJson);*/
            }
        });
    }

    //该方法，在Activity已经存在的情况下，并且Activity的启动模式不是standard,再次startActivity时会调用该方法，不会调用oncreate方法.
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("LQS", "onNewIntent.......................................");

        int index = intent.getIntExtra("index", -1);
        switchFragment(index);

    }
}
