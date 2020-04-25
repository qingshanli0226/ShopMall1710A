package com.example.shopmall.framework.base.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.base.presenter.IBasePresenter;

public abstract class BaseActivity<P extends IBasePresenter, T> extends AppCompatActivity implements IBaseView<T>, IBaseActivity, CustomToolBar.ToolBarListener {
    protected P mPresenter;
    protected CustomToolBar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initToolBar();
        initView();
        initData();
        inject();
    }


    public void setToolBarTitle(@StringRes int id) {
        toolbar.setToolTitle(id);
    }


    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            System.gc();
        }
    }

    @Override
    public void onLeftTv() {

    }

    @Override
    public void onRightTv() {

    }

    @Override
    public void onLeftImv() {
        finish();
    }

    @Override
    public void onRightImv() {

    }

    @Override
    public void onTitleTv() {

    }


}
