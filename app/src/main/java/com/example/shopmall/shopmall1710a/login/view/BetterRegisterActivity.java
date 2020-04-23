package com.example.shopmall.shopmall1710a.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.presenter.BetterRegisterPresenter;

public class BetterRegisterActivity extends AppCompatActivity implements IBaseView<String>, View.OnClickListener {

    private ImageView back;
    private EditText name;
    private EditText password;
    private Button registerButton;
    private TextView tologin;
    private BetterRegisterPresenter registPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.backs);
        name = (EditText) findViewById(R.id.names);
        password = (EditText) findViewById(R.id.passwords);
        registerButton = (Button) findViewById(R.id.registerButton);
        tologin = (TextView) findViewById(R.id.tologin);
        registPresenter = new BetterRegisterPresenter();
        registPresenter.attachView(this);
        registerButton.setOnClickListener(this);


    }

    @Override
    public void onHtttpReceived(int requestCode, String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
//        Toast.makeText(this, "注册失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        name.setText("");
        password.setText("");
    }

    @Override
    public void showLoading() {
        findViewById(R.id.progressBars).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.progressBars).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerButton:
                register();
                break;
        }
    }


    private void register() {
        registPresenter.addParmas(name.getText().toString(), password.getText().toString());
        registPresenter.postHttpData(0);
        Intent intent = new Intent();
        intent.putExtra("username", name.getText().toString());
        intent.putExtra("password", password.getText().toString());
        setResult(102, intent);
        finish();
    }
}
