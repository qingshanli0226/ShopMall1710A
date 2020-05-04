package com.example.shopmall.shopmall1710a.recommend;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IBaseView;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.base.ShopCartBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.framework.view.BottomBar;
import com.example.shopmall.framework.view.ButtonInfo;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.greendao.DbController;
import com.example.shopmall.shopmall1710a.greendao.Product;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.example.shopmall.shopmall1710a.login.view.LoginActivity;
import com.example.shopmall.shopmall1710a.recommend.presenter.AddShopcarPresenter;
import com.example.shopmall.shopmall1710a.recommend.presenter.RecommendPresenter;
import com.example.shopmall.shopmall1710a.recommend.presenter.UpdateProductNumPresenter;

import java.util.ArrayList;
import java.util.List;

public class RecommendActivity extends BaseActivity<Object> implements IBaseView<Object>, BottomBar.bottomBarListener {
    private List<ButtonInfo> list1 = new ArrayList<>();
    private ImageView mPic;
    private TextView mTitle1;
    private TextView mTitle2;
    private TextView mPrice;
    private BottomBar mBottomBarRecommend;
    private Button intoCar;
    private ShopCartBean.ShopcarData shopcarData = new ShopCartBean.ShopcarData();
    private RecommendPresenter recommendPresenter;
    private String productImageUrl;
    private String productPrice;
    private String productName;
    private String productId;
    private String productNewNum;
    private AddShopcarPresenter addShopcarPresenter;
    private UpdateProductNumPresenter updateProductNumPresenter;
    @Override
    protected void initData() {

    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        recommendPresenter=new RecommendPresenter();
        addShopcarPresenter=new AddShopcarPresenter();
        updateProductNumPresenter=new UpdateProductNumPresenter();
        List<IPresenter<Object>> presenters = new ArrayList<>();
        presenters.add(addShopcarPresenter);
        presenters.add(recommendPresenter);
        presenters.add(updateProductNumPresenter);
        return presenters;
    }
    @Override
    protected void initView() {

        Intent intent = getIntent();
        productImageUrl = intent.getStringExtra("productImageUrl");
        productPrice = intent.getStringExtra("productPrice");
        productId = intent.getStringExtra("productId");
        productName = intent.getStringExtra("productName");
        mPic = (ImageView) findViewById(R.id.pic);
        mTitle1 = (TextView) findViewById(R.id.title1);
        mTitle2 = (TextView) findViewById(R.id.title2);
        mPrice = (TextView) findViewById(R.id.price);
        intoCar = (Button) findViewById(R.id.into_car);
        Glide.with(this).load("http://49.233.93.155:8080/atguigu/img"+productImageUrl).into(mPic);
        mTitle1.setText(productName);
        mPrice.setText("￥"+productPrice);
        mBottomBarRecommend = (BottomBar) findViewById(R.id.bottomBar_recommend);
        toorBar.showAll(false);
        toorBar.showLeftImg(true);
        toorBar.showRightImg(true);
        toorBar.showToorBarTitle(true);
        toorBar.setTitle(R.string.recommend_title);
        toorBar.setTextSize(R.id.ToolBarTitle,20);
        toorBar.setLeftImg(R.drawable.top_bar_left_back);
        toorBar.setRightImg(R.drawable.icon_more);
        list1.add(new ButtonInfo("联系客服",R.drawable.icon_callserver_unpressed));
        list1.add(new ButtonInfo("收藏",R.drawable.good_uncollected));
        list1.add(new ButtonInfo("购物车",R.drawable.icon_good_detail_cart));
        mBottomBarRecommend.setBtnData(list1,false);
        mBottomBarRecommend.setBottomBarListener(this);
        intoCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    //跳到登录界面
                    Intent intent = new Intent();
                    intent.setClass(RecommendActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                //第二步检查商品是否有库存
                recommendPresenter.addParms(productId);
                recommendPresenter.postHttpData(100);
            }
        });
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_recommend2;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {
        if (requestCode == 100) {
            String productNumStr = (String)data;
            int productNum = Integer.valueOf(productNumStr);
            if (productNum>=1) {//代表有库存
                //先判断购物车是否有该产品
                shopcarData.setProductId(productId);
                shopcarData.setProductName(productName);
                shopcarData.setProductPrice(productPrice);
                shopcarData.setUrl(productImageUrl);
                if (!checkIfShopcarHasProduct(productId)) {
                    //商品有库存的话，就添加该商品到购物车
                    shopcarData.setProductSelected(true);//添加上之后就默认是选择的
                    shopcarData.setProductNum("1");//第一次添加肯定为1
                    addShopcarPresenter.addParams(productId, productName, productImageUrl, productPrice);
                    addShopcarPresenter.postHttpDataWithJson(200);
                } else {
                    String oldNum;
                    int newNum = 0;
                    for(ShopCartBean.ShopcarData shopcarData:CacheManager.getInstance().getShopCartBean().getResult()) {
                        if (productId.equals(shopcarData.getProductId())) {
                            oldNum = shopcarData.getProductNum();
                            newNum = Integer.valueOf(oldNum)+1;
                            productNewNum = String.valueOf(newNum);
                        }
                    }
                    updateProductNumPresenter.addParams(productId, productName, productImageUrl, productPrice,productNewNum);
                    updateProductNumPresenter.postHttpDataWithJson(300);
                }
            }
        }else if (requestCode == 200) {
            Toast.makeText(this, (String)data, Toast.LENGTH_SHORT).show();
            //修改购物车产品数量
            CacheManager.getInstance().addNewShopcardata(this,1,shopcarData);
        } else if (requestCode == 300) {
            Toast.makeText(this, "更新成功:"+(String)data, Toast.LENGTH_SHORT).show();
            shopcarData.setProductNum(productNewNum);
            CacheManager.getInstance().updateShopcarProductNum(this, productId,shopcarData);
        }
    }
    private boolean checkIfShopcarHasProduct(String productId) {
        for(ShopCartBean.ShopcarData shopcarData:CacheManager.getInstance().getShopCartBean().getResult()) {
            if (productId.equals(shopcarData.getProductId())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
    @Override
    public void showLoading() {
        findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }

    @Override
    public void onSecondClick() {
        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        DbController dbController = DbController.getDbController(this);
        Product product = new Product();
        product.setId(Long.valueOf(productId));
        product.setName(productName);
        dbController.insert(product);

        Log.e("TAG", "onSecondClick: "+dbController.queryAll().size() );
    }
}
