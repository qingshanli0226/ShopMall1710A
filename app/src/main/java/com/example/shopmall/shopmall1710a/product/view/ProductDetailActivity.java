package com.example.shopmall.shopmall1710a.product.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.view.BetterLoginActivity;
import com.example.shopmall.shopmall1710a.product.presenter.AddShopcarPresenter;
import com.example.shopmall.shopmall1710a.product.presenter.CheckOneProductInventoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends BaseActivity<Object> implements View.OnClickListener, CacheManager.IShopCountRecevedLisener {
    private String productImageUrl;
    private String productPrice;
    private String productName;
    private String productId;
    private TextView shopcarCountTv;

    private CheckOneProductInventoryPresenter checkOneProductInventoryPresenter;
    private AddShopcarPresenter addShopcarPresenter;

    @Override
    protected void initData() {
        CacheManager.getInstance().registerShopCountListener(this);
        //如果登录成功，sp中已经存储了购物产品的数量
        if (ShopUserManager.getInstance().isUserLogin()) {
            shopcarCountTv.setText(String.valueOf(CacheManager.getInstance().getShopcarCount(this)));
        }
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        checkOneProductInventoryPresenter = new CheckOneProductInventoryPresenter();
        addShopcarPresenter = new AddShopcarPresenter();
        List<IPresenter<Object>> presenters = new ArrayList<>();
        presenters.add(checkOneProductInventoryPresenter);
        presenters.add(addShopcarPresenter);
        return presenters;
    }

    @Override
    protected void initView() {
        toolBar.showRightImage();
        toolBar.showRightTv(false);
        Intent intent = getIntent();
        productImageUrl = intent.getStringExtra("productImageUrl");
        productPrice = intent.getStringExtra("productPrice");
        productId = intent.getStringExtra("productId");
        productName = intent.getStringExtra("productName");


        ImageView productImg = findViewById(R.id.productDetailImage);
        TextView productPriceTv = findViewById(R.id.productDetailPrice);
        shopcarCountTv = findViewById(R.id.shopCarCountTV);
        Glide.with(this).load(Constant.BASE_IMAGE_URL+productImageUrl).into(productImg);
        productPriceTv.setText(productPrice);

        findViewById(R.id.addProduct).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void destroy() {
        CacheManager.getInstance().unRegisterShopCountListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addProduct) {
            //判断当前用户是否登录
            if (!ShopUserManager.getInstance().isUserLogin()) {
                //跳到登录界面
                Intent intent = new Intent();
                intent.setClass(this,BetterLoginActivity.class);
                startActivity(intent);
                return;
            }
            //第二步检查商品是否有库存
            checkOneProductInventoryPresenter.addParms(productId);
            checkOneProductInventoryPresenter.postHttpData(100);
        }
    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode == 100) {
            String productNumStr = (String)data;
            int productNum = Integer.valueOf(productNumStr);
            if (productNum>=1) {//代表有库存
                //商品有库存的话，就添加该商品到购物车
                addShopcarPresenter.addParams(productId, productName,productImageUrl,productPrice);
                addShopcarPresenter.postHttpDataWithJson(200);
            }
        }else if (requestCode == 200) {
            Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
            //修改购物车产品数量
            CacheManager.getInstance().addShopcarCount(this,1);
        }
    }

    @Override
    public void onShopcarCountReceived(final int count) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shopcarCountTv.setText(String.valueOf(count));
            }
        });

    }
}
