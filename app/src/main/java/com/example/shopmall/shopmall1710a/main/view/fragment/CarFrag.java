package com.example.shopmall.shopmall1710a.main.view.fragment;


import android.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtill;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.framework.base.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.CartAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CarFrag extends BaseFragment {


    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private LinearLayout llEmptyShopcart;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    private List<ShopCartBean.ResultBean> list = new ArrayList<>();
    private SpUtill spUtill;
    private CartAdapter cartAdapter;

    @Override
    public int bindLayout() {
        return R.layout.frag_car;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {
        spUtill = new SpUtill(getContext());
        tvShopcartEdit = (TextView) findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);
        llEmptyShopcart = (LinearLayout) findViewById(R.id.ll_empty_shopcart);
        ivEmpty = (ImageView) findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) findViewById(R.id.tv_empty_cart_tobuy);

        cartAdapter = new CartAdapter(R.layout.cart_item, list);
        recyclerview.setAdapter(cartAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        list.clear();
        String shopcartData = spUtill.getShopcartData();

        if (shopcartData != null) {
            List<ShopCartBean.ResultBean> result = new Gson().fromJson(shopcartData, ShopCartBean.class).getResult();
            list.addAll(result);
            cartAdapter.notifyDataSetChanged();
        }


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
