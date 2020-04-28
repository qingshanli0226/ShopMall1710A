package com.example.shopmall.buy.shopcar;

import android.view.View;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.shopcar.presenter.AddShopcarPresenter;
import com.example.shopmall.buy.shopcar.view.ShopcarPayView;
import com.example.shopmall.buy.shopcar.view.ShopcarRecylerview;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.bean.ShopCartBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;

import java.util.ArrayList;
import java.util.List;

public class ShopcarFragment extends BaseFragment<String> implements IShopcarEventListener, CacheManager.IShopcarDataRecevedLisener {
    private AddShopcarPresenter addShopcarPresenter;
    private ShopcarPayView shopcarPayView;
    private List<IShopcarEventListener> shopcarEventListenerList = new ArrayList<>();
    private ShopcarRecylerview shopcarRecylerview;
    private boolean isEdit = false;//是否处于编辑模式
    private ShopCartBean shopCartBean;
    @Override
    protected List<IPresenter<String>> getPresenter() {
        addShopcarPresenter = new AddShopcarPresenter();
        List<IPresenter<String>> iPresenterList = new ArrayList<>();
        iPresenterList.add(addShopcarPresenter);
        return iPresenterList;
    }

    @Override
    protected void initData() {
        CacheManager.getInstance().registerShopCountListener(this);
        //如果登录成功，sp中已经存储了购物产品的数量
        if (ShopUserManager.getInstance().isUserLogin()) {
            shopCartBean = CacheManager.getInstance().getShopCartBean();
            shopcarRecylerview.addShopcarData(shopCartBean.getResult());
        }
    }

    @Override
    protected void initView(View rootView) {
        shopcarPayView = rootView.findViewById(R.id.shopcarPayView);
        shopcarPayView.setShopcarEventListener(this);//注册lis可以tener，当shopcarview里面的button被点击时，可以通过回调接收点击事件
        shopcarEventListenerList.add((IShopcarEventListener)shopcarPayView);//当其他模块的事件发生时，可以通过这个列表将事件通知shopcarpayview
        shopcarRecylerview=rootView.findViewById(R.id.shopcarRv);
        shopcarEventListenerList.add((IShopcarEventListener)(shopcarRecylerview));
        shopcarRecylerview.setiShopcarEventListener(this);
    }

    @Override
    protected void initToolBar(View rootView) {
        super.initToolBar(rootView);
        toolBar.showLeft(false);
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        isEdit = !isEdit;
        onEditChange(isEdit);
        if (isEdit) {
            toolBar.setRightTvContent(R.string.finish);
        }else {
            toolBar.setRightTvContent(R.string.edit);
        }
    }

    @Override
    public void onHtttpReceived(int requestCode, String data) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopcar;
    }

    @Override
    public void onEditChange(boolean isEdit) {
        for(IShopcarEventListener listener:shopcarEventListenerList) {
            listener.onEditChange(isEdit);
        }
    }

    @Override
    public void onProductSelectChanged(boolean isSelected, float productPric) {

    }

    @Override
    public void onProductCountChanged(int count) {

    }

    @Override
    public void onAllSelectChanged(boolean isAllSelected) {

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

    @Override
    public void onShopcarDataReceived(int conunt, ShopCartBean shopCartBean) {
        this.shopCartBean = shopCartBean;
        shopcarRecylerview.addShopcarData(shopCartBean.getResult());
    }
}
