package com.example.shopmall.framework.base;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public abstract class BaseActivity<P extends BasePresenter,T> extends AppCompatActivity implements IBaseView<T>, IBaseActivity {
    protected P mPresenter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        inject();
        initData();
    }
    @Override
    public void inject() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter = null;
        }
    }

}
