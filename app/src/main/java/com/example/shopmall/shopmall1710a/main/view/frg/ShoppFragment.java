package com.example.shopmall.shopmall1710a.main.view.frg;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class ShoppFragment extends BaseFragment {
    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
   public int bindLayout() {
        return R.layout.frg_shopp ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
