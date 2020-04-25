package com.example.shopmall.shopmall1710a.home;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.net.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.HomeAdapter;
import com.example.shopmall.shopmall1710a.home.mode.ResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<ResultBean> implements CacheManager.IHomeDataListener {


    private RecyclerView reView;
    private ProgressBar loadingBar;
    private ArrayList<IPresenter<ResultBean>> iPresenters = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private HomePresenter homePresenter;
    private Handler handler = new Handler();
    private BaseBean<ResultBean> baseBean;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        reView = rootview.findViewById(R.id.re_view);
        loadingBar = rootview.findViewById(R.id.loadingBar);
        toorBar.showAll(false);
        toorBar.showRightImg(true);
        toorBar.showRightTv(true);
        toorBar.setRightImg(R.drawable.new_message_icon);
        toorBar.setRightTv(R.string.home_right_tv);
        toorBar.setTextSize(R.id.right_tv,14);


    }

    @Override
    protected void initData() {
//        homePresenter.getHttpData(0);

        String homeData = CacheManager.getInstance().getHomeData();
        Log.e("guofeng", homeData.length() + "");
        if (homeData == null) {
            showLoading();
        }
        baseBean = new Gson().fromJson(homeData, new TypeToken<BaseBean<ResultBean>>() {
        }.getType());
        homeAdapter = new HomeAdapter(getContext(), baseBean.getResult());
        reView.setAdapter(homeAdapter);
        reView.setLayoutManager(new LinearLayoutManager(getContext()));
        CacheManager.getInstance().registerIHomeListener(this);

    }

    @Override
    protected List<IPresenter<ResultBean>> getPresenter() {
//        homePresenter = new HomePresenter();
//        iPresenters.add(homePresenter);
        return iPresenters;
    }


    @Override
    protected void destroy() {
        CacheManager.getInstance().unRegisterIHomeListener();
    }


    @Override
    public void onHtttpReceived(int requestCode, ResultBean data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(getContext(), "请求数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHomeData(String json) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                hideLoading();
            }
        });
    }


}
