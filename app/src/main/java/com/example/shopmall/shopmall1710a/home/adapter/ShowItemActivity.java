package com.example.shopmall.shopmall1710a.home.adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopmall.shopmall1710a.R;

public class ShowItemActivity extends AppCompatActivity {
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoPrice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        Glide.with(this).load(img).into(ivGoodInfoImage);
        tvGoodInfoName.setText(name);
        tvGoodInfoPrice.setText("￥"+price);

    }
}
