package com.example.shopmall.shopmall1710a.fragment.type;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shopmall.common.Constants;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.fragment.type.adapter.TypeLeftAdapter;
import com.example.shopmall.shopmall1710a.fragment.type.adapter.TypeRightAdapter;
import com.example.shopmall.shopmall1710a.fragment.type.entity.TypeBean;
import com.example.shopmall.shopmall1710a.fragment.type.persenter.TypeSelectPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment<Object> {
    private String[] urls = new String[]{
            Constants.SKIRT_URL,
            Constants.JACKET_URL,
            Constants.PANTS_URL,
            Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL,
            Constants.BAG_URL,
            Constants.DRESS_UP_URL,
            Constants.HOME_PRODUCTS_URL,
            Constants.STATIONERY_URL,
            Constants.DIGIT_URL,
            Constants.GAME_URL};
    private TypeSelectPresenter typeSelectPresenter;
    private ListView lvLeft;
    private TypeLeftAdapter leftAdapter;
    private RecyclerView rvRight;
    private TypeRightAdapter rightAdapter;
    private List<TypeBean.ResultBean.ChildBean> childBeans = new ArrayList<>();
    private List<TypeBean.ResultBean.HotProductListBean> hotProductListBeans = new ArrayList<>();
    @Override
    protected List<IPresenter<Object>> getPresenter() {
        List<IPresenter<Object>> list = new ArrayList<>();
        typeSelectPresenter = new TypeSelectPresenter();
        list.add(typeSelectPresenter);
        return list;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView() {
        lvLeft = inflate.findViewById(R.id.lv_left);
        rvRight = inflate.findViewById(R.id.rv_right);
        leftAdapter = new TypeLeftAdapter(getContext());
        lvLeft.setAdapter(leftAdapter);
        rightAdapter = new TypeRightAdapter(getContext(),childBeans,hotProductListBeans);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        rvRight.setLayoutManager(manager);
        rvRight.setAdapter(rightAdapter);

        //点击监听
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftAdapter.changeSelected(position);//刷新
//                if (position != 0) {
//                    isFirst = false;
//                }
                getDataFromNet(urls[position]);
                leftAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void initData() {
        getDataFromNet(urls[0]);
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode == 100){
            Toast.makeText(getContext(), "66"+requestCode, Toast.LENGTH_SHORT).show();
            TypeBean typeBean = new Gson().fromJson(data.toString(), TypeBean.class);
            childBeans.addAll(typeBean.getResult().get(0).getChild());
            hotProductListBeans.addAll(typeBean.getResult().get(0).getHot_product_list());
            rightAdapter.notifyDataSetChanged();
        }
    }


    public void getDataFromNet(String url) {
        typeSelectPresenter.selectType(urls[1]);
        typeSelectPresenter.getHttpData(100);
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(getContext(), "55"+errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
