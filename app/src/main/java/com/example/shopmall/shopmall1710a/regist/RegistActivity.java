package com.example.shopmall.shopmall1710a.regist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.IBaseView;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.regist.presenter.RegistPresenter;


public class RegistActivity extends AppCompatActivity implements IBaseView<String>, View.OnClickListener {
    private EditText username;
    private EditText password;
    private Button registbt;
    private RegistPresenter registPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        registbt = (Button) findViewById(R.id.regist_bt);
        registPresenter=new RegistPresenter();
        registPresenter.attachView(this);
        registbt.setOnClickListener(this);

    }

    @Override
    public void onHtttpReceived(int requestCode, String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {
        Toast.makeText(this, "注册失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        username.setText("");
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
            case R.id.regist_bt:
                regist();
                break;
        }
    }

    private void regist() {
        registPresenter.addParmas(username.getText().toString(),password.getText().toString());
        registPresenter.postHttpData(0);
        Intent intent = new Intent();
        intent.putExtra("username",username.getText().toString());
        intent.putExtra("password",password.getText().toString());
        setResult(102,intent);
        finish();
    }
}
