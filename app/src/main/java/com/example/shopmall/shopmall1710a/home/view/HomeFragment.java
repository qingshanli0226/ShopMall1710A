package com.example.shopmall.shopmall1710a.main.view.frg;

import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;
/*
 首頁
 */
public class HomeFragment extends BaseFragment {


    private RecyclerView home_rv;

    @Override
    public int bindLayout() {
        return R.layout.frg_home;
    }

    @Override
    public void initView() {
        home_rv = (RecyclerView) findViewById(R.id.home_rv);
    }

    @Override
    public void initData() {

    }

    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
