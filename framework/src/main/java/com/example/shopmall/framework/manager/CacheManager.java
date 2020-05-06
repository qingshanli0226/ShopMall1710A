package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.shopmall.common.StringUtil;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.base.ShopCartBean;
import com.example.shopmall.framework.service.ShopMoreService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    public SpCache spCache;
    private IHomeDataListener iHomeDataListener;
    private static  CacheManager instance;
    private ShopCartBean shopCartBean;
    private ShopMoreService shopMoreService;
    private List<IShopcarDataRecevedLisener> shopCountRecevedLisenerList = new LinkedList<>();

    private CacheManager(){
    }
    public static CacheManager getInstance(){
        if (instance==null){
            instance=new CacheManager();
        }
        return instance;
    }

    public void init(final Context context){
        spCache=new SpCache(context);
        Intent intent = new Intent();
        intent.setClass(context, ShopMoreService.class);

        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                ShopMoreService.ShowMoreBinder showMoreBinder = (ShopMoreService.ShowMoreBinder) iBinder;
                shopMoreService = showMoreBinder.getServices();
                shopMoreService.getHomeData(new ShopMoreService.IHomeDataReceiveListener() {
                    @Override
                    public void onHomeDataReceived(String json) {
                        spCache.saveHomeData(json);
                        if (iHomeDataListener==null){
                            return;
                        }
                        iHomeDataListener.onHomeData(json);

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        },Context.BIND_AUTO_CREATE);
//监听用户登录的状态，当登录成功后，获取购物车产品数量，并且把数量存储起来,多个页面可以获取这个数量
        ShopUserManager.getInstance().registerUserLoginListener(new ShopUserManager.IUserLoginListener() {
            @Override
            public void onLoginSuccess() {//登录成功后，获取购物车数据。
                shopMoreService.getShopcarCount(SpUtil.getTpken(context), new ShopMoreService.IShopcarDataListener() {
                    @Override
                    public void onReceiveShopcarData(int count, ShopCartBean shopCartBeanResult) {
                        SpUtil.saveShopcarCount(context, count);//把购物车的产品数量存起来
                        shopCartBean = shopCartBeanResult;
                        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
                            lisener.onShopcarDataReceived(count,shopCartBean,-1);//如果是-1的话全部刷新
                        }
                    }
                });
            }

            @Override
            public void onLogoutSuccess() {

            }
        });
    }
    public ShopCartBean getShopCartBean() {
        return shopCartBean;
    }
    public interface IHomeDataListener{
        void onHomeData(String json);
    }
    public void registerIHomeListener(IHomeDataListener iHomeDataListener){
        this.iHomeDataListener=iHomeDataListener;
    }
    public void unRegisterIHomeListener(){
        iHomeDataListener=null;
    }
    public String getHomeData(){
        return spCache.getHomeData();
    }
    public interface IShopcarDataRecevedLisener {
        void onShopcarDataReceived(int conunt, ShopCartBean shopCartBean, int index);
        void onShopcarDataSelectedReceived(ShopCartBean shopCartBean, int index);
    }

    public void addNewShopcardata(Context context, int addNum, ShopCartBean.ShopcarData shopcarData) {
        //更新缓存的数据
        int sum = SpUtil.getShopcarCount(context) + addNum;
        SpUtil.saveShopcarCount(context, sum);
        shopCartBean.getResult().add(shopcarData);

        //去通知UI刷新数据
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(sum,shopCartBean,-1);
        }
    }

    //删除产品
    public void removeManyProducts(List<ShopCartBean.ShopcarData> shopcarDataList) {
        for(ShopCartBean.ShopcarData item:shopcarDataList) {
            if (shopCartBean.getResult().contains(item)) {
                shopCartBean.getResult().remove(item);
            }
        }

        //第二步，需要做什么事情?
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(shopCartBean.getResult().size(),shopCartBean,-1);
        }
    }
    //获取购物车选择的商品
    public List<ShopCartBean.ShopcarData> getSelectedProducts() {
        List<ShopCartBean.ShopcarData> selectedShopcarDataList = new ArrayList<>();
        for(ShopCartBean.ShopcarData item:shopCartBean.getResult()) {
            if (item.isProductSelected()) {
                selectedShopcarDataList.add(item);
            }
        }
        return selectedShopcarDataList;
    }
    public void updateShopcarProductNum(Context context,String productId, ShopCartBean.ShopcarData newShopcarData) {
        int index = 0;
        for(ShopCartBean.ShopcarData shopcarData:CacheManager.getInstance().getShopCartBean().getResult()) {
            if (productId.equals(shopcarData.getProductId())) {
                shopcarData.setProductNum(newShopcarData.getProductNum());
                break;
            } else {
                index++;
            }
        }
        //去通知UI刷新数据
        int sum = SpUtil.getShopcarCount(context);
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(sum,shopCartBean,index);
        }
    }
    public void registerShopCountListener(IShopcarDataRecevedLisener listener) {
        if (!shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.add(listener);
        }
    }
    public void updateShopcarProductSelected(ShopCartBean.ShopcarData shopcarData) {
        //第一步修改缓存中，该产品的选择值.
        int index = 0;
        for(ShopCartBean.ShopcarData item:shopCartBean.getResult()) {
            if (item.getProductId().equals(shopcarData.getProductId())) {
                item.setProductSelected(shopcarData.isProductSelected());
                break;
            } else  {
                index++;
            }
        }
        //第二步，需要做什么事情?
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataSelectedReceived(shopCartBean, index);
        }

    }
    public void selectAllProduct(boolean selected) {
        for(ShopCartBean.ShopcarData item:shopCartBean.getResult()) {
            item.setProductSelected(selected);
        }

        //第二步，需要做什么事情?
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(shopCartBean.getResult().size(),shopCartBean,-1);
        }
    }
    public void unRegisterShopCountListener(IShopcarDataRecevedLisener listener) {
        if (shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.remove(listener);
        }
    }
}
