package com.example.shopmall.shopmall1710a.login.view;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLoginPresenter;
import com.example.shopmall.shopmall1710a.login.presenter.BetterLogoutPresenter;
import com.example.shopmall.shopmall1710a.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BetterLoginActivity extends BaseActivity<Object> implements View.OnClickListener {

    private EditText passwordEditText;
    private EditText nameEditText;
    private BetterLoginPresenter loginPresenter;
    private BetterLogoutPresenter logoutPresenter;

    private String flag;

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
        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
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

        if (requstCode == 0) {
            if (flag!=null&&flag.equals("home")) {//当flag为home时，代表登录成功后，跳转到首页面
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("index", 0);
                startActivity(intent);
            }
            //登录成功后，需要把登录信息存储到UserManager中，方便集中管理登录用户信息.
            //因为类型问题，存储时将data，强制转换成framework中ResultBean。因为字段一样，不会出现错误
            com.example.shopmall.framework.bean.LoginBean.ResultBean resultBean = (com.example.shopmall.framework.bean.LoginBean.ResultBean)data;
            ShopUserManager.getInstance().saveToken(this, resultBean.getToken());
            ShopUserManager.getInstance().saveUserLoginBeanAndNotify(resultBean);

            finish();
        }
    }


    @Override
    public void onHttpReceivedFailed(int requstCode, ErrorBean errorBean) {
        Toast.makeText(this, "登录失败:" + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
