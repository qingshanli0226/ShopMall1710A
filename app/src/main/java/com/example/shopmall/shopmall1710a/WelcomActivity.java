package com.example.shopmall.shopmall1710a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shopmall.shopmall1710a.login.view.BetterLoginActivity;

public class WelcomActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private ImageView welcomePic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        Glide.with(this).load("http://49.233.93.155:8080/atguigu/gif/welcome.gif").into(welcomePic);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomActivity.this, BetterLoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
        initView();
    }

    private void initView() {
        welcomePic = findViewById(R.id.welcome_pic);
    }
}
