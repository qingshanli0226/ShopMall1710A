package com.example.shopmall.shopmall1710a.login.view;

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
import com.example.shopmall.shopmall1710a.home.view.HomeActivity;
import com.example.shopmall.shopmall1710a.login.mode.BetterLoginBean;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLoginPresenter;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLogoutPresenter;
import com.example.shopmall.shopmall1710a.register.view.BetterRegisterActivity;

public class BetterLoginActivity extends AppCompatActivity implements IBaseView<BetterLoginBean>, View.OnClickListener {

    private EditText passwordEditText;
    private EditText nameEditText;
    private BetterLoginPresenter loginPresenter;
    private BetterLogoutPresenter logoutPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

//        Intent intent = getIntent();
//        if (intent!=null){
//            nameEditText.setText(intent.getStringExtra("name"));
//            passwordEditText.setText(intent.getStringExtra("password"));
//        }

        loginPresenter = new BetterLoginPresenter();
        loginPresenter.attachView(this);

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
            case R.id.logoutButton:
                //logout();
                break;
            case R.id.registerButton:
                Intent intent = new Intent(this, BetterRegisterActivity.class);
                startActivityForResult(intent,1);
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
    public void onHttpReceived(int requstCode, BetterLoginBean data) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(BetterLoginActivity.this, HomeActivity.class));
        BetterLoginActivity.this.finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1&&resultCode==2){
            nameEditText.setText(data.getStringExtra("name"));
            passwordEditText.setText(data.getStringExtra("password"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
