package com.example.shopmall.shopmall1710a.search;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.adapter.TypeAdapter;
import com.example.shopmall.shopmall1710a.type.mode.TypeBean;
import com.example.shopmall.shopmall1710a.type.presenter.TypePresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchDisFragment extends BaseFragment<Object> {
    private List<TypeBean> list = new ArrayList<>();
    private TypeAdapter typeAdapter;
    private android.support.v7.widget.RecyclerView showResult;

    @Override
    protected void initData() {
        list.clear();
        TypePresenter typePresenter = new TypePresenter();
        typePresenter.attachView(this);
        typePresenter.getHttpData(100);
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        showResult = rootView.findViewById(R.id.showResult);
        typeAdapter = new TypeAdapter(R.layout.item_type,list);
        showResult.setAdapter(typeAdapter);
        showResult.setLayoutManager(new GridLayoutManager(getContext(),2));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_searchdiscover;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode==100){
            list.addAll((List<TypeBean>) data);
            typeAdapter.notifyDataSetChanged();
            Log.e("TAG", "onHtttpReceived: "+list.get(0).getName() );
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
