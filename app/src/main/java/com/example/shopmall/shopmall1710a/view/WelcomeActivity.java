package com.example.shopmall.shopmall1710a.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shopmall.shopmall1710a.R;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView welcomePic;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomePic = (ImageView) findViewById(R.id.welcome_pic);
        Glide.with(this).load("http://49.233.93.155:8080/atguigu/gif/welcome.gif").into(welcomePic);
        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.sendEmptyMessageDelayed(0,3000);
    }
}
