package com.example.shopmall.shopmall1710a.login.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtill;
import com.example.shopmall.framework.base.bean.LoginBean;
import com.example.shopmall.framework.base.view.BaseActivity;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLoginPresenter;
import com.example.shopmall.shopmall1710a.main.view.activity.WelcomeActivity;

public class BetterLoginActivity extends BaseActivity<BetterLoginPresenter, LoginBean> implements View.OnClickListener {


    private android.widget.ImageView back;
    private android.widget.EditText name;
    private android.widget.EditText password;
    private android.widget.Button loginButton;
    private android.widget.TextView toregister;
    private android.widget.ProgressBar progressBar;

    private BetterLoginPresenter presenter;

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {


        presenter = new BetterLoginPresenter();
        presenter.attachView(this);

        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        toregister = findViewById(R.id.toregister);
        progressBar = findViewById(R.id.progressBar);

        loginButton.setOnClickListener(this);
        toregister.setOnClickListener(this);
    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onHtttpReceived(int requestCode, LoginBean data) {

        if (requestCode == 0) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);

            ShopUserManager.getInstance().saveToken(this, data.getToken());
            ShopUserManager.getInstance().saveUserLoginBeanAndNotify(data);

            finish();
        }

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(this, "登录失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        name.setText("");
        password.setText("");
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
            case R.id.loginButton:
                login();
                break;
            case R.id.toregister:
                Intent intent = new Intent(this, BetterRegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        String names = name.getText().toString();
        String pwd = password.getText().toString();
        presenter.addParmas(names, pwd);
        presenter.postHttpData(0);
    }
}
