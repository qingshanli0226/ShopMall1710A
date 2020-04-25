package com.example.shopmall.shopmall1710a.car;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;

import java.util.List;

public class CarFragment extends BaseFragment<ResultBean> {
    @Override
    protected void initData() {

    }

    @Override
    protected List<IPresenter<ResultBean>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, ResultBean data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
