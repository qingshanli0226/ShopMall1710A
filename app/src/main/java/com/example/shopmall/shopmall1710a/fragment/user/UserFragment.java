package com.example.shopmall.shopmall1710a.fragment.user;

import android.content.Intent;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.view.BetterLoginActivity;

import java.util.List;

public class UserFragment extends BaseFragment {
    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onRightImgClick() {
        Intent intent = new Intent(getContext(), BetterLoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData() {

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
}
