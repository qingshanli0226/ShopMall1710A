package com.example.shopmall.shopmall1710a.fragment.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.fragment.home.adapter.ChannelInfoAdapter;
import com.example.shopmall.shopmall1710a.fragment.home.adapter.HomeAdapter;
import com.example.shopmall.shopmall1710a.fragment.home.adapter.RecommendInfoAdapter;
import com.example.shopmall.shopmall1710a.fragment.home.adapter.SeckillInfoAdapter;
import com.example.shopmall.shopmall1710a.fragment.home.entity.HomeBean;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<Object> implements CacheManager.IHomeDataListener {

    private RecyclerView recyclerView;
    private List<HomeBean.ResultBean.HotInfoBean> hotInfoBeanList = new ArrayList<>();
    private List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeanList = new ArrayList<>();
    private List<HomeBean.ResultBean.SeckillInfoBean.ListBean> seckillInfoBeanList = new ArrayList<>();
    private List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeanList = new ArrayList<>();
    private List<String> bannerInfoBeanList = new ArrayList<>();
    private List<String> bannerInfoBeanList2 = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private ChannelInfoAdapter channelInfoAdapter;
    private SeckillInfoAdapter seckillInfoAdapter;
    private RecommendInfoAdapter recommendInfoAdapter;
    private Banner banner;
    private RecyclerView rvChannelInfo;
    private Banner bannerActInfo;
    private RecyclerView rvSeckillInfo;
    private RecyclerView rvRecommendInfo;
    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
//        inflate = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
//        MyToolBar myToolBar = inflate.findViewById(R.id.myToolBar);
//        myToolBar.setToolBarClickListener(this);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        banner = inflate.findViewById(R.id.banner);
        rvChannelInfo = inflate.findViewById(R.id.rv_channel_info);
        bannerActInfo = inflate.findViewById(R.id.banner_act_info);
        rvSeckillInfo = inflate.findViewById(R.id.rv_seckill_info);
        rvRecommendInfo = inflate.findViewById(R.id.rv_recommend_info);
        //设置适配器 （rvRecommendInfo）
        recommendInfoAdapter = new RecommendInfoAdapter(R.layout.item_seckillinfo, recommendInfoBeanList);
        rvRecommendInfo.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        rvRecommendInfo.setAdapter(recommendInfoAdapter);
        //设置适配器 （rvSeckillInfo）
        seckillInfoAdapter = new SeckillInfoAdapter(R.layout.item_seckillinfo, seckillInfoBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSeckillInfo.setLayoutManager(linearLayoutManager);
        rvSeckillInfo.setAdapter(seckillInfoAdapter);
        //设置适配器 （ChannelInfo）
        channelInfoAdapter = new ChannelInfoAdapter(R.layout.item_channelinfo, channelInfoBeanList);
        rvChannelInfo.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
        rvChannelInfo.setAdapter(channelInfoAdapter);
        //设置适配器 （HotInfo）
        homeAdapter = new HomeAdapter(R.layout.item, hotInfoBeanList);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(homeAdapter);
        //设置 banner 轮播图
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setDelayTime(5000);
        //设置 bannerActInfo 轮播图
        bannerActInfo.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        bannerActInfo.setDelayTime(10000);
    }

    @Override
    protected void initData() {
        //获取首页数据,将获取的数据展示出来
        String homeDataStr = CacheManager.getInstance().getHomeData();
        if (homeDataStr == null) {
            showLoading();
        } else {
            HomeBean homeBean = new Gson().fromJson(homeDataStr, HomeBean.class);
            // rvRecommendBean
            recommendInfoBeanList.addAll(homeBean.getResult().getRecommend_info());
            recommendInfoAdapter.notifyDataSetChanged();
            // seckillInfoBean
            seckillInfoBeanList.addAll(homeBean.getResult().getSeckill_info().getList());
            seckillInfoAdapter.notifyDataSetChanged();
            // hotInfoBean
            hotInfoBeanList.addAll(homeBean.getResult().getHot_info());
            homeAdapter.notifyDataSetChanged();
            // hotInfoBean
            channelInfoBeanList.addAll(homeBean.getResult().getChannel_info());
            channelInfoAdapter.notifyDataSetChanged();
            //bannerInfoBean
            for (int i = 0; i < homeBean.getResult().getBanner_info().size(); i++) {
                bannerInfoBeanList.add("http://49.233.93.155:8080/atguigu/img/"+homeBean.getResult().getBanner_info().get(i).getImage());
            }
            banner.setImages(bannerInfoBeanList);
            banner.start();
            //bannerActInfo
            for (int i = 0; i < homeBean.getResult().getAct_info().size(); i++) {
                bannerInfoBeanList2.add("http://49.233.93.155:8080/atguigu/img/"+homeBean.getResult().getAct_info().get(i).getIcon_url());
            }
            bannerActInfo.setImages(bannerInfoBeanList2);
            bannerActInfo.start();
        }

        CacheManager.getInstance().registerIHomeDataListener(this);

    }

    @Override
    protected void destroy() {
        //注销listener，避免内存泄漏
        CacheManager.getInstance().unRegisterIHomeDataListener();
    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        Toast.makeText(getContext(), "获取数据成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(getContext(), "获取数据失败"+errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHomeDataReceived(final String homeDataJson) {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                HomeBean homeBean = new Gson().fromJson(homeDataJson, HomeBean.class);
//                hotInfoBeanList.addAll(homeBean.getResult().getHot_info());
//                homeAdapter.notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public void onLeftImgClick() {
        Toast.makeText(getContext(), "dd", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightImgClick() {
        Toast.makeText(getContext(), "dd", Toast.LENGTH_SHORT).show();
    }
}
