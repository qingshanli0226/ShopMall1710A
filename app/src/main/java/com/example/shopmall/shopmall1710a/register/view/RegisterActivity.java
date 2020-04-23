package com.example.shopmall.shopmall1710a.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.net.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.register.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements IBaseView<String>,View.OnClickListener {

    private EditText nameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private ProgressBar progressBar;
    private RegisterPresenter registerPresenter;
    private String name;
    private String password;
    private int REGISTER_RESULT_CODE = 201;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    @Override
    public void onHtttpReceived(int requestCode, String data) {
        intent = getIntent();
        intent.putExtra("username",name);
        intent.putExtra("password",password);
        Toast.makeText(this, "注册成功 "+name, Toast.LENGTH_SHORT).show();
        setResult(REGISTER_RESULT_CODE,intent);
        finish();
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(this, "注册失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    private void initView() {
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        registerPresenter = new RegisterPresenter();
        registerPresenter.attachView(this);
    }

    @Override
    public void onClick(View v) {
        name = nameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        switch (v.getId()){
            case R.id.registerButton:
                Log.e("TAG", "onClick: "+name+password );
                registerPresenter.addParmas(name, password);
                registerPresenter.postHttpData(0);
                break;
        }
    }
}
