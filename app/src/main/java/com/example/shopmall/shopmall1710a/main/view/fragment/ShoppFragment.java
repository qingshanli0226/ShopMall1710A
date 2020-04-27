package com.example.shopmall.shopmall1710a.main.view.fragment;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.ShortCarAdapter;
import com.example.shopmall.shopmall1710a.main.entity.ShopCarEntity;
import com.example.shopmall.shopmall1710a.main.presenter.GetShortCarPresenter;

import java.util.ArrayList;
import java.util.List;

public class ShoppFragment extends BaseFragment{
    private GetShortCarPresenter getShortCarPresenter;
    private RecyclerView shoppRecyclerView;
    private ShortCarAdapter shortCarAdapter;
    private List<ShopCarEntity> lists;
    @Override
   public int bindLayout() {
        return R.layout.fragment_shopp ;
    }

    @Override
    public void initView() {
        shoppRecyclerView = (RecyclerView) findViewById(R.id.shoppRecyclerView);
        shoppRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lists = new ArrayList<>();
    }

    @Override
    public void initData() {
        getShortCarPresenter.getHttpData(100);
    }

    @Override
    public List<IPresenter> getPresenter() {
        List<IPresenter> list = new ArrayList<>();
        getShortCarPresenter = new GetShortCarPresenter(this);
        list.add(getShortCarPresenter);
        return list;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        Log.i("boss", "onHttpReceived: 购物车页面");
        if (requestCode == 100){
            List<ShopCarEntity> shopCarEntity = (List<ShopCarEntity>) data;
            Log.i("boss", "onHttpReceived: 购物车页面"+shopCarEntity.size());
            lists.clear();
            lists.addAll(shopCarEntity);
            if (shortCarAdapter == null){
                Log.i("boss", "onHttpReceived: 购物车"+lists.size());
                shortCarAdapter = new ShortCarAdapter(lists);
                shoppRecyclerView.setAdapter(shortCarAdapter);
            }else {
                shortCarAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Log.i("boss", "onHttpReceivedFailed: 购物车页面 "+errorBean.getErrorMessage());
    }


}
