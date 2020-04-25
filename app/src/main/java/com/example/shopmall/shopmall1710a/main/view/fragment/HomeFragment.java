package com.example.shopmall.shopmall1710a.main.view.fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

/*
 首頁
 */
@Route(path = Constant.ROUTER_PATH_HOME_FRG)
public class HomeFragment extends BaseFragment {

    @Override
   public int bindLayout() {
        return R.layout.fragment_home ;
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
