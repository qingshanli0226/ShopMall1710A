package com.example.shopmall.shopmall1710a.main.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseActivity;
import com.example.shopmall.framework.manager.ShopServiceManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.OrderInfoBean;
import com.example.shopmall.shopmall1710a.main.presenter.ConfirmResultPresenter;

import java.util.Map;

public class OrderInfoActivity extends BaseActivity<ConfirmResultPresenter, Boolean> implements View.OnClickListener {
    private com.example.shopmall.framework.base.view.CustomToolBar toolBar;
    private android.widget.Button btnZF;
    private android.widget.ProgressBar loadingBar;
    private String orderInfo;
    private String outTradeNo;
    private ConfirmResultPresenter confirmResultPresenter;

    @Override
    public int bindLayout() {
        return R.layout.activity_order_info;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {

        confirmResultPresenter = new ConfirmResultPresenter();
        confirmResultPresenter.attachView(this);
        toolBar = findViewById(R.id.toolBar);
        btnZF = findViewById(R.id.btnZF);
        loadingBar = findViewById(R.id.loadingBar);

        btnZF.setOnClickListener(this);

    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        orderInfo = intent.getStringExtra("orderInfo");
        outTradeNo = intent.getStringExtra("tradeNo");
    }

    @Override
    public void onHtttpReceived(int requestCode, Boolean data) {
        if (requestCode == 100) {
            if (data) {
                Toast.makeText(this, "服务端确认支付宝支付成功", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShopServiceManager.getInstance().getAppServieInterface().openMainActivity(OrderInfoActivity.this, 1);
                        finish();//加上finish更好，避免MainActivity被系统回收后，再次创建MainActivity。如果这种情况下，不调用finish的话，该Activity将不会destory
                    }
                });
            } else {
                Toast.makeText(this, "服务端确认支付宝支付失败", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void payByZFB() {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            confirmResultPresenter.addParams(outTradeNo, (String) msg.obj);
            confirmResultPresenter.postHttpDataWithJson(100);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnZF:
                Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
