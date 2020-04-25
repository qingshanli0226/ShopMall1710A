package com.example.shopmall.shopmall1710a.type;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.net.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.HomePresenter;
import com.example.shopmall.shopmall1710a.home.adapter.HomeAdapter;
import com.example.shopmall.shopmall1710a.home.mode.ResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onLeftImgClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onLeftTvClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}
