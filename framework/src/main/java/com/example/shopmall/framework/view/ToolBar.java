package com.example.shopmall.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
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
    private TextView  leftTv;
    private TextView  rightTv;
    private TextView  titleTv;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView messageTv;

    private int toolBarTitleId;
    private int leftImgSrcId;
    private int rightImgSrcId;
    private boolean isRightDisplay;
    private int backgroundColor;
    private boolean isShowTitle;
    private LinearLayout   rightLayout;
    private LinearLayout   leftLayout;
    private LinearLayout rootLayout;
    private int rightTextId;

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
        initViewAttrs(context, attrs);
        initView(context);
        displayView();
    }

    //生成布局文件
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.view_tool_bar, this);
        leftTv = rootView.findViewById(R.id.leftTv);
        rightTv = rootView.findViewById(R.id.rightTv);
        leftImg = rootView.findViewById(R.id.leftImag);
        rightImg =rootView.findViewById(R.id.rightImag);
        titleTv = rootView.findViewById(R.id.toolBarTitle);
        leftLayout = rootView.findViewById(R.id.leftLayout);
        rightLayout = rootView.findViewById(R.id.rightLayout);
        rootLayout = rootView.findViewById(R.id.toolBarRoot);
        messageTv = rootView.findViewById(R.id.message);

        leftLayout.setOnClickListener(this);
        rightLayout.setOnClickListener(this);
    }

    //获取属性值
    private void initViewAttrs(Context context, AttributeSet attrs) {
        //获取自定义view的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MYToolBar);
        //获取的标题
        toolBarTitleId = typedArray.getResourceId(R.styleable.MYToolBar_toolbarTile, -1);
        //获取左侧图片资源的id
        leftImgSrcId = typedArray.getResourceId(R.styleable.MYToolBar_toolBarLeftSrc, -1);
        rightImgSrcId = typedArray.getResourceId(R.styleable.MYToolBar_toolBarRightSrc, -1);
        //右侧内容是否显示
        isRightDisplay = typedArray.getBoolean(R.styleable.MYToolBar_toolBarRightDisplay, true);
        //获取toolbar背景色
        backgroundColor = typedArray.getColor(R.styleable.MYToolBar_toolbarBackgroundColor, Color.WHITE);
        //是否显示标题
        isShowTitle = typedArray.getBoolean(R.styleable.MYToolBar_isShowTitle, true);
        rightTextId = typedArray.getResourceId(R.styleable.MYToolBar_rightText,-1);
    }

    private void displayView() {
        //是否显示右边内容
        if (!isRightDisplay) {
            rightLayout.setVisibility(GONE);
        } else {
            rightLayout.setVisibility(VISIBLE);
        }

        //是否显示标题
        if (isShowTitle) {
            titleTv.setVisibility(VISIBLE);
        } else {
            titleTv.setVisibility(GONE);
        }

        //设置toolbar的背景色
        rootLayout.setBackgroundColor(backgroundColor);
        //设置标题
        if (toolBarTitleId !=-1) {
            setTitle(toolBarTitleId);
        }
        //设置左侧图片
        if (leftImgSrcId !=-1) {
            leftImg.setImageResource(leftImgSrcId);
        }

        //设置右侧图片
        if (rightImgSrcId !=-1) {
            rightImg.setImageResource(rightImgSrcId);
        }

        if (rightTextId!=-1) {
            rightTv.setText(getContext().getResources().getString(rightTextId));
        }
    }


    //设置toolbar的点击事件
    public void setToolBarClickListener(ToolBarListener  listener) {
        this.toolBarListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.leftLayout) {
            if(toolBarListener != null) {
                toolBarListener.onLeftClick();
            }
        }else if (v.getId() == R.id.rightLayout) {
            if (toolBarListener != null) {
                toolBarListener.onRightClick();
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

    //通过字符串刷新右侧数据
    public void setRightTvContent(String content) {
        rightTv.setText(content);
    }

    //让右侧显示图片
    public void showRightImage() {
        rightImg.setVisibility(VISIBLE);
    }

    public void showRightTv(boolean isShow) {
        if (isShow) {
            rightTv.setVisibility(VISIBLE);
        } else {
            rightTv.setVisibility(GONE);
        }
    }

    public void showLeft(boolean isShow) {
        if (isShow) {
            leftLayout.setVisibility(VISIBLE);
        } else {
            leftLayout.setVisibility(GONE);
        }
    }

  //是否显示title
    public void showTitle(boolean isShowTitle) {
        if (isShowTitle) {
            titleTv.setVisibility(VISIBLE);
        } else {
            titleTv.setVisibility(GONE);
        }
    }

    public void showRightMessage(boolean isShow) {
        messageTv.setVisibility(isShow?VISIBLE:GONE);
    }

    public void setMessageText(String text) {
        messageTv.setText(text);
    }

    public void setMessageListener(OnClickListener onClickListener) {
        messageTv.setOnClickListener(onClickListener);
    }

    public interface ToolBarListener{
        void onLeftClick();
        void onRightClick();
    }
}
