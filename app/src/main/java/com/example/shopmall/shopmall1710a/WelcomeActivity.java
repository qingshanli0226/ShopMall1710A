package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseActivity;
import com.example.shopmall.shopmall1710a.main.view.MainAct;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private android.widget.ImageView actWelImv;
    private int num = 0;

    @Override
    public int bindLayout() {
        return R.layout.activity_wel;
    }

    @Override
    public void initView() {

        actWelImv = findViewById(R.id.act_wel_imv);

        Glide.with(this).load("http://49.233.93.155:8080/atguigu/gif/welcome.gif")
                .into(actWelImv);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (num > 0) {
                    Intent intent = new Intent(WelcomeActivity.this, MainAct.class);
                    startActivity(intent);
                    timer.cancel();
                }
                num++;
            }
        }, 0, 3000);


    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {

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
}
