package com.example.shopmall.shopmall1710a.home.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomTitleBar;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.entity.HomeEntity;

import java.util.List;

@Route(path = Constant.ROUTER_PATH_DETAILS_PAGE_ACTIVITY)
public class DetailsPageActivity extends BaseActivity implements View.OnClickListener , CustomTitleBar.OnCustomTitleBarLisenner {
    @Autowired()
    HomeEntity.ResultBean.RecommendInfoBean msg;


    private ImageView detailsFigure;
    private TextView detailsName;
    private TextView detailsPrice;



    @Override
    public int bindLayout() {
        return R.layout.activity_details_page;
    }

    @Override
    public void initView() {

        detailsFigure = findViewById(R.id.detailsFigure);
        detailsName = findViewById(R.id.detailsName);
        detailsPrice = findViewById(R.id.detailsPrice);

    }

    @Override
    public void initData() {
        mCustomTitleBar.setOnCustomTitleBarLisenner(this);
        Glide.with(this)
                .load(Constant.BASE_IMG+msg.getFigure())
                .into(detailsFigure);
        detailsName.setText(msg.getName());
        detailsPrice.setText(msg.getCover_price());
    }

    @Override
    public List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onClick(View view) {

    }


    // title 的点击事件
    @Override
    public void leftOk() { // 左边图片
        finish(); // 回退
    }

    @Override
    public void rightOk() {

    }
}
