package com.example.shopmall.buy;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.buy.adapter.ShortCarAdapter;
import com.example.shopmall.buy.customView.ShopCarBottomBar;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomTitleBar;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;


import java.util.ArrayList;
import java.util.List;

public class ShoppFragment extends BaseFragment implements CustomTitleBar.OnCustomTitleBarLisenner
        , CacheManager.IShopCountRecevedLisener, ShopCarBottomBar.ShopCarBottomBarLisenner {
    private RecyclerView shoppRecyclerView;
    private ShortCarAdapter shortCarAdapter;
    private List<ShortCarEntity.ResultBean> lists;
    private boolean flag;
    private ShopCarBottomBar shopCarBottomBar;

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

    }

    @Override
    public void initData() {
        shortCarAdapter = new ShortCarAdapter(CacheManager.getInstance().getShortCarDatas());
        shoppRecyclerView.setAdapter(shortCarAdapter);
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
        Log.i("boss", "onShopcarCountReceived: 购物车页面"+CacheManager.getInstance().getShortCarDatas().size());
        if (shortCarAdapter != null){
            shortCarAdapter.notifyDataSetChanged();
        }
        // 给 结算view 设置 总金额
        shopCarBottomBar.setMoney(CacheManager.getInstance().getTotalMoney());
    }

    @Override
    public void checkBoxAllOk(boolean flag) { // 触发全选回调
//        shortCarAdapter.notifyDataSetChanged();
    }


}
