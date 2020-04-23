package com.example.shopmall.shopmall1710a.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.mode.LoginBean;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLoginPresenter;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLogoutPresenter;

public class BetterLoginActivity extends AppCompatActivity implements IBaseView<LoginBean>, View.OnClickListener {

    private EditText passwordEditText;
    private EditText nameEditText;
    private BetterLoginPresenter loginPresenter;
    private BetterLogoutPresenter logoutPresenter;

    private TextView toLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new BetterLoginPresenter();
        logoutPresenter = new BetterLogoutPresenter();
        loginPresenter.attachView(this);
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        toLogin = findViewById(R.id.toregister);

        findViewById(R.id.loginButton).setOnClickListener(this);
        toLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                login();
                break;
            case R.id.toregister:
                toregister();
                break;
        }
    }

    private void toregister() {
        Intent intent = new Intent(this, BetterRegisterActivity.class);
        startActivityForResult(intent, 101);
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
    public void showLoading() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onHtttpReceived(int requestCode, LoginBean data) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        this.finish();
    }

    @Override
    public void onHttpReceivedFailed(int requstCode, ErrorBean errorBean) {
//        Toast.makeText(this, "登录失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        this.finish();
    }
}
