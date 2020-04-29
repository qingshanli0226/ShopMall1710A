package com.example.shopmall.buy.shopcar.view;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shopmall.buy.R;
import com.example.shopmall.buy.shopcar.IShopcarEventListener;
import com.example.shopmall.framework.entity.ShopCartBean;
import com.example.shopmall.framework.manager.CacheManager;

//让该view去实现接口，当其他模块事件发生时，可以通过这个接口，去获取事件
public class ShopcarPayView extends LinearLayout implements IShopcarEventListener, View.OnClickListener {
    private IShopcarEventListener iShopcarEventListener;
    private RelativeLayout normalLayout;
    private RelativeLayout editLayout;
    private CheckBox allSelectCheckbox;
    private TextView payVlaue;

    public ShopcarPayView(Context context) {
        super(context);
        init(context, null);
    }

    public ShopcarPayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShopcarPayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View  rootView = LayoutInflater.from(context).inflate(R.layout.view_shopcar_pay, this);
        normalLayout = rootView.findViewById(R.id.normalLayout);
        editLayout = rootView.findViewById(R.id.editLayout);
        payVlaue = rootView.findViewById(R.id.sumValue);
        allSelectCheckbox = rootView.findViewById(R.id.allSelect);

        rootView.findViewById(R.id.allSelect).setOnClickListener(this);
        rootView.findViewById(R.id.payBtn).setOnClickListener(this);
        rootView.findViewById(R.id.deleteBtn).setOnClickListener(this);
        rootView.findViewById(R.id.saveBtn).setOnClickListener(this);

    }

    public void setShopcarEventListener(IShopcarEventListener listener) {
        this.iShopcarEventListener = listener;
    }

    @Override
    public void onEditChange(boolean isEdit) {
        if (isEdit) {
            normalLayout.setVisibility(GONE);
            editLayout.setVisibility(VISIBLE);
        } else {
            normalLayout.setVisibility(VISIBLE);
            editLayout.setVisibility(GONE);
        }
    }

    @Override
    public void onProductSelectChanged(boolean isSelected, ShopCartBean.ResultBean shopcarData) {

    }

    @Override
    public void onProductCountChanged(ShopCartBean.ResultBean shopcarData, int count) {

    }


    @Override
    public void onAllSelectChanged(boolean isAllSelected) {

    }

    @Override
    public void onPayEventChanged(float payValue) {

    }

    @Override
    public void onProductDeleted(ShopCartBean.ResultBean shopcarData) {

    }

    @Override
    public void onProductSaved() {

    }

    @Override
    public void onClick(View v) {
        if (iShopcarEventListener == null) {
            return;
        }
        if (v.getId() == R.id.allSelect) {
            if (allSelectCheckbox.isChecked()) {
                Log.i("TAGddd", "onAllSelectChanged: ");
                iShopcarEventListener.onAllSelectChanged(true);
            } else {
                iShopcarEventListener.onAllSelectChanged(false);
            }

        } else if (v.getId() == R.id.payBtn) {
            if (TextUtils.isEmpty(payVlaue.getText().toString())) {
                return;
            }
            iShopcarEventListener.onPayEventChanged(Float.valueOf(payVlaue.getText().toString()));
        } else if (v.getId() == R.id.saveBtn) {
            iShopcarEventListener.onProductSaved();
        } else if (v.getId() == R.id.deleteBtn) {
            for (ShopCartBean.ResultBean item : CacheManager.getInstance().getShopCartBean().getResult()) {
                if (!item.isProductSelected()){
                    continue;
                }
                iShopcarEventListener.onProductDeleted(item);
            }
        }
    }

    //通知payview去更新价格,每次更新价格时，都是去缓存中获取数据，对已选择的产品进行价格累计.
    public void notifyMoneyChanged() {
        float sumValue = 0;
        for(ShopCartBean.ResultBean item: CacheManager.getInstance().getShopCartBean().getResult()) {
            if (!item.isProductSelected()) {
                continue;
            }

            float productPrice = Float.valueOf(item.getProductPrice());
            int productNum = Integer.valueOf(item.getProductNum());
            sumValue+= productNum*productPrice;
        }

        payVlaue.setText(sumValue+"");
    }
}
