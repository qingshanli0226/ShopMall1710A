package com.example.shopmall.shopmall1710a.main.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.manager.LoginEntity;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.presenter.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ROUTER_PATH_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity {
    private EditText et_user_login;
    private EditText et_pwd_login;
    private LoginPresenter mLoginPresenter;
    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        et_user_login = (EditText) findViewById(R.id.et_pwd_login);
        et_pwd_login = (EditText) findViewById(R.id.et_pwd_login);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.addParams(Integer.parseInt(et_user_login.getText().toString()),et_pwd_login.getText().toString());
                mLoginPresenter.postHttpData(100);
            }
        });
    }

    @Override
    public void initPresenter() {
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public List<IPresenter> getPresenter() {
        List<IPresenter> iPresenters = new ArrayList<>();
        iPresenters.add(mLoginPresenter);
        return iPresenters;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        if (requestCode == 100){
            LoginEntity loginEntity = (LoginEntity) data;
            Log.i("boss", "onHtttpReceived: 手动登录成功!"+loginEntity.getToken());
            SPUtils.getInstance().put(Constant.SP_TOKEN,loginEntity.getToken());
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoginPresenter != null){
            mLoginPresenter.destroy();
        }
    }
}
