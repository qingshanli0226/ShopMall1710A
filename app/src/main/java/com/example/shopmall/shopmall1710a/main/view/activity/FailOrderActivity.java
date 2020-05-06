package com.example.shopmall.shopmall1710a.main.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtill;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.framework.base.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.FailAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FailOrderActivity extends BaseActivity {
    private androidx.recyclerview.widget.RecyclerView actFailorderRecyclery;
    private List<ShopCartBean.ResultBean> list = new ArrayList<>();
    private List<ShopCartBean.ResultBean> result = new ArrayList<>();
    private SpUtill spUtill;
    private FailAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.act_failorder;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {
        spUtill = new SpUtill(this);
        actFailorderRecyclery = findViewById(R.id.act_failorder_recyclery);
        adapter = new FailAdapter(R.layout.fail_item, list);
        actFailorderRecyclery.setAdapter(adapter);
        actFailorderRecyclery.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView textView = view.findViewById(R.id.fail_order);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FailOrderActivity.this, OrderInfoActivity.class);
                        startActivity(intent);

                    }
                });
            }
        });


    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        list.clear();
        String shopcartData = spUtill.getShopcartData();
        result = new Gson().fromJson(shopcartData, ShopCartBean.class).getResult();
        list.addAll(result);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
