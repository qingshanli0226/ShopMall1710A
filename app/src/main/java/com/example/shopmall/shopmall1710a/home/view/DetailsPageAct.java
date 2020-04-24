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
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.model.HomeEntity;

import java.util.List;

@Route(path = Constant.ROUTER_PATH_DETAILS_PAGE_ACT)
public class DetailsPageAct extends BaseActivity implements View.OnClickListener {
    @Autowired()
    HomeEntity.ResultBean.RecommendInfoBean msg;


    private CustomTitleBar detailsTitleBar;
    private ImageView details_figure;
    private TextView details_name;
    private TextView details_price;

    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.act_details_page;
    }

    @Override
    public void initView() {
        detailsTitleBar = findViewById(R.id.detailsTitleBar);
        detailsTitleBar.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 点击回退
                finish(); // 销毁当前页面
            }
        });

        details_figure = findViewById(R.id.details_figure);
        details_name = findViewById(R.id.details_name);
        details_price = findViewById(R.id.details_price);

    }

    @Override
    public void initData() {
        Glide.with(this)
                .load(Constant.BASE_IMG+msg.getFigure())
                .into(details_figure);
        details_name.setText(msg.getName());
        details_price.setText(msg.getCover_price());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
