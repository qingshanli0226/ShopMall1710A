package com.example.shopmall.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.shopmall.framework.R;

import java.util.ArrayList;
import java.util.List;

public class BottomBar extends LinearLayout implements RadioGroup.OnCheckedChangeListener ,View.OnClickListener{
    private View rootView;
    private RadioGroup radioGroup;
    private RadioButton radioButton01;
    private RadioButton radioButton02;
    private RadioButton radioButton03;
    private RadioButton radioButton04;
    private RadioButton radioButton05;
    private List<ButtonInfo> list = new ArrayList<>();
    private List<RadioButton> radioButtons = new ArrayList<>();
    private ViewPager pager;
    private bottomBarListener bottomBarListener;
    public BottomBar(Context context) {
        super(context);
        init(context,null);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.bottombar, this);
        radioGroup = rootView.findViewById(R.id.radioGroup);
        radioButton01 = rootView.findViewById(R.id.btn01);
        radioButton02 = rootView.findViewById(R.id.btn02);
        radioButton03 = rootView.findViewById(R.id.btn03);
        radioButton04 = rootView.findViewById(R.id.btn04);
        radioButton05 = rootView.findViewById(R.id.btn05);
        radioButtons.add(radioButton01);
        radioButtons.add(radioButton02);
        radioButtons.add(radioButton03);
        radioButtons.add(radioButton04);
        radioButtons.add(radioButton05);
        radioButton02.setOnClickListener(this);





    }

    public void setOnCheckedChangeListener(){
        radioGroup.setOnCheckedChangeListener(this);

    }

    public void setBackground(@DrawableRes int background) {
        radioGroup.setBackgroundResource(background);
    }
    public void setBtnData(List<ButtonInfo> list,boolean flag) {
        this.list = list;
        if (flag)
            setData(0);
        else
            setData(-1);
    }
    public void setData(int num) {
        Drawable drawable = null;
        for (int i = 0; i < list.size(); i++) {
            if (num==i){
                if (list.get(i).getSelectPic()!=0){
                    drawable = getResources().getDrawable(list.get(i).getSelectPic());

                }else {
                    drawable = getResources().getDrawable(list.get(i).getUnSelectPic());
                }
                drawable.setBounds(0, 0, 80,80);
                radioButtons.get(i).setCompoundDrawables(null,drawable,null,null);
                radioButtons.get(i).setPadding(20,20,20,20);
                radioButtons.get(i).setText(list.get(i).getTitle());
                radioButtons.get(i).setTextColor(Color.RED);
            }else{
                if (list.get(i).getUnSelectPic()!=0){
                    drawable = getResources().getDrawable(list.get(i).getUnSelectPic());
                    drawable.setBounds(0, 0, 80,80);
                    radioButtons.get(i).setCompoundDrawables(null,drawable,null,null);
                    radioButtons.get(i).setPadding(20,20,20,20);
                }
                radioButtons.get(i).setText(list.get(i).getTitle());
                radioButtons.get(i).setTextColor(Color.BLACK);

            }

        }
        for (int i = list.size(); i < radioButtons.size(); i++){
            radioButtons.get(i).setVisibility(GONE);
        }
    }
    public void setCheckInt(int num){
        for (int i = 0; i < radioButtons.size(); i++) {
            radioButtons.get(num).setChecked(true);
        }
    }
    public void withPager(ViewPager pager){
        this.pager = pager;
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setCheckInt(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    public void setBottomBarListener(bottomBarListener bottomBarListener){
        this.bottomBarListener=bottomBarListener;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn02) {
            if (bottomBarListener!=null){
                bottomBarListener.onSecondClick();
            }
        }
    }

    public interface bottomBarListener{
        void onSecondClick();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.getCheckedRadioButtonId() == R.id.btn01) {
            setData(0);
            pager.setCurrentItem(0);
        }else if (group.getCheckedRadioButtonId() == R.id.btn02) {
            setData(1);
            pager.setCurrentItem(1);
        }else if (group.getCheckedRadioButtonId() == R.id.btn03) {
            setData(2);
            pager.setCurrentItem(2);
        }else if (group.getCheckedRadioButtonId() == R.id.btn04) {
            setData(3);
            pager.setCurrentItem(3);
        }else if (group.getCheckedRadioButtonId() == R.id.btn05) {
            setData(4);
            pager.setCurrentItem(4);
        }
    }
}
