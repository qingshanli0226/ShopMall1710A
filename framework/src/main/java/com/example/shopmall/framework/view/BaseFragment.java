package com.example.shopmall.framework.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.shopmall.framework.AppCore;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.presenter.IPresenter;

import java.util.List;

public abstract class BaseFragment<T> extends Fragment implements IBaseView<T>,IBaseFragment{
    private RelativeLayout baseView;
    private FrameLayout.LayoutParams params;
    private ImageView mloding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return bindView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        initPresenter();
        initData();
    }


    private RelativeLayout bindView(){
        baseView = new RelativeLayout(AppCore.getInstance().getApp());
        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        baseView.setLayoutParams(params);
        View view = getLayoutInflater().inflate(bindLayout(), null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        baseView.addView(view);
        mloding = new ImageView(AppCore.getInstance().getApp());
        mloding.setImageResource(R.drawable.img2);
        baseView.addView(mloding);
        mloding.setVisibility(View.GONE);
        return baseView;
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return baseView.findViewById(id);
    }

    @Override
    public void initPresenter() {
        List<IPresenter> presenterList = getPresenter();
    }

    protected abstract List<IPresenter> getPresenter();


    @Override
    public void showLoading() {
        mloding.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mloding.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyPresenter();
    }

    //强制presenter去调用detachView,把presenter对页面的引用置成空，避免内存泄漏
    protected void destroyPresenter() {
        List<IPresenter> presenterList = getPresenter();
        for(IPresenter item : presenterList) {
            item.destroy();
        }
    }
}
