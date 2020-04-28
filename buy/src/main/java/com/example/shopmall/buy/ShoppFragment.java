package com.example.shopmall.buy;

import android.util.Log;

import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.buy.adapter.ShortCarAdapter;
import com.example.shopmall.buy.customView.ShopCarBottomBar;
import com.example.shopmall.buy.entity.ShopCarEntity;
import com.example.shopmall.buy.presenter.GetShortCarPresenter;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomTitleBar;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;


import java.util.ArrayList;
import java.util.List;

public class ShoppFragment extends BaseFragment implements CustomTitleBar.OnCustomTitleBarLisenner
        , CacheManager.IShopCountRecevedLisener, ShopCarBottomBar.ShopCarBottomBarLisenner , ShortCarAdapter.ShortCarAdapterCheckedLisenner {
    private GetShortCarPresenter getShortCarPresenter;
    private RecyclerView shoppRecyclerView;
    private ShortCarAdapter shortCarAdapter;
    private List<ShopCarEntity> lists;
    private boolean flag;
    private ShopCarBottomBar shopCarBottomBar;
    private List<Boolean> booleans;
    private float money;
    private int count;
    @Override
   public int bindLayout() {
        return R.layout.fragment_shopp ;
    }

    @Override
    public void initView() {
        CacheManager.getInstance().registerCountLisenner(this);
        mCustomTitleBar.setOnCustomTitleBarLisenner(this);
        shoppRecyclerView = (RecyclerView) findViewById(R.id.shoppRecyclerView);
        shoppRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shopCarBottomBar = (ShopCarBottomBar) findViewById(R.id.shopCarBottomBar);
        lists = new ArrayList<>();
        shopCarBottomBar.setShopCarBottomBarLisenner(this);
        booleans = new ArrayList<>();

    }

    @Override
    public void initData() {
        getShortCarPresenter.getHttpData(100);
    }

    @Override
    public List<IPresenter> getPresenter() {
        List<IPresenter> list = new ArrayList<>();
        getShortCarPresenter = new GetShortCarPresenter(this);
        list.add(getShortCarPresenter);
        return list;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        if (requestCode == 100){
            List<ShopCarEntity> shopCarEntity = (List<ShopCarEntity>) data;
            lists.clear();
            lists.addAll(shopCarEntity);
            if (shortCarAdapter == null){
                for (int i = 0; i < lists.size(); i++) {
                    booleans.add(false); // 默认都是未选中
                }
                shortCarAdapter = new ShortCarAdapter(lists,booleans);
                shortCarAdapter.setShortCarAdapterCheckedLisenner(this);
                shoppRecyclerView.setAdapter(shortCarAdapter);
                // 第一次获取到数据的时候 来填充一个集合 管理选中状态
            }else {
                shortCarAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Log.i("boss", "onHttpReceivedFailed: 购物车页面 "+errorBean.getErrorMessage());
    }


    @Override
    public void leftOk() {

    }

    @Override
    public void rightOk() {

    }

    @Override
    public void rightTextOk() {  // 右边文字的点击事件
        flag = !flag;
        if (flag){
            mCustomTitleBar.setRightText("完成");
            shopCarBottomBar.setFlag(false);
        }else {
            mCustomTitleBar.setRightText("编辑");
            shopCarBottomBar.setFlag(true);
        }

    }

    @Override
    public void onShopcarCountReceived(int conunt) {
        getShortCarPresenter.getHttpData(100);
    }

    @Override
    public void checkBoxAllOk(boolean flag) { // 触发全选回调
        for (int i = 0; i < booleans.size(); i++) {
            booleans.set(i,flag);
        }
        shortCarAdapter.notifyDataSetChanged();
        calculateMoney();
    }
    // 选中装填的监听回调
    @Override
    public void isCheckedOk(int position, boolean flag) {
        money = 0;
        booleans.set(position,flag); // 更新集合对应的 状态
        calculateMoney();
    }

    private void calculateMoney() {
        count = 0;
        for (int i = 0; i < booleans.size(); i++) {
            if (booleans.get(i)){
               money += Float.parseFloat(lists.get(i).getProductPrice()) * Integer.parseInt(lists.get(i).getProductNum());
               count ++;
            }
        }
        shopCarBottomBar.setMoney(money);
        shopCarBottomBar.setCount(count);
    }
}
