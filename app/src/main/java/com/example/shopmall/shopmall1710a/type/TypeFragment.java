package com.example.shopmall.shopmall1710a.type;


import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.type.adapter.LeftAdapter;
import com.example.shopmall.shopmall1710a.type.adapter.RightAdapter;
import com.example.shopmall.shopmall1710a.type.entity.TypeBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment {

    private RecyclerView typeLeftRecyclerView;
    private RecyclerView typeRightRecyclerView;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private List<TypeBean.ResultBean> resultBeans;
    private String[]   urls = new String[]{"SKIRT_URL.json","JACKET_URL.json","PANTS_URL.json","OVERCOAT_URL.json","ACCESSORY_URL.json"
        ,"BAG_URL.json","DRESS_UP_URL.json","HOME_PRODUCTS_URL.json","STATIONERY_URL.json","DIGIT_URL.json","GAME_URL.json"};

    @Override
    public int bindLayout() {
        return R.layout.fragment_type;
    }

    @Override
    public void initView() {
        typeLeftRecyclerView = (RecyclerView) findViewById(R.id.TypeLeftRecyclerView);
        typeRightRecyclerView = (RecyclerView) findViewById(R.id.TypeRightRecyclerView);
        typeLeftRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        typeRightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        resultBeans = new ArrayList<>();
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("小裙子");
        list.add("上衣");
        list.add("下装");
        list.add("外套");
        list.add("配件");
        list.add("包包");
        list.add("装扮");
        list.add("居家宅品");
        list.add("办公文具");
        list.add("数码周边");
        list.add("游戏专区");
        if (leftAdapter == null){
            leftAdapter = new LeftAdapter(list);
            typeLeftRecyclerView.setAdapter(leftAdapter);
        }

        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    OkGo.<String>get("http://49.233.93.155:8080/atguigu/json/"+urls[position])
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    resultBeans.clear();
                                    TypeBean typeBean = JSON.parseObject(response.body().toString(), TypeBean.class);
                                    resultBeans.addAll(typeBean.getResult());
                                    if (rightAdapter == null){
                                        rightAdapter = new RightAdapter(resultBeans,getContext());
                                        typeRightRecyclerView.setAdapter(rightAdapter);
                                    }else {
                                        rightAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
            }
        });
    }

    @Override
    public List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
