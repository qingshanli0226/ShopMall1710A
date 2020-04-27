package com.example.shopmall.shopmall1710a.main.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.StringRes;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtill;
import com.example.shopmall.framework.base.bean.ShopCartBean;
import com.example.shopmall.framework.base.view.BaseActivity;
import com.example.shopmall.framework.base.view.CustomToolBar;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.view.BetterLoginActivity;
import com.example.shopmall.shopmall1710a.main.bean.Goods;
import com.example.shopmall.shopmall1710a.main.bean.GoodsBean;
import com.example.shopmall.shopmall1710a.main.presenter.AddShopCartPresenter;
import com.example.shopmall.shopmall1710a.main.presenter.CheckOneProductInventoryPresenter;
import com.example.shopmall.shopmall1710a.main.utils.VirtualkeyboardHeight;
import com.example.shopmall.shopmall1710a.main.view.views.NumberAddSubView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GoodDetailActivity extends BaseActivity<AddShopCartPresenter, String> implements View.OnClickListener {
    private android.widget.ImageView actDetIvGoodInfoImage;
    private android.widget.TextView actDetTvGoodInfoName;
    private android.widget.TextView actDetTvGoodInfoDesc;
    private android.widget.TextView actDetTvGoodInfoPrice;
    private android.widget.TextView actDetTvGoodInfoStore;
    private android.widget.TextView actDetTvGoodInfoStyle;
    private android.webkit.WebView actDetWebGoodInfoMore;
    private android.widget.LinearLayout actDetLlGoodsRoot;
    private android.widget.TextView actDetTvGoodInfoCallcenter;
    private android.widget.TextView actDetTvGoodInfoCollection;
    private android.widget.TextView actDetTvGoodInfoCart;
    private android.widget.Button actDetBtnGoodInfoAddcart;

    private AddShopCartPresenter addShopCartPresenter;
    private CheckOneProductInventoryPresenter checkOneProductInventoryPresenter;


    private GoodsBean goodsBean = new GoodsBean();

    @Override
    public int bindLayout() {
        return R.layout.layout_detail;
    }

    @Override
    public void initView() {

        addShopCartPresenter = new AddShopCartPresenter();
        checkOneProductInventoryPresenter = new CheckOneProductInventoryPresenter();
        checkOneProductInventoryPresenter.attachView(this);
        addShopCartPresenter.attachView(this);

        initToolBar();
        actDetIvGoodInfoImage = findViewById(R.id.act_det_iv_good_info_image);
        actDetTvGoodInfoName = findViewById(R.id.act_det_tv_good_info_name);
        actDetTvGoodInfoDesc = findViewById(R.id.act_det_tv_good_info_desc);
        actDetTvGoodInfoPrice = findViewById(R.id.act_det_tv_good_info_price);
        actDetTvGoodInfoStore = findViewById(R.id.act_det_tv_good_info_store);
        actDetTvGoodInfoStyle = findViewById(R.id.act_det_tv_good_info_style);
        actDetWebGoodInfoMore = findViewById(R.id.act_det_web_good_info_more);
        actDetLlGoodsRoot = findViewById(R.id.act_det_ll_goods_root);
        actDetTvGoodInfoCallcenter = findViewById(R.id.act_det_tv_good_info_callcenter);
        actDetTvGoodInfoCollection = findViewById(R.id.act_det_tv_good_info_collection);
        actDetTvGoodInfoCart = findViewById(R.id.act_det_tv_good_info_cart);
        actDetBtnGoodInfoAddcart = findViewById(R.id.act_det_btn_good_info_addcart);

        actDetBtnGoodInfoAddcart.setOnClickListener(this);

    }

    public void initToolBar() {
        toolbar = findViewById(R.id.act_det_toolbar);
        toolbar.setOnToolBarListener(this);
        toolbar.tvLeft.setVisibility(View.GONE);
        toolbar.tvRight.setVisibility(View.GONE);
    }


    @Override
    public void inject() {

    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        Goods.RecommendInfoBean bean = intent.getParcelableExtra("goods");


        goodsBean.setCover_price(bean.getCover_price());
        goodsBean.setName(bean.getName());
        goodsBean.setFigure(bean.getFigure());
        goodsBean.setProduct_id(bean.getProduct_id());


        Glide.with(this).load(Constant.BASE_URL_IMG + bean.getFigure())
                .into(actDetIvGoodInfoImage);
        actDetTvGoodInfoPrice.setText("￥" + bean.getCover_price());
        actDetTvGoodInfoName.setText(bean.getName());

        toolbar.setToolTitle(R.string.toolbar_name);

    }

    private void addPost() {
        addShopCartPresenter.addParams(goodsBean.getProduct_id(), goodsBean.getName(), goodsBean.getFigure(), goodsBean.getCover_price());
        addShopCartPresenter.postHttpDataWithJson(200);
    }


    @Override
    public void onHtttpReceived(int requestCode, String data) {
//        if (requestCode == 100) {
//            String productNumStr =  data;
//            int productNum = Integer.valueOf(productNumStr);
//            if (productNum >= 1) {
//
//            }
//        } else if (requestCode == 200) {
//            Toast.makeText(this, (String) data, Toast.LENGTH_SHORT).show();
//            CacheManager.getInstance().addShopcarCount(this, 1);
//        }
//        Log.e("TAG", "66:" + goodsBean.toString());
//        Toast.makeText(GoodDetailActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(this, "" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_det_btn_good_info_addcart:
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    Intent intent = new Intent();
                    intent.setClass(this, BetterLoginActivity.class);
                    startActivity(intent);
                    return;
                }

//              showPopwindow();
//                checkOneProductInventoryPresenter.addParms(goodsBean.getProduct_id());
//                checkOneProductInventoryPresenter.postHttpData(100);

                addGood();


                break;
        }
    }

    private void addGood() {
        SpUtill spUtill = new SpUtill(this);
        List<ShopCartBean.ResultBean> result = new ArrayList<>();
        String shopcartData = spUtill.getShopcartData();
        if (shopcartData != null) {
            result = new Gson().fromJson(shopcartData, ShopCartBean.class).getResult();
        }
        ShopCartBean.ResultBean shopCartBean
                = new ShopCartBean.ResultBean(goodsBean.getProduct_id(), goodsBean.getName(), "1", goodsBean.getFigure(), goodsBean.getCover_price());
        result.add(shopCartBean);
        ShopCartBean shopCartBean1 = new ShopCartBean("200", "成功", result);
        String s = new Gson().toJson(shopCartBean1);
        CacheManager.getInstance().spUtill.saveShopcartData(s);
        CacheManager.getInstance().spUtill.saveShopcarCount(this, result.size());
        Toast.makeText(this, "" + result.size(), Toast.LENGTH_SHORT).show();
    }


    private void showPopwindow() {

        // 1 利用layoutInflater获得View
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);


        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        NumberAddSubView nas_goodinfo_num = (NumberAddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        // 加载图片
        Glide.with(this).load(Constant.BASE_URL_IMG + goodsBean.getFigure()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(goodsBean.getName());
        // 显示价格
        tv_goodinfo_price.setText(goodsBean.getCover_price());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(8);
        nas_goodinfo_num.setValue(1);

        nas_goodinfo_num.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                goodsBean.setNumber(value);
            }

            @Override
            public void subNumner(View view, int value) {
                goodsBean.setNumber(value);
            }
        });

        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //添加购物车
                addPost();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });

        // 5 在底部显示
        window.showAtLocation(GoodDetailActivity.this.findViewById(R.id.act_det_ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodDetailActivity.this));

    }
}
