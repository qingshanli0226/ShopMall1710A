package com.example.shopmall.shopmall1710a.main.view.activity;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseActivity;
import com.example.shopmall.framework.base.view.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.SearchAdapter;
import com.example.shopmall.shopmall1710a.main.bean.SearchBean;
import com.example.shopmall.shopmall1710a.main.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActicvity extends BaseActivity<SearchPresenter, SearchBean> implements IBaseView<SearchBean>, View.OnClickListener {
    private android.widget.EditText actSearchSearchEdit;
    private android.widget.TextView actSearchSearch;
    private androidx.recyclerview.widget.RecyclerView actSearchRecycler;
    private SearchAdapter adapter;
    private List<SearchBean.ResultBean.HotProductListBean> listBeans = new ArrayList<>();
    private SearchPresenter presenter;

    public static String names = null;

    @Override
    public int bindLayout() {
        return R.layout.act_search;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {


        presenter = new SearchPresenter();
        presenter.attachView(this);

        actSearchSearchEdit = findViewById(R.id.act_search_search_edit);
        actSearchSearch = findViewById(R.id.act_search_search);
        actSearchRecycler = findViewById(R.id.act_search_recycler);

        adapter = new SearchAdapter(R.layout.item_search, listBeans);
        actSearchRecycler.setAdapter(adapter);
        actSearchRecycler.setLayoutManager(new LinearLayoutManager(this));

        actSearchSearch.setOnClickListener(this);

    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onHtttpReceived(int requestCode, SearchBean data) {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_search_search:

                names = actSearchSearchEdit.getText().toString();
                presenter.addParams(names);
                presenter.getHttpData(120);
                break;
        }
    }
}
