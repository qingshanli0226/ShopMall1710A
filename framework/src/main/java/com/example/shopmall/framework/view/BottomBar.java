package com.example.shopmall.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import com.example.shopmall.framework.R;

public class BottomBar extends LinearLayout {
    private IBottomBarTabCheckedListener iBottomBarChangedListener;
    public BottomBar(Context context) {
        super(context);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_bottombar, this);

        RadioGroup radioGroup = findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = -1;
                 if (checkedId == R.id.home) {
                     index = 0;
                 } else if (checkedId == R.id.type) {
                     index = 1;
                 } else if (checkedId == R.id.mine) {
                     index = 2;
                 }
                 if (iBottomBarChangedListener!=null) {
                     iBottomBarChangedListener.onTabChecked(index);
                 }
            }
        });
    }

    public void setTabCheckedListener(IBottomBarTabCheckedListener iBottomBarChangedListener) {
        this.iBottomBarChangedListener = iBottomBarChangedListener;
    }

    public interface IBottomBarTabCheckedListener {
        void onTabChecked(int index);
    }
}
