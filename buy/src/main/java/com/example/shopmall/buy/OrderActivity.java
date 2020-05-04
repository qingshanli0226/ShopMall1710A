package com.example.shopmall.buy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.LogUtils;
import com.example.shopmall.buy.entity.OrderEntity;
import com.example.shopmall.buy.presenter.OrderPresenter;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderActivity extends BaseActivity implements View.OnClickListener {
    private List<ShortCarEntity.ResultBean> list;
    private String price;
    private TextView orderProce;
    private OrderPresenter orderPresenter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){

            }
        }
    };
    @Override
    public int bindLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        list = (List<ShortCarEntity.ResultBean>) bundle.getSerializable("list");
        price = (String) bundle.get("price");

        orderProce = findViewById(R.id.orderProce);
        orderProce.setText(price);
        findViewById(R.id.orderBtn).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public List<IPresenter> getPresenter() {
        List<IPresenter> list = new ArrayList<>();
        orderPresenter = new OrderPresenter(this);
        list.add(orderPresenter);
        return list;
    }

    @Override
    public void onHttpReceived(int requestCode, final Object data) {

        if (requestCode == 100) { // 订单提交成功
            final OrderEntity info = (OrderEntity) data;
            Log.i("boss", "onHttpReceived: 订单提交成功!"+info);
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(OrderActivity.this);
                    Map <String,String> result = alipay.payV2(info.toString(),true);

                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.orderBtn) { // 提交订单
            orderPresenter.addParames(price,list);
            orderPresenter.postHttpDataWithJson(100);
        }
    }
}
