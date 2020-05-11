package com.example.shopmall.shopmall1710a.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.bean.ShopCartBean;
import com.example.shopmall.framework.manager.ConnectManager;
import com.example.shopmall.framework.message.ShopMallMessage;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.HomeBean;
import com.example.shopmall.shopmall1710a.message.ShopMallMessageActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class HomeFragment extends BaseFragment<Object> implements CacheManager.IShopcarDataRecevedLisener,CacheManager.IHomeDataListener, CacheManager.IShopMessageChangedListener {
    private TextView countTV;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private TextView connectFailedTv;

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

        //先获取一下未读的消息数据
        int countUnreadMessage = CacheManager.getInstance().getCountUnReadMessage();
        updateUnReadCountTv(countUnreadMessage);
        //去监听消息数据的变化
        CacheManager.getInstance().registerShopMessageChangedListener(this);

        CacheManager.getInstance().registerIHomeDataListener(this);

        //一进来先进行网络判断
        if (!ConnectManager.getInstance().isConnected()) {
            connectFailedTv.setVisibility(View.VISIBLE);
        }

    }

    public void processHomeBean(String homeDataJson) {
        HomeBean homeBean = new Gson().fromJson(homeDataJson, new TypeToken<HomeBean>(){}.getType());
        homeAdapter.addData(homeBean.getResult().getHot_info());
    }

    @Override
    protected void initView(View rootView) {
        countTV = rootView.findViewById(R.id.count);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        homeAdapter = new HomeAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(homeAdapter);
        connectFailedTv = rootView.findViewById(R.id.connectFailedTv);
    }

    @Override
    protected void initToolBar(View rootView) {
        super.initToolBar(rootView);
        toolBar.showRightTv(false);
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
            countTV.setText(String.valueOf(CacheManager.getInstance().getShopcarCount()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterShopCountListener(this);
        CacheManager.getInstance().unRegisterIHomeDataListener();
        CacheManager.getInstance().unRegisterShopMessageChangedListener(this);
    }

    @Override
    public void onShopcarDataReceived(int conunt, ShopCartBean shopCartBean, int index) {
        countTV.post(new Runnable() {
            @Override
            public void run() {
                countTV.setText(String.valueOf(CacheManager.getInstance().getShopcarCount()));
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

    @Override
    public void onRightClick() {
        super.onRightClick();
        ShopMallMessageActivity.launch(getActivity());
    }

    @Override
    public void onShopMessageChanged(List<ShopMallMessage> shopMallMessageList, int unReadCount, int index) {
        updateUnReadCountTv(unReadCount);
    }

    @Override
    public void onShopMessageAdded(ShopMallMessage shopMallMessage, int unReadCount, int index) {
        updateUnReadCountTv(unReadCount);
    }

    @Override
    public void onShopMessageDelted(ShopMallMessage shopMallMessage, int unReadCount, int index) {
        updateUnReadCountTv(unReadCount);
    }

    @Override
    public void onShopMessageUpdated(ShopMallMessage shopMallMessage, int unReadCount, int index) {
        updateUnReadCountTv(unReadCount);
    }

    private void updateUnReadCountTv(final int unReadCount){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unReadCount!=0) {
                    toolBar.showRightTv(true);
                    toolBar.setRightTvContent(unReadCount+"");
                } else {
                    toolBar.showRightTv(false);
                }
            }
        });
    }

    @Override
    public void onConnected() {
        connectFailedTv.setVisibility(View.GONE);
    }

    @Override
    public void onDisconnect() {
        connectFailedTv.setVisibility(View.VISIBLE);
    }
}
