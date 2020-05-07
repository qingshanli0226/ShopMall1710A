package com.example.shopmall.shopmall1710a.type;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.adapter.DataAdapter;
import com.example.shopmall.shopmall1710a.type.adapter.TypeAdapter;
import com.example.shopmall.shopmall1710a.type.mode.DataBean;
import com.example.shopmall.shopmall1710a.type.mode.TypeBean;
import com.example.shopmall.shopmall1710a.type.presenter.DataPresenter;
import com.example.shopmall.shopmall1710a.type.presenter.TypePresenter;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment<Object> {
    private List<TypeBean> list = new ArrayList<>();
    private android.support.v7.widget.RecyclerView proType;
    private android.support.v7.widget.RecyclerView productShow;
    private TypeAdapter typeAdapter;
    private DataAdapter dataAdapter;
    private DataBean dataBean;

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
        proType = rootView.findViewById(R.id.proType);
        productShow = rootView.findViewById(R.id.productShow);
        typeAdapter = new TypeAdapter(R.layout.item_type,list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        proType.setLayoutManager(linearLayoutManager);
        proType.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getData(position);
            }
        });


    }

    private void getData(int position) {
        DataPresenter dataPresenter = new DataPresenter(list.get(position).getUrl());
        dataPresenter.attachView(this);
        dataPresenter.getHttpData(200);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.typefragment;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode==100){
            list.addAll((List<TypeBean>) data);
            typeAdapter.notifyDataSetChanged();
            getData(0);
            Log.e("TAG", "onHtttpReceived: "+list.get(0).getName() );
        }
        if (requestCode==200){
            List<DataBean> data1 = (List<DataBean>) data;
            Log.e("TAG", "onHtttpReceived: "+data1.get(0).getChild().size() );
            dataBean = data1.get(0);
//            dataAdapter = new DataAdapter(getContext(),dataBean);
//            productShow.setLayoutManager(new LinearLayoutManager(getContext()));
//            productShow.setAdapter(dataAdapter);
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
