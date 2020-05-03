package com.example.shopmall.shopmall1710a.main.view.fragment;


import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseFragment;
import com.example.shopmall.framework.base.view.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.adapter.TypeLeftAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.TypeTypeRecomAdapter;
import com.example.shopmall.shopmall1710a.main.adapter.TypeTypeTypeAdapter;
import com.example.shopmall.shopmall1710a.main.bean.TypeEntity;
import com.example.shopmall.shopmall1710a.main.presenter.TypePresenter;

import java.util.ArrayList;
import java.util.List;

public class TypeFrag extends BaseFragment<TypePresenter, TypeEntity> implements IBaseView<TypeEntity>, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {


    private TypeLeftAdapter typeLeftAdapter;
    private TypeTypeRecomAdapter typeTypeRecomAdapter;
    private TypeTypeTypeAdapter typeTypeTypeAdapter;

    public static List<String> slist = new ArrayList<>();
    private List<String> nlist = new ArrayList<>();
    private List<TypeEntity.ChildBean> clist = new ArrayList<>();
    private List<TypeEntity.HotProductListBean> hlist = new ArrayList<>();
    private TextView typeTvType;
    private TextView typeTvTag;
    private LinearLayout typeLinType;
    private RecyclerView fragTypeRecyclerleft;
    private RecyclerView typeRecyclertop;
    private RecyclerView typeRecyclerbot;
    private LinearLayout typeLinTag;
    private RecyclerView typeRecyclerTag;


    public static int num = 0;

    private TypePresenter typePresenter;


    @Override
    public void onHtttpReceived(int requestCode, TypeEntity data) {
        Log.d("LMQ", "onHtttpReceived: 123");
        clist.clear();
        hlist.clear();
        List<TypeEntity.ChildBean> child = data.getChild();
        List<TypeEntity.HotProductListBean> hot_product_list = data.getHot_product_list();
        clist.addAll(child);
        Log.d("LMQ", "onHtttpReceived: 1234");
        hlist.addAll(hot_product_list);
        Log.d("LMQ", "onHtttpReceived: 12345");
        typeTypeRecomAdapter.notifyDataSetChanged();
        typeTypeTypeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(getContext(), ""+errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public int bindLayout() {
        return R.layout.frag_type;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {

        typeTvType = findViewById(R.id.type_tv_type);
        typeTvTag = findViewById(R.id.type_tv_tag);
        typeLinType = findViewById(R.id.type_lin_type);
        fragTypeRecyclerleft = findViewById(R.id.frag_type_recyclerleft);
        typeRecyclertop = findViewById(R.id.type_recyclertop);
        typeRecyclerbot = findViewById(R.id.type_recyclerbot);
        typeLinTag = findViewById(R.id.type_lin_tag);
        typeRecyclerTag = findViewById(R.id.type_recycler_tag);


        typeTvType.setOnClickListener(this);
        typeTvTag.setOnClickListener(this);

        typeTypeRecomAdapter = new TypeTypeRecomAdapter(R.layout.item_type_recom, hlist);
        typeTypeTypeAdapter = new TypeTypeTypeAdapter(R.layout.item_type_recom, clist);
        typeLeftAdapter = new TypeLeftAdapter(R.layout.item_type_left, nlist);

        typeLeftAdapter.setOnItemClickListener(this);

        fragTypeRecyclerleft.setAdapter(typeLeftAdapter);
        fragTypeRecyclerleft.setLayoutManager(new LinearLayoutManager(getContext()));

        typeRecyclertop.setAdapter(typeTypeRecomAdapter);
        typeRecyclertop.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        typeRecyclerbot.setAdapter(typeTypeTypeAdapter);
        typeRecyclerbot.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

    }


    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        nlist.clear();
        slist.clear();
        AddUrl();
        typePresenter = new TypePresenter();
        typePresenter.attachView(this);
        typePresenter.getHttpData(110);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type_tv_tag:
                typeLinType.setVisibility(View.GONE);
                typeLinTag.setVisibility(View.VISIBLE);
                typeTvTag.setBackgroundResource(R.drawable.type_tv_right_bg);
                typeTvTag.setTextColor(Color.WHITE);
                typeTvType.setBackgroundResource(R.drawable.type_tv_left_bg_white);
                typeTvType.setTextColor(Color.RED);
                break;
            case R.id.type_tv_type:
                typeLinType.setVisibility(View.VISIBLE);
                typeLinTag.setVisibility(View.GONE);
                typeTvTag.setBackgroundResource(R.drawable.type_tv_right_bg_white);
                typeTvTag.setTextColor(Color.RED);
                typeTvType.setBackgroundResource(R.drawable.type_tv_left_bg);
                typeTvType.setTextColor(Color.WHITE);
                break;
        }
    }


    private void AddUrl() {
        slist.add(Constant.SKIRT_URL);
        slist.add(Constant.JACKET_URL);
        slist.add(Constant.PANTS_URL);
        slist.add(Constant.OVERCOAT_URL);
        slist.add(Constant.ACCESSORY_URL);
        slist.add(Constant.BAG_URL);
        slist.add(Constant.DRESS_UP_URL);
        slist.add(Constant.HOME_PRODUCTS_URL);
        slist.add(Constant.STATIONERY_URL);
        slist.add(Constant.DIGIT_URL);
        slist.add(Constant.GAME_URL);


        nlist.add("小裙子");
        nlist.add("上衣");
        nlist.add("下装");
        nlist.add("外套");
        nlist.add("配件");
        nlist.add("包包");
        nlist.add("装扮");
        nlist.add("居家宅品");
        nlist.add("办公文具");
        nlist.add("数码周边");
        nlist.add("游戏专区");

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        num = position;
        typePresenter.getHttpData(110);
    }
}
