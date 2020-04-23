package com.example.shopmall.framework.base.view;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopmall.framework.base.presenter.IBasePresenter;

public abstract class BaseActivity<P extends IBasePresenter, T> extends AppCompatActivity implements IBaseView<T>, IBaseActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        initData();
        inject();
    }


    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            System.gc();
        }
    }

}
