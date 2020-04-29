package com.example.shopmall.buy.shopcar;

import android.util.Log;
import android.view.View;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.shopcar.presenter.AddShopcarPresenter;
import com.example.shopmall.buy.shopcar.presenter.RemoveOneProductPresenter;
import com.example.shopmall.buy.shopcar.presenter.UpdateProductNumPresenter;
import com.example.shopmall.buy.shopcar.presenter.UpdateProductSelectPresenter;
import com.example.shopmall.buy.shopcar.view.ShopcarPayView;
import com.example.shopmall.buy.shopcar.view.ShopcarRecylerview;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.entity.ShopCartBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.framework.view.MyToolBar;

import java.util.ArrayList;
import java.util.List;

public class ShopcarFragment extends BaseFragment<String> implements IShopcarEventListener, CacheManager.IShopcarDataRecevedLisener {
    private AddShopcarPresenter addShopcarPresenter;
    private UpdateProductSelectPresenter updateProductSelectPresenter;
    private UpdateProductNumPresenter updateProductNumPresenter;
    private RemoveOneProductPresenter removeOneProductPresenter;
    private ShopcarRecylerview shopcarRecylerview;
    private ShopcarPayView shopcarPayView;
    private List<IShopcarEventListener> shopcarEventListenerList = new ArrayList<>();

    private boolean isEdit = false;//是否处于编辑模式
    private ShopCartBean shopCartBean;
    private ShopCartBean.ResultBean shopcarData;//存正在操作的数据
    private boolean productSelected;
    private int productCount;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopcar;
    }

    @Override
    protected List<IPresenter<String>> getPresenter() {
        List<IPresenter<String>> iPresenterList = new ArrayList<>();
        addShopcarPresenter = new AddShopcarPresenter();
        updateProductSelectPresenter = new UpdateProductSelectPresenter();
        updateProductNumPresenter = new UpdateProductNumPresenter();
        removeOneProductPresenter = new RemoveOneProductPresenter();
        iPresenterList.add(addShopcarPresenter);
        iPresenterList.add(updateProductSelectPresenter);
        iPresenterList.add(updateProductNumPresenter);
        iPresenterList.add(removeOneProductPresenter);
        return iPresenterList;
    }

    @Override
    protected void initData() {
        CacheManager.getInstance().registerShopCountListener(this);
        //如果登录成功，sp中已经存储了购物产品的数量
        if (ShopUserManager.getInstance().isUserLogin()) {
            shopCartBean = CacheManager.getInstance().getShopCartBean();
            shopcarRecylerview.addShopcarData(shopCartBean.getResult());
            shopcarPayView.notifyMoneyChanged();
        }
    }

    @Override
    protected void initView() {
        shopcarPayView = inflate.findViewById(R.id.shopcarPayView);
        shopcarPayView.setShopcarEventListener(this);//注册lis可以tener，当shopcarview里面的button被点击时，可以通过回调接收点击事件
        shopcarEventListenerList.add((IShopcarEventListener)shopcarPayView);//当其他模块的事件发生时，可以通过这个列表将事件通知shopcarpayview

        shopcarRecylerview=inflate.findViewById(R.id.shopcarRv);
        shopcarEventListenerList.add((IShopcarEventListener)(shopcarRecylerview));
        shopcarRecylerview.setiShopcarEventListener(this);
    }

    @Override
    public void onRightImgClick() {
        super.onRightImgClick();

        if (isEdit) {
            myToolBar.setRightTvContent("编辑");
        }else {
            myToolBar.setRightTvContent("完成");
        }
        isEdit = !isEdit;
        onEditChange(isEdit);
    }

    @Override
    public void onEditChange(boolean isEdit) {
        for(IShopcarEventListener listener:shopcarEventListenerList) {
            listener.onEditChange(isEdit);
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
        }else if (requestCode == 300){
            CacheManager.getInstance().removeShopcardata(getContext(),1,shopcarData);
        }
    }


    @Override
    protected void destroy() {

    }



    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onProductSelectChanged(boolean isSelected,ShopCartBean.ResultBean shopcarData) {
        //实现网络请求，修改产品在服务端的选择值
        this.shopcarData = shopcarData;
        this.productSelected = isSelected;
        updateProductSelectPresenter.addParams(shopcarData, isSelected);
        updateProductSelectPresenter.postHttpDataWithJson(100);
    }

    @Override
    public void onProductCountChanged(ShopCartBean.ResultBean shopcarData,int count) {
        this.shopcarData = shopcarData;
        this.productCount = count;
        updateProductNumPresenter.addParams(
                shopcarData.getProductId(),
                shopcarData.getProductName(),
                shopcarData.getUrl(),
                shopcarData.getProductPrice(),
                String.valueOf(count));
        updateProductNumPresenter.postHttpDataWithJson(200);
    }

    @Override
    public void onAllSelectChanged(boolean isAllSelected) {
        Log.i("TAGddd", "onAllSelectChanged: "+isAllSelected);
        shopcarRecylerview.updateselect(isAllSelected);
    }

    @Override
    public void onPayEventChanged(float payValue) {

    }

    @Override
    public void onProductDeleted(ShopCartBean.ResultBean shopcarData) {
        this.shopcarData = shopcarData;
        removeOneProductPresenter.addParams(
                shopcarData.getProductId(),
                shopcarData.getProductNum(),
                shopcarData.getProductName(),
                shopcarData.getUrl(),
                shopcarData.getProductPrice());
        removeOneProductPresenter.postHttpDataWithJson(300);
    }

    @Override
    public void onProductSaved() {

    }

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

    @Override
    public void onShopcarDataSelectedReceived(ShopCartBean shopCartBean, int index) {
        shopcarRecylerview.updateOneData(shopcarData, index);
        shopcarPayView.notifyMoneyChanged();
    }
}
