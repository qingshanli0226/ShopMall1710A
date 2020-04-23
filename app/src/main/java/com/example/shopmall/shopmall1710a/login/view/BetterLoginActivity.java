package com.example.shopmall.shopmall1710a.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.BasePresenter;
import com.example.shopmall.framework.base.IBaseView;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.mode.BetterLoginBean;
import com.example.shopmall.shopmall1710a.login.mode.LoginBean;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLoginPresenter;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLogoutPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class BetterLoginActivity extends BaseActivity<Object> implements View.OnClickListener {

    private EditText passwordEditText;
    private EditText nameEditText;
    private BetterLoginPresenter loginPresenter;
    private BetterLogoutPresenter logoutPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.logoutButton).setOnClickListener(this);
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        loginPresenter = new BetterLoginPresenter();
        logoutPresenter = new BetterLogoutPresenter();
        ArrayList<IPresenter<Object>> arrayList = new ArrayList<>();
        arrayList.add(loginPresenter);
        arrayList.add(logoutPresenter);

        return arrayList;
    }

    @Override
    protected void initData() {

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
    protected void destroy() {
    }

    @Override
    public void onHtttpReceived(int requstCode, Object data) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public void onHttpReceivedFailed(int requstCode, ErrorBean errorBean) {
        Toast.makeText(this, "登录失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
