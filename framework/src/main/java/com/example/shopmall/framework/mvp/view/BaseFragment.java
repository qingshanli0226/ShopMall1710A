package com.example.shopmall.framework.mvp.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.customView.CustomTitleBar;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.framework.mvp.presenter.IPresenter;

import java.util.List;

public abstract class BaseFragment<T> extends Fragment implements IBaseFragment,IBaseView<T> {
    protected ProgressBar mLoadingBar;
    protected CustomTitleBar mCustomTitleBar;
    private View baseView;
    private List<IPresenter> presenterList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return baseView = inflater.inflate(bindLayout(),container,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public <T extends View> T findViewById(int id) {
        return baseView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenterList != null){
            for (IPresenter iPresenter : presenterList) {
                iPresenter.destroy();
            }
        }
    }
}
