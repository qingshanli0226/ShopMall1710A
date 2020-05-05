package com.example.shopmall.shopmall1710a.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.adapter.MyAdapter;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.example.shopmall.shopmall1710a.home.presenter.BannerPresenter;
import com.example.shopmall.shopmall1710a.search.view.SearchActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<ResultBean> implements CacheManager.IHomeDataListener {
    private RecyclerView reView;
    BannerPresenter bannerPresenter;
    MyAdapter myAdapter;
    BaseBean<ResultBean> resultBean;
    private Handler handler=new Handler();
    private android.widget.EditText editPrint;

    @Override
    protected void initData() {
       // bannerPresenter.getHttpData(1);

        String homeData = CacheManager.getInstance().getHomeData();
        if (homeData==null){
            showLoading();
        }
        resultBean=new Gson().fromJson(homeData,new TypeToken<BaseBean<ResultBean>>(){}.getType());
//        Log.i("tag",homeData);

        myAdapter=new MyAdapter(getContext(),resultBean.getResult());
        reView.setAdapter(myAdapter);
        CacheManager.getInstance().registerIHomeListener(this);

    }

    @Override
    protected List<IPresenter<ResultBean>> getPresenter() {
        ArrayList<IPresenter<ResultBean>> iPresenters = new ArrayList<>();
        bannerPresenter = new BannerPresenter();
        iPresenters.add(bannerPresenter);
        return iPresenters;
    }

    @Override
    protected void initView() {
        reView = (RecyclerView) rootView.findViewById(R.id.re_view);
        loadingBar = rootView.findViewById(R.id.progressBar);
        editPrint = rootView.findViewById(R.id.edit_print);
        reView.setLayoutManager(new LinearLayoutManager(getContext()));
        toorBar.showAll(false);
        toorBar.showRightImg(true);
        toorBar.showRightTv(true);
        toorBar.setRightImg(R.drawable.new_message_icon);
        toorBar.setRightTv(R.string.home_right_tv);
        toorBar.setTextSize(R.id.right_tv,14);

        editPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }

    @Override
    protected void destroy() {
       // bannerPresenter.detachView();
        CacheManager.getInstance().unRegisterIHomeListener();
    }


    @Override
    public void onHtttpReceived(int requestCode, ResultBean data) {
        Log.i("TAG", "onHtttpReceived: "+data.toString());

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onHomeData(final String json) {
        Log.i("TAG11111111111", "onHomeData: "+json);

        handler.post(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                myAdapter.notifyDataSetChanged();
            }
        });
    }
}
