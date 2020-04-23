package com.example.shopmall.shopmall1710a.main.view.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseFragment;
import com.example.shopmall.framework.base.view.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.ActAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.ChannelAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.RecommendAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.SeckillAdapter;
import com.example.shopmall.shopmall1710a.main.bean.Goods;
import com.example.shopmall.shopmall1710a.main.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFrag extends BaseFragment<HomePresenter, Goods> implements IBaseView<Goods> {

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
        homeRecycler.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));

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
        Log.d("LAE", "initData: ");
        homePresenter = new HomePresenter();
        homePresenter.attachView(this);
        homePresenter.getHttpData(0);

    }

    @Override
    public void onHtttpReceived(int requestCode, Goods data) {
        Log.d("LAE", "onHtttpReceived: " + data);

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
}
