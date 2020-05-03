package com.example.shopmall.shopmall1710a.main.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseFragment;
import com.example.shopmall.framework.base.view.IBaseView;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.ActAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.ChannelAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.RecommendAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.SeckillAdapter;
import com.example.shopmall.shopmall1710a.main.bean.Goods;
import com.example.shopmall.shopmall1710a.main.bean.GoodsBean;
import com.example.shopmall.shopmall1710a.main.presenter.HomePresenter;
import com.example.shopmall.shopmall1710a.main.view.activity.GoodDetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFrag extends BaseFragment<HomePresenter, Goods> implements IBaseView<Goods>, CacheManager.IHomeDataListener, CacheManager.IShopCountRecevedLisener {

    private HomePresenter homePresenter;


    private LinearLayout linHome;
    private TextView tvSearchHome;
    private TextView tvMessageHome;
    private Banner homeBanner;
    private RecyclerView homeRecycler;
    private RecyclerView homeRecycler2;
    private RecyclerView homeRecycler3;
    private ProgressBar progressBar;
    private ImageButton ibTop;

    private ChannelAdapter channelAdapter;
    private ActAdapter actAdapter;
    private SeckillAdapter seckillAdapter;
    private RecommendAdapter recommendAdapter;

    private List<Goods.ChannelInfoBean> channelList = new ArrayList<>();
    private List<Goods.ActInfoBean> actList = new ArrayList<>();
    private List<Goods.SeckillInfoBean.ListBean> secList = new ArrayList<>();
    private List<Goods.RecommendInfoBean> recList = new ArrayList<>();

    private List<String> banlist = new ArrayList<>();
    private RecyclerView homeRecycler4;

    @Override
    public int bindLayout() {
        return R.layout.frag_home;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {

        linHome = findViewById(R.id.lin_home);
        tvSearchHome = findViewById(R.id.tv_search_home);
        tvMessageHome = findViewById(R.id.tv_message_home);
        homeBanner = findViewById(R.id.home_banner);
        homeRecycler = findViewById(R.id.home_recycler);
        homeRecycler2 = findViewById(R.id.home_recycler2);
        homeRecycler3 = findViewById(R.id.home_recycler3);
        progressBar = findViewById(R.id.progressBar);
        homeRecycler4 = findViewById(R.id.home_recycler4);
        ibTop = findViewById(R.id.ib_top);

        channelAdapter = new ChannelAdapter(R.layout.item_home_channel, channelList);
        homeRecycler.setAdapter(channelAdapter);
        homeRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        actAdapter = new ActAdapter(R.layout.item_home_act, actList);
        homeRecycler4.setAdapter(actAdapter);
        homeRecycler4.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        seckillAdapter = new SeckillAdapter(R.layout.item_home_seckill, secList);
        homeRecycler2.setAdapter(seckillAdapter);
        homeRecycler2.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        recommendAdapter = new RecommendAdapter(R.layout.item_home_recommend, recList);
        homeRecycler3.setAdapter(recommendAdapter);
        homeRecycler3.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));


    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        String homeDataStr = CacheManager.getInstance().getHomeData(getActivity());
        if (homeDataStr == null) {
            showLoading();
        } else {
            processHomeBean(homeDataStr);
        }

        CacheManager.getInstance().registerIHomeDataListener(this);


        Log.d("LAE", "initData: ");
        homePresenter = new HomePresenter();
        homePresenter.attachView(this);
        Log.d("LMQ", "onHtttpReceived: 1236");
        homePresenter.getHttpData(0);
        Log.d("LMQ", "onHtttpReceived: 1238");
    }

    public void processHomeBean(String homeDataJson) {
        Goods good = new Gson().fromJson(homeDataJson, new TypeToken<Goods>() {
        }.getType());
    }


    @Override
    public void onHtttpReceived(int requestCode, Goods data) {
        Log.d("LAE", "onHtttpReceived: " + data);
        Log.d("LMQ", "onHtttpReceived: 1237");
        List<Goods.BannerInfoBean> banner_info = data.getBanner_info();
        initAdapter(data);
        initBanner(banner_info);


    }


    private void initAdapter(Goods data) {
        channelList.clear();
        actList.clear();
        secList.clear();
        recList.clear();

        channelList.addAll(data.getChannel_info());
        actList.addAll(data.getAct_info());
        secList.addAll(data.getSeckill_info().getList());
        recList.addAll(data.getRecommend_info());

        recommendAdapter.notifyDataSetChanged();
        actAdapter.notifyDataSetChanged();
        channelAdapter.notifyDataSetChanged();
        seckillAdapter.notifyDataSetChanged();


        recommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                Goods.RecommendInfoBean recommendInfoBean = recList.get(position);
                intent.putExtra("goods", recommendInfoBean);
                startActivity(intent);
            }
        });

    }

    private void initBanner(List<Goods.BannerInfoBean> banner_info) {
        for (int i = 0; i < banner_info.size(); i++) {
            banlist.add(Constant.BASE_URL_IMG + banner_info.get(i).getImage());
        }

        homeBanner.setImages(banlist);
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        homeBanner.start();
    }


    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onHomeDataReceived(final String homeDataJson) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                processHomeBean(homeDataJson);
            }
        });
    }

    @Override
    public void onShopcarCountReceived(int conunt) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterShopCountListener(this);
    }
}
