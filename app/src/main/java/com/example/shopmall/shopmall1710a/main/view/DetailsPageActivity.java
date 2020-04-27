package com.example.shopmall.shopmall1710a.main.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.*;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.customView.CustomTitleBar;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.entity.HomeEntity;
import com.example.shopmall.shopmall1710a.main.presenter.AddOneProductPresenter;
import com.example.shopmall.shopmall1710a.main.presenter.InventoryPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ROUTER_PATH_DETAILS_PAGE_ACTIVITY)
public class DetailsPageActivity extends BaseActivity implements View.OnClickListener , CustomTitleBar.OnCustomTitleBarLisenner
, CacheManager.IShopCountRecevedLisener {


    @Autowired()
    HomeEntity.ResultBean.RecommendInfoBean msg;

    private InventoryPresenter inventoryPresenter;
    private AddOneProductPresenter addOneProductPresenter;

    private ImageView detailsFigure; // 图片
    private TextView detailsName;
    private TextView detailsPrice;
    private TextView shortCarNum;
    private Button addShopCarBtn;
    private float[] mCurrentPosition = new float[2];
    private ValueAnimator valueAnimator;
    private ImageView imageView;

    @Override
    public int bindLayout() {
        return R.layout.activity_details_page;
    }

    @Override
    public void initView() {
        CacheManager.getInstance().registerCountLisenner(this);
        detailsFigure = findViewById(R.id.detailsFigure);
        detailsFigure.setOnClickListener(this);
        detailsName = findViewById(R.id.detailsName);
        detailsPrice = findViewById(R.id.detailsPrice);
        shortCarNum = findViewById(R.id.shortCarNum);
        addShopCarBtn = findViewById(R.id.addShopCarBtn);
        addShopCarBtn.setOnClickListener(this);

    }



    @Override
    public void initData() {
        mCustomTitleBar.setOnCustomTitleBarLisenner(this);
        Glide.with(this)
                .load(Constant.BASE_IMG+msg.getFigure())
                .into(detailsFigure);
        detailsName.setText(msg.getName());
        detailsPrice.setText(msg.getCover_price());

        shortCarNum.setText(SPUtils.getInstance().getInt(Constant.SP_SHOP_COUNT)+"");
    }

    @Override
    public List<IPresenter> getPresenter() {
        inventoryPresenter = new InventoryPresenter(this);
        addOneProductPresenter = new AddOneProductPresenter(this);
        List<IPresenter> list = new ArrayList<>();
        list.add(inventoryPresenter);
        list.add(addOneProductPresenter);
        return list;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        if (requestCode == 100){
            String str = (String) data;
            Log.i("boss", "onHttpReceived: "+str);
            if (str.equals("1")){ // 库存充足
                Log.i("boss", "onHttpReceived: 库存充足");
                // 调用添加购物车接口
                addOneProductPresenter.addParams(msg.getProduct_id(),1
                ,msg.getName(),msg.getFigure(),msg.getCover_price());
                addOneProductPresenter.postHttpDataWithJson(200);
            }
        }else if (requestCode == 200){ // 添加成功
            Log.i("boss", "onHttpReceived: 添加购物车"+data);
            CacheManager.getInstance().startShopMallService();

        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addShopCarBtn: // 添加商品到购物车
                /*
                    1.先判断库存
                 */
                inventoryPresenter.addparams(Integer.parseInt(msg.getProduct_id()),1);
                inventoryPresenter.postHttpData(100);

                starAnamation();

                break;
            case R.id.detailsFigure:
                break;
        }
    }

    // 动画
    private void starAnamation() {
        this.imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.logo);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(100,100);
        this.addContentView(imageView,layoutParams);

        // 获取商品的位置
        int[] location = new int[2];
        detailsFigure.getLocationOnScreen(location);

        // 获取购物车的位置
        int[] location1 = new int[2];
        addShopCarBtn.getLocationOnScreen(location1);



        Path path = new Path();
        path.moveTo(location[0]+addShopCarBtn.getWidth(),location[1]);

        path.cubicTo(location[0]+1000,location[1],location[0],location[1]+1500,location1[0],location1[1]);

        final PathMeasure pathMeasure = new PathMeasure(path, false);

        valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();

                pathMeasure.getPosTan(value,mCurrentPosition,null);
                imageView.setTranslationX(mCurrentPosition[0]);
                imageView.setTranslationY(mCurrentPosition[1]);
            }
        });

        valueAnimator.start();


        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 动画结束的监听
                imageView.setVisibility(View.GONE);
            }
        });
    }


    // title 的点击事件
    @Override
    public void leftOk() { // 左边图片
        finish(); // 回退
    }

    @Override
    public void rightOk() {

    }

    // 获取购物车数量成功!
    @Override
    public void onShopcarCountReceived(int conunt) {
        Log.i("boss", "onShopcarCountReceived: ----------------------------");
        shortCarNum.setText(conunt+"");
    }
}
