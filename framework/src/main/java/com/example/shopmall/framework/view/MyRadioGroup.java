package com.example.shopmall.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shopmall.framework.R;

import java.util.ArrayList;
import java.util.List;

public class MyRadioGroup extends LinearLayout implements RadioGroup.OnCheckedChangeListener  {
    public RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private List<RadioButtonBean> radioButtonBeanList = new ArrayList<>();
    private List<RadioButton> radioButtonList = new ArrayList<>();
    private ViewPager viewPager;

    private String buttonName1Id;
    private String buttonName2Id;
    private String buttonName3Id;
    private String buttonName4Id;
    private String buttonName5Id;
    private boolean isUpdateText;
    public MyRadioGroup(Context context) {
        super(context);
        init(context,null);
    }

    public MyRadioGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyRadioGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initViewAttrs(context, attrs);
        initView(context);
        displayView();
    }

    private void displayView() {
        if (isUpdateText){
            rb1.setText(buttonName1Id);
            rb2.setText(buttonName2Id);
            rb3.setText(buttonName3Id);
            rb4.setText(buttonName4Id);
            rb5.setText(buttonName5Id);
        }
    }

    private void initViewAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRadioGroup);
        buttonName1Id = typedArray.getString(R.styleable.MyRadioGroup_buttonName1);
        buttonName2Id = typedArray.getString(R.styleable.MyRadioGroup_buttonName2);
        buttonName3Id = typedArray.getString(R.styleable.MyRadioGroup_buttonName3);
        buttonName4Id = typedArray.getString(R.styleable.MyRadioGroup_buttonName4);
        buttonName5Id = typedArray.getString(R.styleable.MyRadioGroup_buttonName5);
        isUpdateText = typedArray.getBoolean(R.styleable.MyRadioGroup_isUpdateText, false);

    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_radiogroup, this);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rb5 = (RadioButton) findViewById(R.id.rb5);
        radioButtonList.add(rb1);
        radioButtonList.add(rb2);
        radioButtonList.add(rb3);
        radioButtonList.add(rb4);
        radioButtonList.add(rb5);
    }


    //设置MyRadioGroup的点击事件
    public void setOnCheckedChangeListener(){
        radioGroup.setOnCheckedChangeListener(this);
    }
    //设置RadioButton数据
    public void setRadioButtonList(List<RadioButtonBean> list,boolean flag){
        this.radioButtonBeanList = list;
        if (flag){
            setData(0);
        }else {
            setData(-1);
        }
    }

    private void setData(int num) {
        Drawable drawable = null;
        for (int i = 0; i < radioButtonBeanList.size(); i++) {
            if (num==i){
                if (radioButtonBeanList.get(i).getSelectImg()!=0){
                    drawable = getResources().getDrawable(radioButtonBeanList.get(i).getUnSelectImg());
                    drawable.setBounds(0, 0, 50,50);
                    radioButtonList.get(i).setCompoundDrawables(null,drawable,null,null);
//                    radioButtonList.get(i).setPadding(20,20,20,20);
                }
                radioButtonList.get(i).setText(radioButtonBeanList.get(i).getTitle());
                radioButtonList.get(i).setTextColor(Color.BLACK);
            }else{
                if (radioButtonBeanList.get(i).getUnSelectImg()!=0){
                    drawable = getResources().getDrawable(radioButtonBeanList.get(i).getSelectImg());
                    drawable.setBounds(0, 0, 50,50);
                    radioButtonList.get(i).setCompoundDrawables(null,drawable,null,null);
//                    radioButtonList.get(i).setPadding(20,20,20,20);
                }
                radioButtonList.get(i).setText(radioButtonBeanList.get(i).getTitle());
                radioButtonList.get(i).setTextColor(Color.GRAY);
            }
        }
    }

    public void withPager(ViewPager viewPager){
        this.viewPager = viewPager;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb1||checkedId == 0) {
            setData(0);
            viewPager.setCurrentItem(0);
        }
        else if (checkedId == R.id.rb2) {
            setData(1);
            viewPager.setCurrentItem(1);
        }
        else if (checkedId == R.id.rb3) {
            setData(2);
            viewPager.setCurrentItem(2);
        }
        else if (checkedId == R.id.rb4) {
            setData(3);
            viewPager.setCurrentItem(3);
        }
        else if (checkedId == R.id.rb5) {
            setData(4);
            viewPager.setCurrentItem(4);
        }
    }
}
