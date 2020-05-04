package com.example.shopmall.framework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.shopmall.framework.R;
import com.example.shopmall.framework.view.ToorBar;

import java.util.List;

//实现Activity的基类,定义Activity调用逻辑，调用函数的时序，定义一些通用的功能，这些功能，子类会使用
public abstract class BaseFragment<T> extends Fragment implements IBaseView<T>, ToorBar.ToolBarListener {
    protected ProgressBar loadingBar;
    protected View rootView;
    protected ToorBar toorBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initToorBar();
        loadingBar = rootView.findViewById(R.id.progress_bar);//子类Activity定义loadingBar这个控件,不定义的话，页面将崩溃
        initView();//初始化控件
        //子类Activity定义loadingBar这个控件,不定义的话，页面将崩溃
        initPresenter();//初始化presenter
        initData();//初始化数据

        return rootView;
    }

    protected abstract void initData();
    private void initToorBar() {
        toorBar=rootView.findViewById(R.id.toor_bar);
        toorBar.setToolBarListener(this);
    }
    //使presenter和页面关联起来
    private void initPresenter() {
        List<IPresenter<T>> presenterList = getPresenter();
        if (presenterList==null){
            return;
        }
        for(IPresenter<T> item : presenterList) {
            item.attachView(this);
        }
    }

    protected abstract List<IPresenter<T>> getPresenter();

    protected abstract void initView();

    //需要子类提供layoutID
    protected abstract int getLayoutId();

    //约束子类使用presenter获取数据时必须显示加载页面
    @Override
    public void showLoading() {
        //不加为空的判断，是告诉子类，如果你使用Presenter获取网络数据，那么你必须显示这个加载页面，否则的话，会出现页面崩溃.
        loadingBar.setVisibility(View.VISIBLE);
    }

    //关闭加载页面
    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
        destroyPresenter();
    }

    //强制presenter去调用detachView,把presenter对页面的引用置成空，避免内存泄漏
    protected void destroyPresenter() {
        List<IPresenter<T>> presenterList = getPresenter();
        if (presenterList==null){
            return;
        }
        for(IPresenter<T> item : presenterList) {
            item.detachView();
        }
    }

    protected abstract void destroy();

    @Override
    public void onLeftImgClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onLeftTvClick() {

    }

    @Override
    public void onRightTvClick() {
        Log.i("TAG", "onRightTvClick: "+11);
    }
}
