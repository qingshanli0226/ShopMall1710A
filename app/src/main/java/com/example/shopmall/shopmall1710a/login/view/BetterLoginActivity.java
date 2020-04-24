package com.example.shopmall.shopmall1710a.login.view;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.MainActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLoginPresenter;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLogoutPresenter;
import com.example.shopmall.shopmall1710a.register.view.BetterRegisterActivity;

import java.util.ArrayList;
import java.util.List;


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
                Intent intent = new Intent(this, BetterRegisterActivity.class);
                startActivity(intent);
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onHttpReceivedFailed(int requstCode, ErrorBean errorBean) {
        Toast.makeText(this, "登录失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLeftImgClick() {
        super.onLeftImgClick();
    }

    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        Toast.makeText(this, "dd", Toast.LENGTH_SHORT).show();
    }
}
