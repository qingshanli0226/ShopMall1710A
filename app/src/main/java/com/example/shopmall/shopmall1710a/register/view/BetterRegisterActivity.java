package com.example.shopmall.shopmall1710a.register.view;

import android.content.Intent;
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
import com.example.shopmall.shopmall1710a.register.mode.RegisterBean;
import com.example.shopmall.shopmall1710a.register.presenter.BetterRegisterPresenter;

public class BetterRegisterActivity extends AppCompatActivity implements IBaseView<RegisterBean>, View.OnClickListener {

    private EditText passwordEditText;
    private EditText nameEditText;
    private BetterRegisterPresenter registerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter=new BetterRegisterPresenter();
        registerPresenter.attachView(this);
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                login();
                break;
        }
    }

    private void login() {
        registerPresenter.addParmas(nameEditText.getText().toString(), passwordEditText.getText().toString());
        registerPresenter.postHttpData(0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.detachView();
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
    public void onHtttpReceived(int requestCode, RegisterBean data) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("name",nameEditText.getText().toString());
        intent.putExtra("password",passwordEditText.getText().toString());
        setResult(2,intent);
        finish();
    }

    @Override
    public void onHttpReceivedFailed(int requstCode, ErrorBean errorBean) {
        Toast.makeText(this, "注册失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
