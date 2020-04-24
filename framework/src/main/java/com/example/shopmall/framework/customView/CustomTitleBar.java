package com.example.shopmall.framework.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.shopmall.framework.R;

public class CustomTitleBar extends RelativeLayout {
    private boolean right_text_is_show = false;
    private ImageView rightIv;
    private ImageView leftIv;
    private TextView rightTv;
    private TextView titleTv;

    private String title_text;
    private String right_text;
    private int left_imag_id;
    private int right_imag_id;

    public CustomTitleBar(Context context) {
        super(context);
        initView(context);
    }
    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initValue(context,attrs);
    }
    public CustomTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initValue(context,attrs);
    }


    private void initView(Context context) {
        // 拿到布局 add到当前页面
       LayoutInflater.from(context).inflate(R.layout.view_title_bar,this);
       // 拿到布局控件
        leftIv = findViewById(R.id.title_bar_lift_iv);
        rightIv = findViewById(R.id.title_bar_right_iv);
        rightTv = findViewById(R.id.title_bar_right_text);
        titleTv = findViewById(R.id.title_bar_title_text);
        // 可对 控件进项操作

    }

    private void initValue(Context context, AttributeSet attrs) {
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        right_text_is_show = typedArray.getBoolean(R.styleable.CustomTitleBar_isRightTextShow,false);
        title_text = typedArray.getString(R.styleable.CustomTitleBar_title);
        right_text = typedArray.getString(R.styleable.CustomTitleBar_rightText);
        left_imag_id = typedArray.getResourceId(R.styleable.CustomTitleBar_liftIvId,R.mipmap.top_bar_left_back);
        right_imag_id = typedArray.getResourceId(R.styleable.CustomTitleBar_rightIvId,R.mipmap.icon_more);

        // 设置属性
        rightTv.setText(right_text);
        if (right_text_is_show){
            rightTv.setVisibility(VISIBLE);
        }else {
            rightTv.setVisibility(GONE);
        }
        titleTv.setText(title_text);

        leftIv.setImageResource(left_imag_id);
        rightIv.setImageResource(right_imag_id);

        this.setBackgroundColor(Color.WHITE); // 默认背景为白色
    }

    // 对外提供 获取控件的get方法


    public ImageView getRightIv() {
        return rightIv;
    }

    public ImageView getLeftIv() {
        return leftIv;
    }

    public TextView getRightTv() {
        return rightTv;
    }

    public TextView getTitleTv() {
        return titleTv;
    }
}
