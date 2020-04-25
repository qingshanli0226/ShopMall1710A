package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.view.BottomBar;
import com.example.shopmall.framework.view.ButtonInfo;
import com.example.shopmall.framework.view.ToorBar;
import com.example.shopmall.net.BaseBean;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecommendActivity extends BaseActivity<ResultBean> {
    private ResultBean.RecommendInfoBean recommend;
    private List<ResultBean.RecommendInfoBean> list = new ArrayList<>();
    private List<ButtonInfo> list1 = new ArrayList<>();
    private ImageView mPic;
    private TextView mTitle1;
    private TextView mTitle2;
    private TextView mPrice;
    private BottomBar mBottomBarRecommend;

    @Override
    protected void initData() {
        list.add(recommend);
        Glide.with(this).load("http://49.233.93.155:8080/atguigu/img"+recommend.getFigure()).into(mPic);
        mTitle1.setText(recommend.getName());
        mPrice.setText("￥"+recommend.getCover_price());
    }

    @Override
    protected List<IPresenter<ResultBean>> getPresenter() {
        return null;
    }
    @Override
    protected void initView() {
        Intent intent = getIntent();
        recommend = (ResultBean.RecommendInfoBean) intent.getSerializableExtra("recommend");
        String homeData = CacheManager.getInstance().getHomeData();
        mPic = (ImageView) findViewById(R.id.pic);
        mTitle1 = (TextView) findViewById(R.id.title1);
        mTitle2 = (TextView) findViewById(R.id.title2);
        mPrice = (TextView) findViewById(R.id.price);
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
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_recommend2;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, ResultBean data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
