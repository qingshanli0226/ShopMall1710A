package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class RecommendActivity extends AppCompatActivity {
    private ResultBean.RecommendInfoBean recommend;
    private List<ResultBean.RecommendInfoBean> list = new ArrayList<>();
    private List<ButtonInfo> list1 = new ArrayList<>();
    private ToorBar mToorBarRecommend;
    private ImageView mPic;
    private TextView mTitle1;
    private TextView mTitle2;
    private TextView mPrice;
    private BottomBar mBottomBarRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend2);
        Intent intent = getIntent();
         recommend = (ResultBean.RecommendInfoBean) intent.getSerializableExtra("recommend");

        initView();
        initData();
    }

    private void initData() {
        list.add(recommend);
        Glide.with(this).load("http://49.233.93.155:8080/atguigu/img"+recommend.getFigure()).into(mPic);
        mTitle1.setText(recommend.getName());
        mPrice.setText("￥"+recommend.getCover_price());
    }

    private void initView() {
        String homeData = CacheManager.getInstance().getHomeData();
        mToorBarRecommend = (ToorBar) findViewById(R.id.toor_bar_recommend);
        mPic = (ImageView) findViewById(R.id.pic);
        mTitle1 = (TextView) findViewById(R.id.title1);
        mTitle2 = (TextView) findViewById(R.id.title2);
        mPrice = (TextView) findViewById(R.id.price);
        mBottomBarRecommend = (BottomBar) findViewById(R.id.bottomBar_recommend);
        mToorBarRecommend.showAll(false);
        mToorBarRecommend.showLeftImg(true);
        mToorBarRecommend.showRightImg(true);
        mToorBarRecommend.showToorBarTitle(true);
        mToorBarRecommend.setTitle(R.string.recommend_title);
        mToorBarRecommend.setTextSize(R.id.ToolBarTitle,20);
        mToorBarRecommend.setLeftImg(R.drawable.top_bar_left_back);
        mToorBarRecommend.setRightImg(R.drawable.icon_more);
        list1.add(new ButtonInfo("联系客服",R.drawable.icon_callserver_unpressed));
        list1.add(new ButtonInfo("收藏",R.drawable.good_uncollected));
        list1.add(new ButtonInfo("购物车",R.drawable.icon_good_detail_cart));
        mBottomBarRecommend.setBtnData(list1,false);
    }
}
