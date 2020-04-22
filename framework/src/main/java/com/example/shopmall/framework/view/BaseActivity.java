package com.example.shopmall.framework.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.presenter.IPresenter;

import java.util.List;


public abstract class BaseActivity<T> extends AppCompatActivity implements IBaseView<T>,IBaseActivity{
    private RelativeLayout baseView;
    private FrameLayout.LayoutParams params;
    private ImageView mloding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(bindView());
        initView();
        initPresenter();
        initData();
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

    private RelativeLayout bindView(){
        baseView = new RelativeLayout(this);
        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        baseView.setLayoutParams(params);
        View view = getLayoutInflater().inflate(bindLayout(), null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        baseView.addView(view);
        mloding = new ImageView(this);
        mloding.setImageResource(R.drawable.img2);
        baseView.addView(mloding);
        mloding.setVisibility(View.GONE);
        return baseView;
    }

    @Override
    public void showLoading() {
        mloding.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mloding.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
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