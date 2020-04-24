package com.example.shopmall.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shopmall.framework.R;

public class MyRadioGroup extends LinearLayout {
    private RadioGroupListener radioGroupListener;
    private RadioGroup radioGroup;
    public RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    if (radioGroupListener != null){
                        radioGroupListener.onRadioButtonOneClick();
                    }
                }
                else if (checkedId == R.id.rb2) {
                    if (radioGroupListener != null) {
                        radioGroupListener.onRadioButtonTwoClick();
                    }
                }
                else if (checkedId == R.id.rb3) {
                    if (radioGroupListener != null) {
                        radioGroupListener.onRadioButtonThreeClick();
                    }
                }
                else if (checkedId == R.id.rb4) {
                    if (radioGroupListener != null) {
                        radioGroupListener.onRadioButtonFoneClick();
                    }
                }
                else if (checkedId == R.id.rb5) {
                    if (radioGroupListener != null) {
                        radioGroupListener.onRadioButtonFiveClick();
                    }
                }
            }
        });
    }


    //设置toolbar的点击事件
    public void setRadioGroupListener(RadioGroupListener listener) {
        this.radioGroupListener = listener;
    }

    public interface RadioGroupListener {
        void onRadioButtonOneClick();
        void onRadioButtonTwoClick();
        void onRadioButtonThreeClick();
        void onRadioButtonFoneClick();
        void onRadioButtonFiveClick();
    }

}
