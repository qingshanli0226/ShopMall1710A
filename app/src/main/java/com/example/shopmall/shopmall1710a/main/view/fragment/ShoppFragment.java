package com.example.shopmall.shopmall1710a.main.view.fragment;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class ShoppFragment extends BaseFragment {

    @Override
   public int bindLayout() {
        return R.layout.fragment_shopp ;
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
