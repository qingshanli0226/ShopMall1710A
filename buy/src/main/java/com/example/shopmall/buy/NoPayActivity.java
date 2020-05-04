package com.example.shopmall.buy;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.buy.adapter.NoPayAdapter;
import com.example.shopmall.buy.entity.NoPayEntity;
import com.example.shopmall.buy.presenter.FindForPayPresenter;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NoPayActivity extends BaseActivity {
    private RecyclerView noPayRecyclerView;
    private FindForPayPresenter findForPayPresenter;
    private NoPayAdapter noPayAdapter;
    @Override
    public int bindLayout() {
        return R.layout.activity_nopay;
    }

    @Override
    public void initView() {
        noPayRecyclerView = findViewById(R.id.noPayRecyclerView);
        noPayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        findForPayPresenter.getHttpData(100);
    }

    @Override
    public List<IPresenter> getPresenter() {
        List<IPresenter> list = new ArrayList<>();
        list.add(findForPayPresenter = new FindForPayPresenter(this));
        return list;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        if (requestCode == 100){
            if (noPayAdapter == null){
                List<NoPayEntity> list = (List<NoPayEntity>) data;
                Log.i("boss", "onHttpReceived: 待付款信息"+list.size());
                noPayAdapter = new NoPayAdapter(list);
                noPayRecyclerView.setAdapter(noPayAdapter);
            }
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Log.i("boss", "onHttpReceivedFailed: "+errorBean.getErrorMessage());
    }
}
