package com.example.shopmall.framework.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopmall.framework.R;

public class ToorBar extends LinearLayout implements View.OnClickListener {
    private TextView leftTv,rightTv,titleTv;
    private ImageView leftImg,rightImg;
    private ToolBarListener toolBarListener;
    View rootView;
    public ToorBar(Context context) {
        super(context);
    }

    public ToorBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        rootView=layoutInflater.inflate(R.layout.view_tool_bar,this);
        titleTv=rootView.findViewById(R.id.ToolBarTitle);
        leftImg=rootView.findViewById(R.id.left_image);
        leftTv=rootView.findViewById(R.id.left_tv);
        rightImg=rootView.findViewById(R.id.right_image);
        rightTv=rootView.findViewById(R.id.right_tv);
        leftImg.setOnClickListener(this);
        leftTv.setOnClickListener(this);
        rightTv.setOnClickListener(this);
        rightImg.setOnClickListener(this);
    }

    public ToorBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setToolBarListener(ToolBarListener toolBarListener){
        this.toolBarListener=toolBarListener;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.left_image) {
            if (toolBarListener != null) {
                toolBarListener.onLeftImgClick();
            }
        }else if (id == R.id.right_image) {
            if (toolBarListener != null) {
                toolBarListener.onRightImgClick();
            }
        }else if (id == R.id.left_tv) {
            if (toolBarListener != null) {
                toolBarListener.onLeftTvClick();
            }
        }else if (id == R.id.right_tv) {
            if (toolBarListener != null) {
                toolBarListener.onRightTvClick();
            }
        }
    }
    //参数必须传R.string.
    public void setTitle(@StringRes int titleId){
        titleTv.setText(getContext().getResources().getString(titleId));
    }
    //参数必须传递R.drawable.
    public void setLeftImg(@DrawableRes int drawableId){
        leftImg.setImageResource(drawableId);
    }
    public void setRightImg(@DrawableRes int drawableId){
        rightImg.setImageResource(drawableId);
    }
    public void setLeftTv(@StringRes int titleId){
        leftTv.setText(getContext().getResources().getString(titleId));
    }
    public void setRightTv(@StringRes int titleId){
        rightTv.setText(getContext().getResources().getString(titleId));
    }
    public void setTextSize(@IdRes int titleId,int size){
        TextView textView=rootView.findViewById(titleId);
        textView.setTextSize(size);
    }
    public void showRightImg(Boolean isFlag){
        if (isFlag){
            rightImg.setVisibility(View.VISIBLE);
        }else{
            rightImg.setVisibility(View.GONE);
        }
    }
    public void showRightTv(Boolean isFlag){
        Log.i("TAG", "showRightTv: "+1);
        if (isFlag){
            rightTv.setVisibility(View.VISIBLE);
        }else{
            rightTv.setVisibility(View.GONE);
        }

    }
    public void showToorBarTitle(Boolean isFlag){
        if (isFlag){
            titleTv.setVisibility(View.VISIBLE);
        }else{
            titleTv.setVisibility(View.GONE);
        }

    }
    public void showLeftTv(Boolean isFlag){

        if (isFlag){
            leftTv.setVisibility(View.VISIBLE);
        }else{
            leftTv.setVisibility(View.GONE);
        }

    }
    public void showLeftImg(Boolean isFlag){
        if (isFlag){
            leftImg.setVisibility(View.VISIBLE);
        }else{
            leftImg.setVisibility(View.GONE);
        }

    }

    public void showAll(Boolean isFlag){
        if (isFlag){
            showRightTv(true);
            showRightImg(true);
            showLeftImg(true);
            showLeftTv(true);
            showToorBarTitle(true);
        }else{
            showRightTv(false);
            showRightImg(false);
            showLeftImg(false);
            showLeftTv(false);
            showToorBarTitle(false);

        }
    }
    public interface ToolBarListener{
        void onLeftImgClick();
        void onRightImgClick();
        void onLeftTvClick();
        void onRightTvClick();
    }
}
