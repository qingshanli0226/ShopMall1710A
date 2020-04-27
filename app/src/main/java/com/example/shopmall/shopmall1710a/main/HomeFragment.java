package com.example.shopmall.shopmall1710a.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.HomeBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class HomeFragment extends BaseFragment<Object> implements CacheManager.IShopCountRecevedLisener,CacheManager.IHomeDataListener {
    private TextView countTV;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initData() {
//获取首页数据,将获取的数据展示出来
        String homeDataStr = CacheManager.getInstance().getHomeData(getActivity());
        if (homeDataStr == null) {
            showLoading();
        } else {
            processHomeBean(homeDataStr);
        }

        CacheManager.getInstance().registerIHomeDataListener(this);

    }

    public void processHomeBean(String homeDataJson) {
        HomeBean homeBean = new Gson().fromJson(homeDataJson, new TypeToken<HomeBean>(){}.getType());
        homeAdapter.addData(homeBean.getResult().getHot_info());
    }

    @Override
    protected void initView(View rootView) {
        countTV = rootView.findViewById(R.id.count);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        homeAdapter = new HomeAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CacheManager.getInstance().registerShopCountListener(this);
        //如果登录成功，sp中已经存储了购物产品的数量
        if (ShopUserManager.getInstance().isUserLogin()) {
            countTV.setText(String.valueOf(CacheManager.getInstance().getShopcarCount(getActivity())));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterShopCountListener(this);
    }

    @Override
    public void onShopcarCountReceived(int conunt) {
        countTV.post(new Runnable() {
            @Override
            public void run() {
                countTV.setText(String.valueOf(CacheManager.getInstance().getShopcarCount(getActivity())));
            }
        });
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
}
