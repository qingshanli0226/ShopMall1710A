package com.example.shopmall.shopmall1710a.home.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.HomeAdapter;
import com.example.shopmall.shopmall1710a.home.model.HomeEntity;
import com.example.shopmall.shopmall1710a.home.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter,List<HomeEntity>> {
    private HomePresenter mHomePresenter;

    private EditText fragment_home_find_et;
    private Button fragment_home_find_btn;
    private Banner fragment_home_find_banner;
    private RecyclerView fragment_home_find_rv_head;
    private ImageView fragment_home_find_img;
    private TextView fragment_home_find_time;
    private RecyclerView fragment_home_find_rv_center;
    private TextView fragment_home_find_tuijian;
    private RecyclerView fragment_home_find_rv_bottom;

    private ArrayList<String> list_banner = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();

    private ArrayList<HomeEntity> list_rvHead = new ArrayList<>();
    private HomeAdapter mHomeAdapter;
    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mHomePresenter = new HomePresenter();
        mHomePresenter.attachView(this);
        mHomePresenter.getHttpData(0);

        fragment_home_find_banner = findViewById(R.id.fragment_home_find_banner);
        fragment_home_find_rv_head = findViewById(R.id.fragment_home_find_rv_head);
        fragment_home_find_rv_center = findViewById(R.id.fragment_home_find_rv_center);
        fragment_home_find_rv_bottom = findViewById(R.id.fragment_home_find_rv_bottom);
        fragment_home_find_img = findViewById(R.id.fragment_home_find_img);
        initBanner();

        mHomeAdapter = new HomeAdapter(R.layout.layout_view_home_rv,list_rvHead);
        fragment_home_find_rv_head.setAdapter(mHomeAdapter);
        fragment_home_find_rv_head.setLayoutManager(new GridLayoutManager(getActivity(),6));

        fragment_home_find_rv_center.setAdapter(mHomeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment_home_find_rv_center.setLayoutManager(linearLayoutManager);

        fragment_home_find_rv_bottom.setAdapter(mHomeAdapter);
        fragment_home_find_rv_bottom.setLayoutManager(new GridLayoutManager(getActivity(),3));

        Glide.with(getActivity()).load("https://i04piccdn.sogoucdn.com/8944381c84e05df4").into(fragment_home_find_img);
    }



    private void initBanner() {
        list_title.add("");
        list_title.add("");
        list_title.add("");
        list_banner.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1587565748&di=e1125e2c8d9966a4e7724cf2b83a3c9f&src=http://5b0988e595225.cdn.sohucs.com/images/20180427/4256d47e4cbe4c489f598eb4002ba0ef.jpeg");
        list_banner.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2201407865,2073162312&fm=26&gp=0.jpg");
        list_banner.add("http://img4.imgtn.bdimg.com/it/u=3896121372,2760926030&fm=11&gp=0.jpg");
        fragment_home_find_banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//数字加标题
        //设置图片加载器
        fragment_home_find_banner.setImageLoader(new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View imageView) {
                Glide.with(context).load(path).into((ImageView) imageView);
            }

            @Override
            public View createImageView(Context context) {
                return new ImageView(context);
            }
        });
        //设置图片集合
        fragment_home_find_banner.setBannerTitles(list_title);
        fragment_home_find_banner.setImages(list_banner);
        //设置自动轮播，默认为true
        fragment_home_find_banner.isAutoPlay(true);
        //设置轮播时间
        fragment_home_find_banner.setDelayTime(1500);
        //banner设置方法全部调用完毕时最后调用
        fragment_home_find_banner.start();
    }

    @Override
    public void initData() {
    }


    @Override
    public void onHttpReceived(int requestCode, List<HomeEntity> data) {
        list_rvHead.addAll(data);
        mHomeAdapter.notifyDataSetChanged();
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
    public void onDestroy() {
        super.onDestroy();
        mHomePresenter.detachView();
    }
}
