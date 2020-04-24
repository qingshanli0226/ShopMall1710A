package com.example.shopmall.framework.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.framework.presenter.IPresenter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public abstract class BaseActivity<T> extends AppCompatActivity implements IBaseView<T>,IBaseActivity{
    private RelativeLayout baseView;
    private FrameLayout.LayoutParams params;
    private RelativeLayout loadingBar;
    protected ImageView welcomIv;
    private long star_time;
    private long end_time;
    private Timer mTimer;
    private int count = 0;
    private Handler handler = new Handler();
    private boolean flage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindView());
        initView();
        initPresenter();
        initData();
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
        loadingBar = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_framework,null);
        FrameLayout.LayoutParams loadingBarParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loadingBar.setLayoutParams(loadingBarParams);
        loadingBar.setVisibility(View.GONE);
        baseView.addView(loadingBar);
        welcomIv = new ImageView(this);
        FrameLayout.LayoutParams welcomIvParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        welcomIv.setLayoutParams(welcomIvParams);
        welcomIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this)
                .load("http://49.233.93.155:8080/atguigu/gif/welcome.gif")
                .into(welcomIv);
        baseView.addView(welcomIv);
        welcomIv.setVisibility(View.GONE);
        return baseView;
    }

    @Override
    public void showTost(String str) {
        Toast.makeText(AppCore.getInstance().getApp(),str, Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        destroyPresenter();
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    //强制presenter去调用detachView,把presenter对页面的引用置成空，避免内存泄漏
    protected void destroyPresenter() {
        List<IPresenter> presenterList = getPresenter();
        if (presenterList != null){
            for(IPresenter item : presenterList) {
                item.destroy();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        flage = !flage;
        if (flage){
            star_time = System.currentTimeMillis();
        }else {
            end_time = System.currentTimeMillis();
            if (end_time - star_time >= 5000){
                welcomIv.setVisibility(View.VISIBLE);
                mTimer = new Timer();
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.i("happy", "run: ----------------");
                        if (count == 3){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    count = 0;
                                    welcomIv.setVisibility(View.GONE);
                                    mTimer.cancel();
                                    mTimer = null;
                                }
                            });
                        }
                        count++;
                    }
                },0,1000);
            }
        }
    }


}
