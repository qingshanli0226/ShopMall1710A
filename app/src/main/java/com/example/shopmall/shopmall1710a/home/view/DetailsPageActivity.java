package com.example.shopmall.shopmall1710a.home.view;

import android.util.Log;
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
import com.example.shopmall.shopmall1710a.main.presenter.AddShopcarPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ROUTER_PATH_DETAILS_PAGE_ACTIVITY)
public class DetailsPageActivity extends BaseActivity implements View.OnClickListener , CustomTitleBar.OnCustomTitleBarLisenner {
    public AddShopcarPresenter addShopcarPresenter;


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
        findViewById(R.id.addShopCarBtn).setOnClickListener(this);
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
        addShopcarPresenter = new AddShopcarPresenter(this);
        List<IPresenter> iPresenterList = new ArrayList<>();
        iPresenterList.add(addShopcarPresenter);
        return iPresenterList;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        if (requestCode == 100){
            Log.i("boss", "onHttpReceived: 添加购物车成功");

        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addShopCarBtn: // 添加商品到购物车
                Log.i("boss", "onClick: 点击添加购物车");
                addShopcarPresenter.addParams();
                addShopcarPresenter.postHttpDataWithJson(100);
                break;
        }
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
