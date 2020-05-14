package com.example.shopmall.point;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.bean.StepBean;
import com.example.shopmall.framework.manager.CacheManager;

import java.util.List;

public class PointActivity extends BaseActivity<Object> implements CacheManager.IStepChangeListener {
    private TextView stepValueTv,pointValueTv;

    @Override
    protected void initData() {
        CacheManager.getInstance().registeStepChangeListener(this);
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        stepValueTv = findViewById(R.id.stepValue);
        pointValueTv = findViewById(R.id.pointValue);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_point;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onStepChanged(StepBean stepBean) {
        stepValueTv.setText(String.valueOf(stepBean.getCurrentStep()));
    }

    @Override
    public void onPointChanged(int point) {
        pointValueTv.setText(String.valueOf(point));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, PointActivity.class);
        activity.startActivity(intent);
    }
}
