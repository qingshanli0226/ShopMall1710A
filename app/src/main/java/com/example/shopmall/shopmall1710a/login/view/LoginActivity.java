package com.example.shopmall.shopmall1710a.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.IBaseView;
import com.example.shopmall.shopmall1710a.MainActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.model.LoginBean;
import com.example.shopmall.shopmall1710a.login.presenter.LoginPresenter;
import com.example.shopmall.shopmall1710a.regist.RegistActivity;


public class LoginActivity extends AppCompatActivity implements IBaseView<LoginBean>, View.OnClickListener {
    private EditText username;
    private EditText password;
    private Button loginBt;
    private LoginPresenter loginPresenter;
    /**
     * 注册
     */
    private Button mRegistBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginBt = (Button) findViewById(R.id.login_bt);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        loginBt.setOnClickListener(this);

    }

    @Override
    public void onHtttpReceived(int requestCode, LoginBean data) {

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(this, "登录失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                login();
                break;
            case R.id.regist_bt:
                regist();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 102 && data != null) {
            String username1 = data.getStringExtra("username");
            String password1 = data.getStringExtra("password");
            username.setText(username1);
            password.setText(password1);
        }
    }

    private void regist() {
        Intent intent = new Intent(this, RegistActivity.class);
        startActivityForResult(intent, 101);
    }

    private void login() {
        loginPresenter.addParmas(username.getText().toString(), password.getText().toString());
        loginPresenter.postHttpData(0);
    }

    private void initView() {
        mRegistBt = (Button) findViewById(R.id.regist_bt);
        mRegistBt.setOnClickListener(this);
    }
}
