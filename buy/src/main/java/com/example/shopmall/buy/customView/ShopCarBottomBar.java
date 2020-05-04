package com.example.shopmall.buy.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.shopmall.buy.R;


public class ShopCarBottomBar extends RelativeLayout {
    private CheckBox shopCarBottomBarCheckBox;
    private TextView shopCarBottomBarTotal;
    private TextView shopCarBottomBarAllMoney;
    private Button shopCarBottomBarCloseMoney;
    private Button shopCarBottomBarRemove;
    private Button shopCarBottomBarCollect;
    private boolean isChecked;

    public ShopCarBottomBar(Context context) {
        super(context);
        initView(context);
    }
    public ShopCarBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initValue(context,attrs);
    }
    public ShopCarBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initValue(context,attrs);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_shop_car_buttom_bar, this);

        shopCarBottomBarCheckBox = inflate.findViewById(R.id.shopCarBottomBarCheckBox);
        shopCarBottomBarTotal = inflate.findViewById(R.id.shopCarBottomBarTotal);
        shopCarBottomBarAllMoney = inflate.findViewById(R.id.shopCarBottomBarAllMoney);
        shopCarBottomBarCloseMoney = inflate.findViewById(R.id.shopCarBottomBarCloseMoney);
        shopCarBottomBarRemove = inflate.findViewById(R.id.shopCarBottomBarRemove);
        shopCarBottomBarCollect = inflate.findViewById(R.id.shopCarBottomBarCollect);

        shopCarBottomBarRemove.setVisibility(GONE); // 默认 编辑模式
        shopCarBottomBarCollect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopCarBottomBarLisenner != null){
                    shopCarBottomBarLisenner.collect();
                }
            }
        });

        shopCarBottomBarCheckBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopCarBottomBarLisenner != null){
                    shopCarBottomBarLisenner.checkBoxAllOk(!isChecked);
                }
            }
        });
        shopCarBottomBarRemove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopCarBottomBarLisenner != null){
                    shopCarBottomBarLisenner.removeOk();
                }
            }
        });
        shopCarBottomBarCloseMoney.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopCarBottomBarLisenner != null){
                    shopCarBottomBarLisenner.closeMoney();
                }
            }
        });

    }
    private void initValue(Context context, AttributeSet attrs) {
    }

    public void setFlag(boolean flag) { // 提供方法来切换模式
        if (flag){ // 编辑模式
            shopCarBottomBarCheckBox.setVisibility(VISIBLE);
            shopCarBottomBarTotal.setVisibility(VISIBLE);
            shopCarBottomBarAllMoney.setVisibility(VISIBLE);
            shopCarBottomBarCloseMoney.setVisibility(VISIBLE);
            shopCarBottomBarRemove.setVisibility(GONE);
            shopCarBottomBarCollect.setVisibility(GONE);

        }else { // 完成模式
            shopCarBottomBarCheckBox.setVisibility(VISIBLE);
            shopCarBottomBarTotal.setVisibility(GONE);
            shopCarBottomBarAllMoney.setVisibility(GONE);
            shopCarBottomBarCloseMoney.setVisibility(GONE);
            shopCarBottomBarRemove.setVisibility(VISIBLE); // 默认 编辑模式
            shopCarBottomBarCollect.setVisibility(VISIBLE);
        }
    }

    public interface ShopCarBottomBarLisenner{
        void checkBoxAllOk(boolean flag); // 全选
        void removeOk(); // 删除
        void closeMoney(); // 结算
        void collect(); // 收藏
    }
    private ShopCarBottomBarLisenner shopCarBottomBarLisenner;

    public void setShopCarBottomBarLisenner(ShopCarBottomBarLisenner shopCarBottomBarLisenner) {
        this.shopCarBottomBarLisenner = shopCarBottomBarLisenner;
    }
    public void setMoney(float money) { // 提供设置金额的方法
        shopCarBottomBarAllMoney.setText(money+"");
    }

    public void setCount(int count){ // 提供设置数量的方法
        shopCarBottomBarCloseMoney.setText("去结算("+count+")");
    }

    public void setCheckBoxAll(boolean flag){
        isChecked = flag;
        shopCarBottomBarCheckBox.setChecked(flag);
    }
}
