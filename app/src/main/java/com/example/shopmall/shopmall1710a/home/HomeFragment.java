package com.example.shopmall.shopmall1710a.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.MyAdapter;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.example.shopmall.shopmall1710a.home.presenter.BannerPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<ResultBean> {
    private RecyclerView reView;
    BannerPresenter bannerPresenter;
    MyAdapter myAdapter;
    @Override
    protected void initData() {
        bannerPresenter.getHttpData(1);
    }

    @Override
    protected List<IPresenter<ResultBean>> getPresenter() {
        ArrayList<IPresenter<ResultBean>> iPresenters = new ArrayList<>();

        iPresenters.add(bannerPresenter);
        return iPresenters;
    }

    @Override
    protected void initView() {
        bannerPresenter = new BannerPresenter();
        reView = (RecyclerView) rootView.findViewById(R.id.re_view);
        loadingBar = rootView.findViewById(R.id.progressBar);
        reView.setLayoutManager(new LinearLayoutManager(getContext()));


        bannerPresenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }

    @Override
    protected void destroy() {
        bannerPresenter.detachView();
    }


    @Override
    public void onHtttpReceived(int requestCode, ResultBean data) {
        Log.i("TAG", "onHtttpReceived: "+data.toString());
        myAdapter=new MyAdapter(getContext(),data);
        reView.setAdapter(myAdapter);
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
