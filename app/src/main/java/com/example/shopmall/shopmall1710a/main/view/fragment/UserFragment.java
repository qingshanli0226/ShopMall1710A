package com.example.shopmall.shopmall1710a.main.view.fragment;


import android.view.View;
import android.widget.Button;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;
@Route(path = Constant.ROUTER_PATH_USER_FRAGMENT)
public class UserFragment  extends BaseFragment {
    @Override
   public int bindLayout() {
        return R.layout.frgament_user ;
    }

    @Override
    public void initView() {
        findViewById(R.id.userLoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(Constant.ROUTER_PATH_LOGIN_ACTIVITY)
                        .navigation();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public List<IPresenter> getPresenter() {
        return null;
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
