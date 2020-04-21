package com.example.shopmall.shopmall1710a.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.mode.BetterLoginBean;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLoginPresenter;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLogoutPresenter;

public class BetterLoginActivity extends AppCompatActivity implements IBaseView<BetterLoginBean>, View.OnClickListener {

    private EditText passwordEditText;
    private EditText nameEditText;
    private BetterLoginPresenter loginPresenter;
    private BetterLogoutPresenter logoutPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new BetterLoginPresenter();
        logoutPresenter = new BetterLogoutPresenter();
        loginPresenter.attachView(this);
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.logoutButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                login();
                break;
            case R.id.logoutButton:
                //logout();
                break;
        }
    }

    private void logout() {
        logoutPresenter.postHttpData(0);
    }

    private void login() {
        loginPresenter.addParmas(nameEditText.getText().toString(), passwordEditText.getText().toString());
        loginPresenter.postHttpData(0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginPresenter.detachView();
    }

    @Override
    public void onHtttpReceived(int requstCode, BetterLoginBean data) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();


       // finish();//登录成功后，关闭当前登录页面
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
    public void onHttpReceivedFailed(int requstCode, ErrorBean errorBean) {
        Toast.makeText(this, "登录失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
