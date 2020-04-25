package com.example.shopmall.framework.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewpager.widget.ViewPager;
import com.example.shopmall.framework.R;
import com.example.shopmall.framework.customView.bean.BottomBean;


import java.util.ArrayList;
import java.util.List;

public class CustomBottomBar extends LinearLayout implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;

    private List<RadioButton> radioButtons = new ArrayList<>();
    private List<Drawable> lists = new ArrayList<>();
    private List<Drawable> listp = new ArrayList<>();
    // 传入 viewPage 和 相应 bean类
    private ViewPager viewPager;
    private List<BottomBean> bottomBeans;
    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setBottomBeans(List<BottomBean> bottomBeans) {
        this.bottomBeans = bottomBeans;
        start();
    }

    public CustomBottomBar(Context context) {
        super(context);
        initView(context);
    }
    public CustomBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public CustomBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_buttom_bar,this);
        radioGroup  = findViewById(R.id.customBottomBar);
        rb1 = findViewById(R.id.rb1);
        radioButtons.add(rb1);
        rb2 = findViewById(R.id.rb2);
        radioButtons.add(rb2);
        rb3 = findViewById(R.id.rb3);
        radioButtons.add(rb3);
        rb4 = findViewById(R.id.rb4);
        radioButtons.add(rb4);
        rb5 = findViewById(R.id.rb5);
        radioButtons.add(rb5);

    }

    private void start() {
        radioGroup.setOnCheckedChangeListener(this);
        for (int i = 0; i < bottomBeans.size(); i++) {
            Drawable drawable = getResources().getDrawable(bottomBeans.get(i).getPitch());
            drawable.setBounds(0,0,80,80);
            listp.add(drawable);
            Drawable drawable1 = getResources().getDrawable(bottomBeans.get(i).getSelected());
            drawable1.setBounds(0,0,80,80);
            lists.add(drawable1);
        }
        for (int i = 0; i < radioButtons.size(); i++) {
            radioButtons.get(i).setText(bottomBeans.get(i).getTitle());
            radioButtons.get(i).setCompoundDrawables(null,listp.get(i),null,null);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.rb1) {
            viewPager.setCurrentItem(0);
            setData(0);
        }else if (radioGroup.getCheckedRadioButtonId() == R.id.rb2){
            viewPager.setCurrentItem(1);
            setData(1);
        }else if (radioGroup.getCheckedRadioButtonId() == R.id.rb3){
            viewPager.setCurrentItem(2);
            setData(2);
        }else if (radioGroup.getCheckedRadioButtonId() == R.id.rb4){
            viewPager.setCurrentItem(3);
            setData(3);
        }else if (radioGroup.getCheckedRadioButtonId() == R.id.rb5){
            viewPager.setCurrentItem(4);
            setData(4);
        }
    }

    private void setData(int i) {
        for (int i1 = 0; i1 < radioButtons.size(); i1++) {
            if (i == i1){
                radioButtons.get(i1).setCompoundDrawables(null,lists.get(i1),null,null);
            }else {
                radioButtons.get(i1).setCompoundDrawables(null,listp.get(i1),null,null);
            }
        }
    }


}
