package com.example.shopmall.framework.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.shopmall.framework.R;

public class CustomTitleBar extends RelativeLayout implements View.OnClickListener {
    // 控价
    private ImageView leftImage,rightImage;
    private TextView titleTextView,rightTextView;
    // 自定义属性值
    private String titleText,rightText;
    private int leftImagId,rightImageId;
    private boolean isRightTextShow; // 判定右边文字是否显示
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
        // 获取视图 添加 自身布局
        LayoutInflater.from(context).inflate(R.layout.view_title_bar,this);
        // 获取 控件
        leftImage = findViewById(R.id.leftImage);
        rightImage = findViewById(R.id.rightImage);
        titleTextView = findViewById(R.id.titleText);
        rightTextView = findViewById(R.id.rightText);

        leftImage.setOnClickListener(this);
        rightImage.setOnClickListener(this);
    }

    private void initValue(Context context, AttributeSet attrs) {
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        titleText = typedArray.getString(R.styleable.CustomTitleBar_titleText);
        rightText = typedArray.getString(R.styleable.CustomTitleBar_rightText);
        leftImagId = typedArray.getResourceId(R.styleable.CustomTitleBar_leftImageId,R.drawable.back);
        rightImageId = typedArray.getResourceId(R.styleable.CustomTitleBar_rightImageId,R.drawable.dot);
        isRightTextShow = typedArray.getBoolean(R.styleable.CustomTitleBar_isRightTextShow,false);

        // 把自定义属性 设置给控件
        if (isRightTextShow){
            rightTextView.setVisibility(VISIBLE);
        }else {
            rightTextView.setVisibility(GONE);
        }
        titleTextView.setText(titleText);
        leftImage.setImageResource(leftImagId);
        rightImage.setImageResource(rightImageId);
        rightTextView.setText(rightText);
    }

    // 点击事件
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.leftImage) {
            if (onCustomTitleBarLisenner != null){
                onCustomTitleBarLisenner.leftOk();
            }
        }else if (view.getId() == R.id.rightImage){
            if (onCustomTitleBarLisenner != null){
                onCustomTitleBarLisenner.rightOk();
            }
        }
    }

    public interface OnCustomTitleBarLisenner{
        void leftOk();
        void rightOk();
    }
    private OnCustomTitleBarLisenner onCustomTitleBarLisenner;

    public void setOnCustomTitleBarLisenner(OnCustomTitleBarLisenner onCustomTitleBarLisenner) {
        this.onCustomTitleBarLisenner = onCustomTitleBarLisenner;
    }
}
