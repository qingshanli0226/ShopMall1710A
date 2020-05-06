package com.example.shopmall.buy.shopcar.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.shopcar.presenter.ConfirmResultPresenter;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.ShopServiceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderInfoActivity extends BaseActivity<Boolean> implements View.OnClickListener {
    private String orderInfo;
    private String outTradeNo;
    private ConfirmResultPresenter confirmResultPresenter;

    @Override
    protected void initData() {
       Intent intent = getIntent();
       orderInfo = intent.getStringExtra("orderInfo");
       outTradeNo = intent.getStringExtra("tradeNo");
    }

    @Override
    protected List<IPresenter<Boolean>> getPresenter() {
        confirmResultPresenter = new ConfirmResultPresenter();
        List<IPresenter<Boolean>> iPresenterList = new ArrayList<>();
        iPresenterList.add(confirmResultPresenter);
        return iPresenterList;
    }

    @Override
    protected void initView() {
        findViewById(R.id.btnZF).setOnClickListener(this);
        //配置支付宝测试沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
        toolBar.showRightTv(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnZF) {
            //支付宝的API需要什么参数,需要字符串
            payByZFB();
        }
    }

    private void payByZFB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(OrderInfoActivity.this);
                Map<String,String> result = payTask.payV2(orderInfo, true);
                if(result.get("resultStatus").equals("9000")) {//客户端接到支付宝返回值是9000的话，代表着支付宝支付成功,但是需要和服务端再次确认
                    Message message = Message.obtain(handler, 1, result.get("result"));
                    message.sendToTarget();
                }
            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            confirmResultPresenter.addParams(outTradeNo, (String) msg.obj);
            confirmResultPresenter.postHttpDataWithJson(100);
        }
    };

    @Override
    public void onHtttpReceived(int requestCode, Boolean success) {
        if (requestCode == 100) {
            if (success) {
                Toast.makeText(this, "服务端确认支付宝支付成功",Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShopServiceManager.getInstance().getAppServieInterface().openMainActivity(OrderInfoActivity.this,1);
                        finish();//加上finish更好，避免MainActivity被系统回收后，再次创建MainActivity。如果这种情况下，不调用finish的话，该Activity将不会destory
                    }
                });
            } else {
                Toast.makeText(this, "服务端确认支付宝支付失败",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public static void launch(Activity activity, String orderInfo, String tradeNo) {
        Intent intent = new Intent();
        intent.putExtra("orderInfo", orderInfo);
        intent.putExtra("tradeNo", tradeNo);
        intent.setClass(activity,OrderInfoActivity.class);
        activity.startActivity(intent);
    }
}
