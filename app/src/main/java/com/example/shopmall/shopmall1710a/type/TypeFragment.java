package com.example.shopmall.shopmall1710a.type;

import android.support.v4.app.Fragment;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

public class TypeFragment extends BaseFragment<Fragment> {
    @Override
    protected void initData() {

    }

    @Override
    protected List<IPresenter<Fragment>> getPresenter() {
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
    public void onHtttpReceived(int requestCode, Fragment data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
