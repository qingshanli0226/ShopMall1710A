package com.example.shopmall.shopmall1710a.main.view.fragment;


import android.os.Handler;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.HomeAdapter;
import com.example.shopmall.shopmall1710a.home.entity.HomeEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ROUTER_PATH_HOME_FRAGMENT)
public class HomeFragment extends BaseFragment implements CacheManager.IHomeDataListener,HomeAdapter.StartActivityLinsenner{
    private Handler handler = new Handler();
    private RecyclerView homeRv;
    private List<HomeEntity> homeEntities;
    private HomeAdapter homeAdapter;
    private HomeEntity homeEntity;
    @Override
   public int bindLayout() {
        return R.layout.fragment_home ;
    }

    @Override
    public void initView() {
        homeEntities = new ArrayList<>();
        homeRv = (RecyclerView) findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(homeEntities);
        homeAdapter.setStartActivityLinsenner(this);
        homeRv.setAdapter(homeAdapter);
    }

    @Override
    public void initData() {
        String homeData = CacheManager.getInstance().getHomeData();
        if (homeData == null){
//            showLoading();
        }else { // 有缓存数据
            Log.i("happy", "initData: 有缓存数据");
            homeEntity = new Gson().fromJson(homeData, HomeEntity.class);
            addDatas();
        }
        CacheManager.getInstance().registerIHomeDataListener(this);
    }

    private void addDatas() {
        homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),1));
        homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),2));
        homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),3));
        homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),4));
        homeEntities.add(new HomeEntity(1,"ok",homeEntity.getResult(),5));
        homeAdapter.notifyDataSetChanged();

    }

    @Override
    public List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onHomeDataReceived(final String homeDataJson) { // 获取到新的数据刷新页面
//        homeEntities.clear();
        handler.post(new Runnable() {
            @Override
            public void run() {
//                homeEntity= new Gson().fromJson(homeDataJson, HomeEntity.class);
//                homeAdapter.notifyDataSetChanged();
            }
        });
    }



    // 条目点击事件
    @Override
    public void onStartOk(int type, int position) {
        switch (type){
            case HomeEntity.HOME_TYPE_RECOMMEND:
                ARouter.getInstance().build(Constant.ROUTER_PATH_DETAILS_PAGE_ACTIVITY)
                        .withSerializable("msg",homeEntity.getResult().getRecommend_info().get(position))
                        .navigation();
                break;
        }
    }
}
