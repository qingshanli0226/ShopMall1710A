package com.example.shopmall.shopmall1710a.home.view.frg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.HomeAdapter;
import com.example.shopmall.shopmall1710a.home.model.HomeEntity;
import com.example.shopmall.shopmall1710a.home.view.DetailsPageAct;
import com.google.gson.Gson;
import retrofit2.http.GET;

import java.util.ArrayList;
import java.util.List;
/*
 首頁
 */
@Route(path = Constant.ROUTER_PATH_HOME_FRG)
public class HomeFragment extends BaseFragment implements CacheManager.IHomeDataListener,HomeAdapter.StartActivityLinsenner{

    private Handler handler = new Handler();
    private RecyclerView home_rv;
    private List<HomeEntity> homeEntities;
    private HomeAdapter homeAdapter;
    private HomeEntity homeEntity;

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
        homeAdapter.setStartActivityLinsenner(this);
        home_rv.setAdapter(homeAdapter);
    }

    @Override
    public void initData() {
        String homeData = CacheManager.getInstance().getHomeData();
        showTost(homeData);
        if (homeData == null){
//            showLoading();
        }else { // 有缓存数据
            Log.i("happy", "initData: 有缓存数据");
            homeEntity = new Gson().fromJson(homeData, HomeEntity.class);
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
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
////                showTost(homeDataJson);
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterIHomeDataListener();
    }

    @Override
    public void onStartOk(int type, int position) {
//        Intent intent = new Intent(getActivity(), DetailsPageAct.class);
//        startActivity(intent);


        switch (type){
            case HomeEntity.HOME_TYPE_RECOMMEND:
                ARouter.getInstance().build(Constant.ROUTER_PATH_DETAILS_PAGE_ACT)
                        .withSerializable("msg",homeEntity.getResult().getRecommend_info().get(position))
                        .navigation();

            break;
        }

    }
}
