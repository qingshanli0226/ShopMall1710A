package com.example.shopmall.framework.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import com.example.shopmall.framework.R;

public class CustomToolBar extends LinearLayout implements View.OnClickListener {
    public TextView tvLeft, tvRight, tvTitle;
    public ImageView imvLeft, imvRight;
    private ToolBarListener toolBarListener;

    public CustomToolBar(Context context) {
        super(context);
        init(context, null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.layout_customtoolbar, this);
        tvTitle = rootView.findViewById(R.id.toolBarTitle);
        imvLeft = rootView.findViewById(R.id.toolBarImvLeft);
        tvLeft = rootView.findViewById(R.id.toolBarTvLeft);
        imvRight = rootView.findViewById(R.id.toolBarImvRight);
        tvRight = rootView.findViewById(R.id.toolBarTvRight);

        tvTitle.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        imvLeft.setOnClickListener(this);
        imvRight.setOnClickListener(this);
    }

    public void setOnToolBarListener(ToolBarListener listener) {
        this.toolBarListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolBarTitle) {
            if (toolBarListener != null) {
                toolBarListener.onTitleTv();
            }
        }
        if (view.getId() == R.id.toolBarTvLeft) {
            if (toolBarListener != null) {
                toolBarListener.onLeftTv();
            }
        }
        if (view.getId() == R.id.toolBarTvRight) {
            if (toolBarListener != null) {
                toolBarListener.onRightTv();
            }
        }
        if (view.getId() == R.id.toolBarImvLeft) {
            if (toolBarListener != null) {
                toolBarListener.onLeftImv();
            }
        }
        if (view.getId() == R.id.toolBarImvRight) {
            if (toolBarListener != null) {
                toolBarListener.onRightImv();
            }
        }


    }

    //注解表示必须传递图片id
    public void setLeftImgDrawable(@DrawableRes int id) {
        imvLeft.setImageResource(id);
    }

    //注解表示必须传递图片id
    public void setRightImgDrawable(@DrawableRes int id) {
        imvRight.setImageResource(id);
    }

    //注解表示必须传递R.String.……
    public void setRightTv(@StringRes int id) {
        String titleStr = getContext().getString(id);
        tvRight.setText(titleStr);
    }

    //注解表示必须传递R.String.……
    public void setLeftTv(@StringRes int id) {
        String titleStr = getContext().getString(id);
        tvLeft.setText(titleStr);
    }

    //注解表示必须传递R.String.……
    public void setToolTitle(@StringRes int titleId) {
        String titleStr = getContext().getString(titleId);
        tvTitle.setText(titleStr);
    }


    public interface ToolBarListener {
        void onLeftTv();

        void onRightTv();

        void onLeftImv();

        void onRightImv();

        void onTitleTv();
    }

}
