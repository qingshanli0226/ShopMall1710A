package com.example.shopmall.buy.view;

import android.view.View;
import android.widget.Toast;

import com.example.shopmall.buy.IShopcarEventListener;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.mode.InventoryBean;
import com.example.shopmall.buy.mode.OrderInfoBean;
import com.example.shopmall.buy.presenter.AddShopcarPresenter;
import com.example.shopmall.buy.presenter.CheckInvtenoryPresenter;
import com.example.shopmall.buy.presenter.OrderInfoPresenter;
import com.example.shopmall.buy.presenter.RemoveProductsPresenter;
import com.example.shopmall.buy.presenter.SelectAllProductPresenter;
import com.example.shopmall.buy.presenter.UpdateProductNumPresenter;
import com.example.shopmall.buy.presenter.UpdateProductSelectPresenter;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.base.ShopCartBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;

import java.util.ArrayList;
import java.util.List;

public class ShopcarFragment extends BaseFragment<Object> implements IShopcarEventListener, CacheManager.IShopcarDataRecevedLisener {
    private AddShopcarPresenter addShopcarPresenter;
    private UpdateProductSelectPresenter updateProductSelectPresenter;
    private UpdateProductNumPresenter updateProductNumPresenter;
    private SelectAllProductPresenter selectAllProductPresenter;
    private RemoveProductsPresenter removeProductsPresenter;
    private CheckInvtenoryPresenter checkInvtenoryPresenter;
    private OrderInfoPresenter orderInfoPresenter;
    private ShopcarPayView shopcarPayView;
    private List<IShopcarEventListener> shopcarEventListenerList = new ArrayList<>();
    private ShopcarRecylerview shopcarRecylerview;
    private boolean isEdit = false;//是否处于编辑模式
    private ShopCartBean shopCartBean;
    private ShopCartBean.ShopcarData shopcarData;//存正在操作的数据
    private boolean productSelected;
    private int productCount;
    private int productAllSelectedType = -1;
    //定义类型，用来区分到底是那个view发出的事件。有时同一个事件，不同的view都可发出，通过该type来区分那个view发出的.所以定义下面三个类型
    public static final int PAY_VIEW_TYPE = 1;
    public static final int TOOLBAR_VIEW_TYPE = 2;
    public static final int RECYCLERVIEW_VIEW_TYPE = 3;
    @Override
    protected List<IPresenter<Object>> getPresenter() {
        addShopcarPresenter = new AddShopcarPresenter();
        updateProductSelectPresenter = new UpdateProductSelectPresenter();
        updateProductNumPresenter = new UpdateProductNumPresenter();
        selectAllProductPresenter = new SelectAllProductPresenter();
        removeProductsPresenter = new RemoveProductsPresenter();
        checkInvtenoryPresenter = new CheckInvtenoryPresenter();
        orderInfoPresenter = new OrderInfoPresenter();
        List<IPresenter<Object>> iPresenterList = new ArrayList<>();
        iPresenterList.add(addShopcarPresenter);
        iPresenterList.add(updateProductSelectPresenter);
        iPresenterList.add(updateProductNumPresenter);
        iPresenterList.add(selectAllProductPresenter);
        iPresenterList.add(removeProductsPresenter);
        iPresenterList.add(checkInvtenoryPresenter);
        iPresenterList.add(orderInfoPresenter);
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
    public void onRightTvClick() {
        super.onRightTvClick();
        isEdit = !isEdit;
        onEditChange(isEdit);
        if (isEdit) {
            toorBar.setRightTv(R.string.finish);
        }else {
            toorBar.setRightTv(R.string.edit);
        }
    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
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
        } else if (requestCode == 500) {
            Toast.makeText(getActivity(), "删除成功",Toast.LENGTH_SHORT).show();
            CacheManager.getInstance().removeManyProducts(shopcarRecylerview.getShopcarDelteDataList());
        } else if (requestCode == 600) {//检查购物车库存
            List<InventoryBean> inventoryBeanList = (List<InventoryBean>)data;
            if (checkIfCanOrder(inventoryBeanList)) {
                getOrder();
            }

        } else if (requestCode == 700) {//生成订单信息
            Toast.makeText(getActivity(), "生成订单成功",Toast.LENGTH_SHORT).show();
            //生成点单后，需要把列表选择的商品从购物车列表中删除
            CacheManager.getInstance().removeManyProducts(CacheManager.getInstance().getSelectedProducts());
            OrderInfoBean orderInfoBean = (OrderInfoBean)data;
            OrderInfoActivity.launch(getActivity(), orderInfoBean.getOrderInfo(),orderInfoBean.getOutTradeNo());

            //显示订单详情页面，并且把参数传递过去

        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    //需要手动比较库存是否充足
    private boolean checkIfCanOrder(List<InventoryBean> inventoryBeanList) {
        boolean flag = true;
        for(InventoryBean item:inventoryBeanList) {
            for(ShopCartBean.ShopcarData shopcarData: CacheManager.getInstance().getSelectedProducts()) {
                if (item.getProductId().equals(shopcarData.getProductId())) {
                    if (!item.getProductNum().equals(shopcarData.getProductNum())) {
                        Toast.makeText(getActivity(), "库存不足，无法生成订单",Toast.LENGTH_SHORT).show();
                        flag = false;
                    }
                }
            }
        }
        //库存充足，下订单

        return flag;
    }

    //生成订单信息
    private void getOrder() {
        orderInfoPresenter.addParams("buy", shopcarPayView.getTotalPrice(), CacheManager.getInstance().getSelectedProducts());
        orderInfoPresenter.postHttpDataWithJson(700);
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

        //当从编辑状态返回时，需要检查当前列表是否还有数据，如果没有，则不可以在全选状态
        if (!isEdit) {
            updateAllSelectUI(CacheManager.getInstance().getShopCartBean());
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
    public void onAllSelectChanged(boolean isAllSelected, int viewType) {
        if (!isEdit) {
            selectAllProductPresenter.addParams(isAllSelected);
            selectAllProductPresenter.postHttpDataWithJson(400);
            productAllSelectedType = isAllSelected ? 1 : 2;//1代表的全选，2代表全不选 -1代表初始值
        } else {
            for (IShopcarEventListener listener:shopcarEventListenerList) {
                listener.onAllSelectChanged(isAllSelected,viewType);
            }
        }
    }

    @Override
    public void onPayEventChanged(float payValue) {

        //需要做两件事：1,检查库存是否够 2，生成订单信息
        if (CacheManager.getInstance().getSelectedProducts().size() == 0) {
            return;
        }
        checkInvtenoryPresenter.addParams(CacheManager.getInstance().getSelectedProducts());
        checkInvtenoryPresenter.postHttpDataWithJson(600);

        //能不能在这里直接生成订单信息


    }

    @Override
    public void onProductDeleted() {
        removeProductsPresenter.addParams(shopcarRecylerview.getShopcarDelteDataList());
        removeProductsPresenter.postHttpDataWithJson(500);
    }

    @Override
    public void onProductSaved() {

    }

    //监听获取购物车的数据，将数据添加购物车列表中，并且更新购物车价格
    @Override
    public void onShopcarDataReceived(int conunt, final ShopCartBean shopCartBean, final int index) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (index == -1) {//当index为-1时，代表的刷新全部列表，否则只刷新某一个itemview
                    ShopcarFragment.this.shopCartBean = shopCartBean;
                    shopcarRecylerview.addShopcarData(shopCartBean.getResult());
                } else {
                    shopcarRecylerview.updateOneData(shopcarData, index);
                }
                updateAllSelectUI(shopCartBean);
                shopcarPayView.notifyMoneyChanged();
            }
        });
    }

    @Override
    public void onShopcarDataSelectedReceived(ShopCartBean shopCartBean, int index) {

    }

    //更新UI,主要是更新是否全选，当个数为0时，不是全选
    private void updateAllSelectUI(ShopCartBean shopCartBean) {
        int selectNums = 0;
        for(ShopCartBean.ShopcarData shopcarData :shopCartBean.getResult()) {
            if (shopcarData.isProductSelected()) {
                selectNums++;
            }
        }
        //当数量相等，且个数大于零
        if (selectNums == shopCartBean.getResult().size() && selectNums > 0) {
            for (IShopcarEventListener listener:shopcarEventListenerList) {
                listener.onAllSelectChanged(true,RECYCLERVIEW_VIEW_TYPE);
            }
        } else {
            for (IShopcarEventListener listener:shopcarEventListenerList) {
                listener.onAllSelectChanged(false,RECYCLERVIEW_VIEW_TYPE);
            }
        }
    }
}
