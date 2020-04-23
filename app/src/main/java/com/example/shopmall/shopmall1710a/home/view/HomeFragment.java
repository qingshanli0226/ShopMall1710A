package com.example.shopmall.shopmall1710a.home.view;

import android.os.Handler;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.HomeAdapter;
import com.example.shopmall.shopmall1710a.home.model.HomeEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
/*
 首頁
 */
public class HomeFragment extends BaseFragment implements CacheManager.IHomeDataListener{

    private Handler handler = new Handler();
    private RecyclerView home_rv;
    private List<HomeEntity> homeEntities;
    private HomeAdapter homeAdapter;

    @Override
    public int bindLayout() {
        return R.layout.frg_home;
    }

    @Override
    public void initView() {
        home_rv = (RecyclerView) findViewById(R.id.home_rv);
        home_rv.setLayoutManager(new LinearLayoutManager(AppCore.getInstance().getApp()));
        homeEntities = new ArrayList<>();
        homeAdapter = new HomeAdapter(homeEntities);
        home_rv.setAdapter(homeAdapter);
    }

    @Override
    public void initData() {
        String homeData = CacheManager.getInstance().getHomeData();
        showTost(homeData);
        if (homeData == null){
            showLoading();
        }else { // 有缓存数据
            Log.i("happy", "initData: 有缓存数据");
            HomeEntity homeEntity = new Gson().fromJson(homeData, HomeEntity.class);
            homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),1));
            homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),2));
            homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),3));
            homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),4));
            homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),5));

            homeAdapter.notifyDataSetChanged();
        }
        CacheManager.getInstance().registerIHomeDataListener(this);

    }

    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onHomeDataReceived(final String homeDataJson) { // 这里不是主线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                showTost(homeDataJson);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterIHomeDataListener();
    }
}
