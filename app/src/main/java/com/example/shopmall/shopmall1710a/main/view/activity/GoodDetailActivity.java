package com.example.shopmall.shopmall1710a.main.view.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.ColorDrawable;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.*;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;
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
    private TextView actDetTvGoodInfoCart;
    private android.widget.Button actDetBtnGoodInfoAddcart;

    private AddShopCartPresenter addShopCartPresenter;
    private CheckOneProductInventoryPresenter checkOneProductInventoryPresenter;

    private List<ShopCartBean.ResultBean> result = new ArrayList<>();
    private SpUtill spUtill;


    private GoodsBean goodsBean = new GoodsBean();
    private RelativeLayout linl;
    private CustomToolBar actDetToolbar;

    private int flag = 0;
    private int tag = 0;


    private PathMeasure mPathMeasure;

    /**
     * 贝塞尔曲线中间过程的点的坐标
     */
    private float[] mCurrentPosition = new float[2];
    private int i = 0;
    private TextView fragCartNum;


    @Override
    public int bindLayout() {
        return R.layout.layout_detail;
    }

    @Override
    public void initView() {
        spUtill = new SpUtill(this);
        addShopCartPresenter = new AddShopCartPresenter();
        checkOneProductInventoryPresenter = new CheckOneProductInventoryPresenter();
        checkOneProductInventoryPresenter.attachView(this);
        addShopCartPresenter.attachView(this);

        initToolBar();
        actDetIvGoodInfoImage = findViewById(R.id.act_det_iv_good_info_image);
        linl = findViewById(R.id.linl);
        actDetToolbar = findViewById(R.id.act_det_toolbar);
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
        fragCartNum = findViewById(R.id.frag_cart_num);

        getCartCache();

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
                addCart(actDetIvGoodInfoImage);


                break;
        }
    }

    private void addGood() {


        ShopCartBean.ResultBean shopCartBean
                = new ShopCartBean.ResultBean(goodsBean.getProduct_id(), goodsBean.getName(), "1", goodsBean.getFigure(), goodsBean.getCover_price());
        for (int i1 = 0; i1 < result.size(); i1++) {
            if (result.get(i1).getProductId().equals(goodsBean.getProduct_id())) {
                flag = 1;
                tag = i1;
            }
        }
        if (flag == 1) {
            int num = Integer.parseInt(result.get(tag).getProductNum());
            result.get(tag).setProductNum(String.valueOf(num + 1));
        } else {
            result.add(shopCartBean);
        }

        fragCartNum.setText(result.size() + "");
        ShopCartBean shopCartBean1 = new ShopCartBean("200", "成功", result);
        String s = new Gson().toJson(shopCartBean1);
        CacheManager.getInstance().spUtill.saveShopcartData(s);
        CacheManager.getInstance().spUtill.saveShopcarCount(this, result.size());
        Toast.makeText(this, "" + result.size(), Toast.LENGTH_SHORT).show();
    }

    public void getCartCache() {
        String shopcartData = spUtill.getShopcartData();
        if (shopcartData != null) {
            result = new Gson().fromJson(shopcartData, ShopCartBean.class).getResult();
            if (result.size() > 0) {
                fragCartNum.setVisibility(View.VISIBLE);
                fragCartNum.setText(result.size() + "");
            }
        }
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


    /**
     * ★★★★★把商品添加到购物车的动画效果★★★★★
     *
     * @param iv
     */
    private void addCart(ImageView iv) {
//   一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        final ImageView goods = new ImageView(this);
        goods.setImageDrawable(iv.getDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        linl.addView(goods, params);

//    二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        linl.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        iv.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        actDetTvGoodInfoCart .getLocationInWindow(endLoc);


//    三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;


        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLocation[0] + actDetTvGoodInfoCart.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

//    四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        mPathMeasure = new PathMeasure(path, false);

        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
//   五、 开始执行动画
        valueAnimator.start();

//   六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1
                // 把移动的图片imageview从父布局里移除
                linl.removeView(goods);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


//    class MyVH extends RecyclerView.ViewHolder {
//
//        private ImageView iv;
//        private TextView buy;
//
//        public MyVH(View itemView) {
//            super(itemView);
//            iv = (ImageView) itemView.findViewById(R.id.iv);
//            buy = (TextView) itemView.findViewById(R.id.buy);
//        }
//    }


}
