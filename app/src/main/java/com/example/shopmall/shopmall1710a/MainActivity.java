package com.example.shopmall.shopmall1710a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;

import java.util.List;

public class MainActivity extends BaseActivity<Object> implements CacheManager.IHomeDataListener {

    private TextView contentTV;

    @Override
    protected void initData() {
        //获取首页数据,将获取的数据展示出来
        String homeDataStr = CacheManager.getInstance().getHomeData();
        if (homeDataStr == null) {
            showLoading();
        } else {

            contentTV.setText(homeDataStr);
        }

        CacheManager.getInstance().registerIHomeDataListener(this);
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
         contentTV = findViewById(R.id.content);
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
                contentTV.setText(homeDataJson);
            }
        });
    }
}
