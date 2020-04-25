package com.example.shopmall.shopmall1710a.main.view.activity;

import android.content.Intent;
import android.view.View;
import androidx.annotation.StringRes;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseActivity;
import com.example.shopmall.framework.base.view.CustomToolBar;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.Goods;

public class GoodDetailActivity extends BaseActivity {
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

    @Override
    public int bindLayout() {
        return R.layout.layout_detail;
    }

    @Override
    public void initView() {


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

        Glide.with(this).load(Constant.BASE_URL_IMG + bean.getFigure())
                .into(actDetIvGoodInfoImage);
        actDetTvGoodInfoPrice.setText("￥" + bean.getCover_price());
        actDetTvGoodInfoName.setText(bean.getName());

        toolbar.setToolTitle(R.string.toolbar_name);
    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
