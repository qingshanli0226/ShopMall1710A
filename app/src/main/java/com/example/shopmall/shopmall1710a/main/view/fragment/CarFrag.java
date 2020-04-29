package com.example.shopmall.shopmall1710a.main.view.fragment;


import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtill;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.framework.base.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.CartAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CarFrag extends BaseFragment implements View.OnClickListener {


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
    private List<ShopCartBean.ResultBean> result = new ArrayList<>();
    private List<Boolean> blist = new ArrayList<>();
    private SpUtill spUtill;
    private CartAdapter cartAdapter;
    private double sum = 0.00;


    private CheckBox checkBox;

    private int flag = 0;

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

        checkBox = (CheckBox) findViewById(R.id.checkbox_goods);

        cartAdapter = new CartAdapter(R.layout.cart_item, list);
        recyclerview.setAdapter(cartAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));


        tvShopcartEdit.setOnClickListener(this);
        checkboxAll.setOnClickListener(this);
        cbAll.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        list.clear();
        String shopcartData = spUtill.getShopcartData();

        if (shopcartData != null) {
            llEmptyShopcart.setVisibility(View.GONE);
            result = new Gson().fromJson(shopcartData, ShopCartBean.class).getResult();
            list.addAll(result);
            for (int i = 0; i < list.size(); i++) {
                blist.add(true);
            }
            cartAdapter.notifyDataSetChanged();
            for (int i = 0; i < list.size(); i++) {
                if (blist.get(i) == true) {
                    double num = Double.parseDouble(list.get(i).getProductNum());
                    double price = Double.parseDouble(list.get(i).getProductPrice());
                    sum += num * price;
                }
            }
            tvShopcartTotal.setText("￥" + sum);
        } else {
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }

        CBChange();

        cartAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int goodsnum = Integer.parseInt(list.get(position).getProductNum());
                sum = 0;
                switch (view.getId()) {
                    case R.id.goods_add:
                        goodsnum++;
                        list.get(position).setProductNum(String.valueOf(goodsnum));
                        break;
                    case R.id.goods_reduce:
                        if (goodsnum <= 1) {
                            list.remove(position);
                        } else {
                            goodsnum--;
                            list.get(position).setProductNum(String.valueOf(goodsnum));
                        }
                        break;
                    case R.id.checkbox_goods:
                        CheckBox cb = view.findViewById(R.id.checkbox_goods);
                        boolean checked = cb.isChecked();
                        blist.remove(position);
                        blist.add(position, checked);
                        CBChange();
                        break;

                }
                cartAdapter.notifyDataSetChanged();
                for (int i = 0; i < list.size(); i++) {
                    if (blist.get(i) == true) {
                        double num = Double.parseDouble(list.get(i).getProductNum());
                        double price = Double.parseDouble(list.get(i).getProductPrice());
                        sum += num * price;
                    }
                }
                tvShopcartTotal.setText("￥" + sum);
            }
        });
    }

    private void CBChange() {
        for (int i = 0; i < blist.size(); i++) {
            if (blist.get(i) != true) {
                flag = 1;
            }
        }

        if (flag == 0) {
            checkboxAll.setChecked(true);
        } else {
            checkboxAll.setChecked(false);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                if (tvShopcartEdit.getText().toString().equals("编辑")) {
                    llDelete.setVisibility(View.VISIBLE);
                    llCheckAll.setVisibility(View.GONE);
                    tvShopcartEdit.setText("完成");
                } else {
                    llDelete.setVisibility(View.GONE);
                    llCheckAll.setVisibility(View.VISIBLE);
                    tvShopcartEdit.setText("编辑");
                }
                break;
            case R.id.checkbox_all:
                if (checkboxAll.isChecked() == false) {
                    for (int i = 0; i < blist.size(); i++) {
                        blist.remove(i);
                        blist.add(i, false);
                    }
                } else {
                    for (int i = 0; i < blist.size(); i++) {
                        blist.remove(i);
                        blist.add(i, true);
                    }
                }
                break;

            case R.id.btn_delete:
                for (int i = 0; i < list.size(); i++) {
                    if (blist.get(i) == true) {
                        list.remove(i);
                    }
                }

                cartAdapter.notifyDataSetChanged();
                break;
        }
    }
}
