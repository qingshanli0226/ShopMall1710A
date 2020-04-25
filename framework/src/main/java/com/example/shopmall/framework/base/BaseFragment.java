package com.example.shopmall.framework.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.shopmall.framework.R;
import com.example.shopmall.framework.view.MyToolBar;

import java.util.List;

public abstract class BaseFragment<T> extends Fragment implements IBaseView<T>,MyToolBar.ToolBarListener {
    protected ProgressBar loadingBar;
    private MyToolBar myToolBar;
    protected View inflate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(getLayoutId(), container, false);
        loadingBar = inflate.findViewById(R.id.loadingBar);
        initView();//初始化控件
        myToolBar = inflate.findViewById(R.id.myToolBar);
        myToolBar.setToolBarClickListener(this);
        initPresenter();//初始化presenter
        initData();//初始化数据
        return inflate;
    }

    @Override
    public void onLeftImgClick() {

    }

    @Override
    public void onRightImgClick() {

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

    /**
     * 需要子类提供layoutID
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

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
        if (presenterList == null) {
            return;
        }
        for(IPresenter<T> item : presenterList) {
            item.detachView();
        }
    }

    protected abstract void destroy();
}
