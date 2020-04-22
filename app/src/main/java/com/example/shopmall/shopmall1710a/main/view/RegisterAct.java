package com.example.shopmall.shopmall1710a.main.view;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.presenter.RegisterPresenter;

import java.util.ArrayList;
import java.util.List;

public class RegisterAct extends BaseActivity {
    private EditText et_user_reg;
    private EditText et_pwd_reg;
    private RegisterPresenter mRegisterPresenter;
    @Override
    public int bindLayout() {
        return R.layout.act_register;
    }

    @Override
    public void initView() {
        et_pwd_reg = (EditText) findViewById(R.id.et_pwd_reg);
        et_user_reg = (EditText) findViewById(R.id.et_user_reg);
        findViewById(R.id.btn_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterPresenter.addParams(Integer.parseInt(et_user_reg.getText().toString()),et_pwd_reg.getText().toString());
                mRegisterPresenter.postHttpData(100);
            }
        });
    }

    @Override
    public void initPresenter() {
        mRegisterPresenter = new RegisterPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {
        if (requestCode == 100){
            String str = (String) data;
            Log.i("happy", "onHtttpReceived: "+str);
        }
    }

    @Override
    protected List<IPresenter> getPresenter() {
        ArrayList<IPresenter> iPresenters = new ArrayList<>();
        iPresenters.add(mRegisterPresenter);
        return iPresenters;
    }
}
