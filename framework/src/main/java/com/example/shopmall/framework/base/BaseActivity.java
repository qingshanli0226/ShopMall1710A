package com.example.shopmall.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.shopmall.net.IBaseView;
import com.example.shopmall.net.IPresenter;

import java.util.List;

public abstract class BaseActivity<T> extends AppCompatActivity implements IBaseView<T> {
    protected ProgressBar loadingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        loadingBar = findViewById(R.id.loadingBar);//子类Activity定义loadingBar这个控件,不定义的话，页面将崩溃
        initView();//初始化控件
        initPresenter();//初始化presenter
        initData();//初始化数据
    }

    //使presenter和页面关联起来
    private void initPresenter() {
        List<IPresenter<T>> presenterList = getPresenter();
        for(IPresenter<T> item : presenterList) {
            item.attachView(this);
        }
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract List<IPresenter<T>> getPresenter();


    @Override
    public void showLoading() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        destroyPresenter();
    }

    //强制presenter去调用detachView,把presenter对页面的引用置成空，避免内存泄漏
    protected void destroyPresenter() {
        List<IPresenter<T>> presenterList = getPresenter();
        for(IPresenter<T> item : presenterList) {
            item.detachView();
        }
    }

    protected abstract void destroy();
}
