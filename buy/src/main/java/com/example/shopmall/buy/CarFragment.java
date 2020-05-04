package com.example.shopmall.buy;

import android.view.View;

import com.example.shopmall.buy.presenter.AddShopcarPresenter;
import com.example.shopmall.buy.presenter.SelectAllProductPresenter;
import com.example.shopmall.buy.presenter.UpdateProductNumPresenter;
import com.example.shopmall.buy.presenter.UpdateProductSelectPresenter;
import com.example.shopmall.buy.view.ShopcarPayView;
import com.example.shopmall.buy.view.ShopcarRecylerview;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.base.ShopCartBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;

import java.util.ArrayList;
import java.util.List;


public class CarFragment extends BaseFragment<String> implements IShopcarEventListener, CacheManager.IShopcarDataRecevedLisener {
    private AddShopcarPresenter addShopcarPresenter;
    private UpdateProductSelectPresenter updateProductSelectPresenter;
    private UpdateProductNumPresenter updateProductNumPresenter;
    private SelectAllProductPresenter selectAllProductPresenter;
    private ShopcarPayView shopcarPayView;
    private List<IShopcarEventListener> shopcarEventListenerList = new ArrayList<>();
    private ShopcarRecylerview shopcarRecylerview;
    private boolean isEdit = false;//是否处于编辑模式
    private ShopCartBean shopCartBean;
    private ShopCartBean.ShopcarData shopcarData;//存正在操作的数据
    private boolean productSelected;
    private int productCount;
    private int productAllSelectedType = -1;
    @Override
    protected List<IPresenter<String>> getPresenter() {
        addShopcarPresenter = new AddShopcarPresenter();
        updateProductSelectPresenter = new UpdateProductSelectPresenter();
        updateProductNumPresenter = new UpdateProductNumPresenter();
        selectAllProductPresenter = new SelectAllProductPresenter();
        List<IPresenter<String>> iPresenterList = new ArrayList<>();
        iPresenterList.add(addShopcarPresenter);
        iPresenterList.add(updateProductSelectPresenter);
        iPresenterList.add(updateProductNumPresenter);
        iPresenterList.add(selectAllProductPresenter);
        return iPresenterList;
    }

    @Override
    protected void initView() {
        shopcarPayView = rootView.findViewById(R.id.shopcarPayView);
        shopcarPayView.setShopcarEventListener(this);//注册lis可以tener，当shopcarview里面的button被点击时，可以通过回调接收点击事件
        shopcarEventListenerList.add((IShopcarEventListener)shopcarPayView);//当其他模块的事件发生时，可以通过这个列表将事件通知shopcarpayview
        shopcarRecylerview=rootView.findViewById(R.id.shopcarRv);
        shopcarEventListenerList.add((IShopcarEventListener)(shopcarRecylerview));
        shopcarRecylerview.setiShopcarEventListener(this);
    }

    @Override
    protected void initData() {
        CacheManager.getInstance().registerShopCountListener(this);
        //如果登录成功，sp中已经存储了购物产品的数量
        if (ShopUserManager.getInstance().isUserLogin()) {
            shopCartBean = CacheManager.getInstance().getShopCartBean();
            shopcarRecylerview.addShopcarData(shopCartBean.getResult());
            shopcarPayView.notifyMoneyChanged();
            updateAllSelectUI(shopCartBean);
        }
    }





    @Override
    public void onHtttpReceived(int requestCode, String data) {
        if (requestCode == 100) {
            //代表服务端购物车的选择完成
            //修改购物车缓存中的该产品的选择值
            shopcarData.setProductSelected(productSelected);//先改变内存类的他的值
            CacheManager.getInstance().updateShopcarProductSelected(shopcarData);
        } else if (requestCode == 200) {
            shopcarData.setProductNum(String.valueOf(productCount));
            CacheManager.getInstance().updateShopcarProductNum(getActivity(),shopcarData.getProductId(),shopcarData);
        } else if (requestCode == 400) {
            if (productAllSelectedType == -1) {
                return;
            }
            //告诉我们的缓存数据是全选还是全不选
            CacheManager.getInstance().selectAllProduct(productAllSelectedType==1?true:false);
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopcar;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onEditChange(boolean isEdit) {
        for(IShopcarEventListener listener:shopcarEventListenerList) {
            listener.onEditChange(isEdit);
        }
    }

    @Override
    public void onProductSelectChanged(boolean isSelected, ShopCartBean.ShopcarData shopcarData) {
        //实现网络请求，修改产品在服务端的选择值
        this.shopcarData = shopcarData;
        this.productSelected = isSelected;
        updateProductSelectPresenter.addParams(shopcarData, isSelected);
        updateProductSelectPresenter.postHttpDataWithJson(100);
    }

    @Override
    public void onProductCountChanged(ShopCartBean.ShopcarData shopcarData, int count) {
        this.shopcarData = shopcarData;
        this.productCount = count;
        updateProductNumPresenter.addParams(shopcarData.getProductId(),shopcarData.getProductName(),shopcarData.getUrl(),
                shopcarData.getProductPrice(),String.valueOf(count));
        updateProductNumPresenter.postHttpDataWithJson(200);
    }


    @Override
    public void onAllSelectChanged(boolean isAllSelected) {
        if (!isEdit) {
            selectAllProductPresenter.addParams(isAllSelected);
            selectAllProductPresenter.postHttpDataWithJson(400);
            productAllSelectedType = isAllSelected ? 1 : 2;//1代表的全选，2代表全不选 -1代表初始值
        } else {
            for (IShopcarEventListener listener:shopcarEventListenerList) {
                listener.onAllSelectChanged(isAllSelected);
            }
        }
    }

    @Override
    public void onPayEventChanged(float payValue) {

    }

    @Override
    public void onProductDeleted() {

    }

    @Override
    public void onProductSaved() {

    }

    //监听获取购物车的数据，将数据添加购物车列表中，并且更新购物车价格
    @Override
    public void onShopcarDataReceived(int conunt, ShopCartBean shopCartBean,int index) {
        if (index == -1) {//当index为-1时，代表的刷新全部列表，否则只刷新某一个itemview
            this.shopCartBean = shopCartBean;
            shopcarRecylerview.addShopcarData(shopCartBean.getResult());
        } else {
            shopcarRecylerview.updateOneData(shopcarData, index);
        }
        shopcarPayView.notifyMoneyChanged();
    }

    //购物车产品选择改变
    @Override
    public void onShopcarDataSelectedReceived(ShopCartBean shopCartBean, int index) {
        shopcarRecylerview.updateOneData(shopcarData, index);
        shopcarPayView.notifyMoneyChanged();
        updateAllSelectUI(shopCartBean);
    }

    private void updateAllSelectUI(ShopCartBean shopCartBean) {
        int selectNums = 0;
        for(ShopCartBean.ShopcarData shopcarData :shopCartBean.getResult()) {
            if (shopcarData.isProductSelected()) {
                selectNums++;
            }
        }
        if (selectNums == shopCartBean.getResult().size()) {
            for (IShopcarEventListener listener:shopcarEventListenerList) {
                listener.onAllSelectChanged(true);
            }
        } else {
            for (IShopcarEventListener listener:shopcarEventListenerList) {
                listener.onAllSelectChanged(false);
            }
        }
    }
}
