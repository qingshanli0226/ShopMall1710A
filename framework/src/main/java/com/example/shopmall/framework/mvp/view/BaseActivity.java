package com.example.shopmall.framework.mvp.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.customView.CustomTitleBar;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.framework.mvp.presenter.IPresenter;

import java.util.List;


public abstract class BaseActivity<T> extends AppCompatActivity implements IBaseView<T>,IBaseActivity {
    protected ProgressBar mLoadingBar;
    protected CustomTitleBar mCustomTitleBar;
    private List<IPresenter> presenterList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initTitleBar();
        initPresenter();
        initView();
        inject();
        initLoading();
        initData();
    }

    @Override
    public void inject() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initLoading() {
        mLoadingBar = findViewById(R.id.loadingBar);
        mLoadingBar.setVisibility(View.GONE); // 默认不显示
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void initTitleBar() {
        mCustomTitleBar = findViewById(R.id.customTitleBar);
    }

    @Override
    public void initPresenter() {
        presenterList = getPresenter();
        if (presenterList==null){
            return;
        }
    }

    @Override
    public void showLoading() {
        if (mLoadingBar != null){
            mLoadingBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingBar != null){
            mLoadingBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(AppCore.getInstance().getApp(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenterList != null){
            for (IPresenter iPresenter : presenterList) {
                iPresenter.destroy();
            }
        }
    }
}
