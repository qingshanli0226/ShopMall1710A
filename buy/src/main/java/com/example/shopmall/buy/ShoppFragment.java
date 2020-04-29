package com.example.shopmall.buy;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.buy.adapter.ShortCarAdapter;
import com.example.shopmall.buy.customView.ShopCarBottomBar;
import com.example.shopmall.buy.presenter.*;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomTitleBar;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;


import javax.xml.transform.sax.TemplatesHandler;
import java.util.ArrayList;
import java.util.List;

public class ShoppFragment extends BaseFragment implements CustomTitleBar.OnCustomTitleBarLisenner
        , CacheManager.IShopCountRecevedLisener, ShopCarBottomBar.ShopCarBottomBarLisenner ,ShortCarAdapter.OnShortCarClickLisener{
    private RecyclerView shoppRecyclerView;
    private ShortCarAdapter shortCarAdapter;
    private List<ShortCarEntity.ResultBean> lists;
    private boolean flag;
    private ShopCarBottomBar shopCarBottomBar;
    private AddOneProductPresenter addOneProductPresenter;
    private UpdateProductNumPresenter updateProductNumPresenter;
    private UpdateProductSelectedPresenter updateProductSelectedPresenter;
    private SelectAllProductPresenter selectAllProductPresenter;
    private RemoveManyProductPresenter removeManyProductPresenter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                if (shortCarAdapter != null){
                    shortCarAdapter.notifyDataSetChanged();
                }
                // 给 结算view 设置 总金额
                shopCarBottomBar.setMoney(CacheManager.getInstance().getTotalMoney());
            }
        }
    };
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
        shortCarAdapter.setOnShortCarClickLisener(this);
        shoppRecyclerView.setAdapter(shortCarAdapter);
    }

    @Override
    public List<IPresenter> getPresenter() {
        List<IPresenter> list = new ArrayList<>();
        list.add(addOneProductPresenter = new AddOneProductPresenter(this));
        list.add(updateProductNumPresenter = new UpdateProductNumPresenter(this));
        list.add(updateProductSelectedPresenter = new UpdateProductSelectedPresenter(this));
        list.add(selectAllProductPresenter = new SelectAllProductPresenter(this));
        list.add(removeManyProductPresenter = new RemoveManyProductPresenter(this));
        return list;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        if (requestCode == 100){ // 数据变更成功
            CacheManager.getInstance().startShopMallService();
        }else if (requestCode == 200){ // 减少商品数成功
            CacheManager.getInstance().startShopMallService();
        }else if (requestCode == 300){ // 选中状态改变成功
            CacheManager.getInstance().startShopMallService();
        }else if (requestCode == 400){ // 全选状态切换成功
            CacheManager.getInstance().startShopMallService();
        }else if (requestCode == 500){
            CacheManager.getInstance().startShopMallService();
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

    // 切换 编辑 状态
    @Override
    public void rightTextOk() {
        flag = !flag;
        if (flag){
            mCustomTitleBar.setRightText("完成");
            shopCarBottomBar.setFlag(false);
        }else {
            mCustomTitleBar.setRightText("编辑");
            shopCarBottomBar.setFlag(true);
        }

    }
    // 购物车数据获取成功
    @Override
    public void onShopcarCountReceived(int conunt) {
        Log.i("boss", "onShopcarCountReceived: 购物车页面"+CacheManager.getInstance().getShortCarDatas().size());
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }
    // 全选触发
    @Override
    public void checkBoxAllOk(boolean flag) {
        selectAllProductPresenter.addParams(flag);
        selectAllProductPresenter.postHttpDataWithJson(400);
    }
    // 删除
    @Override
    public void removeOk() {
        List<ShortCarEntity.ResultBean> shortCarDatas = CacheManager.getInstance().getShortCarDatas();
        List<ShortCarEntity.ResultBean> list = new ArrayList<>();
        for (ShortCarEntity.ResultBean shortCarData : shortCarDatas) {
            if (shortCarData.isProductSelected()){
                list.add(shortCarData);
            }
        }
        removeManyProductPresenter.addParams(list);
        removeManyProductPresenter.postHttpDataWithJson(500);
    }

    // 减少商品数量
    @Override
    public void onReduceNum(int position) {
        List<ShortCarEntity.ResultBean> shortCarDatas = CacheManager.getInstance().getShortCarDatas();
        ShortCarEntity.ResultBean resultBean = shortCarDatas.get(position);
        int i = Integer.parseInt(resultBean.getProductNum());
        updateProductNumPresenter.addParame(resultBean.getProductId(),--i,resultBean.getProductName()
                ,resultBean.getUrl()
                ,resultBean.getProductPrice());
        updateProductNumPresenter.postHttpDataWithJson(200);
    }
    // 增加商品数量
    @Override
    public void onAddNum(int position) {
        List<ShortCarEntity.ResultBean> shortCarDatas = CacheManager.getInstance().getShortCarDatas();
        ShortCarEntity.ResultBean resultBean = shortCarDatas.get(position);
        addOneProductPresenter.addParams(resultBean.getProductId(),1,resultBean.getProductName()
                ,resultBean.getUrl()
                ,resultBean.getProductPrice()
                ,resultBean.isProductSelected());
        addOneProductPresenter.postHttpDataWithJson(100);
    }
    // 改变选中状态
    @Override
    public void onChangeCheckState(int position) {
        List<ShortCarEntity.ResultBean> shortCarDatas = CacheManager.getInstance().getShortCarDatas();
        ShortCarEntity.ResultBean resultBean = shortCarDatas.get(position);
        updateProductSelectedPresenter.addParams(resultBean.getProductId(),!resultBean.isProductSelected()
                ,resultBean.getProductName(),resultBean.getUrl(),resultBean.getProductPrice());
        updateProductSelectedPresenter.postHttpDataWithJson(300);
    }
}
