package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("欢迎页");
        textView.setGravity(Gravity.CENTER);
        setContentView(textView);
        CacheManager.getInstance().init(ShopMallApplication.instance);
        ShopUserManager.getInstance().init(ShopMallApplication.instance);
        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.sendEmptyMessageDelayed(0, 1000);

    }
}
