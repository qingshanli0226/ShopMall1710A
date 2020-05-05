package com.example.shopmall.shopmall1710a.search.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.search.HotSearchFragment;
import com.example.shopmall.shopmall1710a.search.SearchDisFragment;
import com.example.shopmall.shopmall1710a.search.adapter.RecordAdapter;
import com.example.shopmall.shopmall1710a.search.adapter.SearchProAdapter;
import com.example.shopmall.shopmall1710a.search.mode.SearchResultBean;
import com.example.shopmall.shopmall1710a.search.presenter.SearchPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<Object>{
    private List<SearchResultBean.HotProductListBean> list = new ArrayList<>();
    private SearchProAdapter searchProAdapter;
    private List<String> records = new ArrayList<>();
    private RecordAdapter recordAdapter;
    private List<String> tabs = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private android.widget.EditText editPrint;
    private android.widget.LinearLayout showRecords;
    private android.support.v7.widget.RecyclerView recordShow;
    private android.support.design.widget.TabLayout tab;
    private android.support.v4.view.ViewPager pager;
    private LinearLayout searchShow;
    private android.support.v7.widget.RecyclerView searchResult;

    @Override
    protected void initData() {
        String historyRecord = SpUtil.getHistoryRecord(this);
        if (historyRecord!=null){
            List<String> record = new Gson().fromJson(historyRecord, new TypeToken<List<String>>() {
            }.getType());
            records.addAll(record);

        }
        tabs.add("搜索发现");
        tabs.add("热搜榜");
        fragments.add(new SearchDisFragment());
        fragments.add(new HotSearchFragment());
        setPager();
    }

    private void setPager() {
        tab.setupWithViewPager(pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs.get(position);
            }
        });
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        showRecords = findViewById(R.id.showRecords);
        recordShow = findViewById(R.id.recordShow);
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        editPrint = findViewById(R.id.edit_print);
        searchShow = findViewById(R.id.searchShow);
        searchResult = findViewById(R.id.searchResult);

        toorBar.showAll(false);
        toorBar.showLeftImg(true);
        toorBar.showRightTv(true);
        toorBar.setRightTv(R.string.search);
        toorBar.setTextSize(R.id.right_tv,20);
        recordAdapter = new RecordAdapter(R.layout.record_item,records);
        recordShow.setLayoutManager(new GridLayoutManager(this,3));
        recordShow.setAdapter(recordAdapter);

        searchProAdapter = new SearchProAdapter(R.layout.search_item,list);
        searchResult.setLayoutManager(new LinearLayoutManager(this));
        searchResult.setAdapter(searchProAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode==5){
            List<SearchResultBean> data1 = (List<SearchResultBean>) data;
            List<SearchResultBean.HotProductListBean> hot_product_list = data1.get(0).getHot_product_list();
            Log.e("TAG", "guofeng: "+hot_product_list.size());
            list.addAll(hot_product_list);
            searchProAdapter.notifyDataSetChanged();
//            recordShow.setVisibility(View.GONE);
            searchResult.setVisibility(View.VISIBLE);
            pager.setVisibility(View.GONE);
            tab.setVisibility(View.GONE);
            showRecords.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onRightTvClick() {
        super.onRightTvClick();
        String string = editPrint.getText().toString();
        records.add(string);
        String s = new Gson().toJson(records);
        SpUtil.saveHistoryRecord(this,s);
        getSearchResult(string);
    }

    private void getSearchResult(String string) {
        Log.e("TAG", "getSearchResult: " );
        SearchPresenter searchPresenter = new SearchPresenter();
        searchPresenter.attachView(this);
        searchPresenter.addParam(string);
        searchPresenter.getHttpData(5);
    }
}
