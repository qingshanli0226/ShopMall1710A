package com.example.shopmall.buy;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;

import java.util.List;

public class OrderActivity extends BaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
