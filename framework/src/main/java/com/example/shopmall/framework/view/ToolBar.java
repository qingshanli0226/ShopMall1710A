package com.example.shopmall.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopmall.framework.R;

//自定义一个ToolBar，可以覆盖所有页面
public class ToolBar extends LinearLayout implements View.OnClickListener {

    private ToolBarListener toolBarListener;
    private TextView leftTv,rightTv,titleTv;
    private ImageView leftImg, rightImg;

    private int toolBarTitle;
    private int leftImgSrc;
    private int rightImgSrc;
    private boolean isRightDisplay;
    private int clolor;

    public ToolBar(Context context) {
        super(context);
        init(context, null);
    }

    public ToolBar(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    //初始化
    private void init(Context context, AttributeSet attrs) {
        //获取自定义view的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MYToolBar);
        //获取的标题
         toolBarTitle = typedArray.getResourceId(R.styleable.MYToolBar_toolbarTile, -1);
        //获取左侧图片资源的id
        leftImgSrc = typedArray.getResourceId(R.styleable.MYToolBar_toolBarLeftSrc, -1);
        rightImgSrc = typedArray.getResourceId(R.styleable.MYToolBar_toolBarRightSrc, -1);
        //右侧内容是否显示
        isRightDisplay = typedArray.getBoolean(R.styleable.MYToolBar_toolBarRightDisplay, true);
        clolor = typedArray.getColor(R.styleable.MYToolBar_toolbarBackgroundColor, Color.WHITE);


        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.view_tool_bar, this);

        leftTv=rootView.findViewById(R.id.leftTv);
        rightTv=rootView.findViewById(R.id.rightTv);
        leftImg = rootView.findViewById(R.id.leftImag);
        rightImg=rootView.findViewById(R.id.rightImag);
        titleTv = rootView.findViewById(R.id.toolBarTitle);


        if (toolBarTitle!=-1) {
            setTitle(toolBarTitle);
        }
        if (leftImgSrc!=-1) {
            leftImg.setImageResource(leftImgSrc);
        }

        if (rightImgSrc!=-1) {
            rightImg.setImageResource(rightImgSrc);
        }
        if (!isRightDisplay) {
            findViewById(R.id.rightLayout).setVisibility(GONE);
        } else {
            rightImg.setVisibility(VISIBLE);
        }

        findViewById(R.id.toolBarRoot).setBackgroundColor(clolor);


        leftImg.setOnClickListener(this);
        rightImg.setOnClickListener(this);
    }

    public void setToolBarClickListener(ToolBarListener  listener) {
        this.toolBarListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.leftImag) {
            if(toolBarListener != null) {
                toolBarListener.onLeftImgClick();
            }
        }else if (v.getId() == R.id.rightImag) {
            if (toolBarListener != null) {
                toolBarListener.onRightImgClick();
            }
        }
    }

    //参数注解代表必须穿一个R.string.name
    //修改页面标题
    public void setTitle(@StringRes int titleId) {
        String title = getContext().getResources().getString(titleId);
        titleTv.setText(title);
    }

    //该参数注解代表必须传递一个R.mipmap.back
    public void setLeftImageDrawble(@DrawableRes int drawbleId) {
        leftImg.setImageResource(drawbleId);
    }

    //该参数注解代表必须传递一个R.mipmap.back
    public void setRightImageDrawble(@DrawableRes int drawbleId) {
        rightImg.setImageResource(drawbleId);
    }

    //设置右侧文本的内容
    public void setRightTvContent(@StringRes int contentId) {
        rightTv.setText(getContext().getResources().getString(contentId));
    }

    //让右侧显示图片
    public void showRightImage() {
        rightImg.setVisibility(VISIBLE);
    }

    public interface ToolBarListener{
        void onLeftImgClick();
        void onRightImgClick();
    }


}
