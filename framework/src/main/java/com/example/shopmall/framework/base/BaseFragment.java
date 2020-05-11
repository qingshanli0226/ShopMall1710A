package com.example.shopmall.framework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ConnectManager;
import com.example.shopmall.framework.view.ToolBar;

import java.util.List;

public abstract class BaseFragment <T> extends Fragment implements IBaseView<T>,ToolBar.ToolBarListener, ConnectManager.IConnectChangeListener {
    protected ProgressBar loadingBar;
    protected ToolBar toolBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initView(rootView);
        initPresenter();
        loadingBar = rootView.findViewById(R.id.loadingBar);//子类Activity定义loadingBar这个控件,不定义的话，页面将崩溃
        initToolBar(rootView);
        initData();
        ConnectManager.getInstance().registerConnectChangeListener(this);
        return rootView;
    }

    //使presenter和页面关联起来
    private void initPresenter() {
        List<IPresenter<T>> presenterList = getPresenter();
        if (presenterList == null) {
            return;
        }
        for(IPresenter<T> item : presenterList) {
            item.attachView(this);
        }
    }

    protected abstract List<IPresenter<T>> getPresenter();

    protected abstract void initData();

    protected void initToolBar(View rootView) {
        toolBar = rootView.findViewById(R.id.toolBar);
        toolBar.setToolBarClickListener(this);
    }


    protected abstract void initView(View rootView);

    public abstract int getLayoutId();


    @Override
    public void onHtttpReceived(int requestCode, T data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void showLoading() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        List<IPresenter<T>> presenterList = getPresenter();
        if (presenterList == null) {
            return;
        }
        for(IPresenter<T> item : presenterList) {
            item.detachView();
        }
        ConnectManager.getInstance().unRegisterConnectChangeListener(this);
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnect() {

    }
}
