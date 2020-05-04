package com.example.shopmall.shopmall1710a.user;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.shopmall.buy.NoPayActivity;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.user.UserCollectActivity;
import com.example.shopmall.shopmall1710a.user.adapter.UserlistAdapter;
import com.example.shopmall.shopmall1710a.user.entity.UserListEntity;

import java.util.ArrayList;
import java.util.List;
@Route(path = Constant.ROUTER_PATH_USER_FRAGMENT)
public class UserFragment  extends BaseFragment implements View.OnClickListener {
    private RadioButton userNoPay;
    private RecyclerView userRecyclerView;
    private List<UserListEntity> userListEntities;
    private UserlistAdapter userlistAdapter;
    @Override
   public int bindLayout() {
        return R.layout.frgament_user ;
    }

    @Override
    public void initView() {
        findViewById(R.id.userLoginBtn).setOnClickListener(this);
        userNoPay = (RadioButton) findViewById(R.id.userNoPay);
        userNoPay.setOnClickListener(this);
        findViewById(R.id.userCollect).setOnClickListener(this);
        userRecyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userListEntities = new ArrayList<>();
    }

    @Override
    public void initData() {
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_address,"收货地址"));
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_collect,"我的收藏"));
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_coupon,"我的优惠券"));
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_score,"我的瓜子"));
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_prize,"我的奖品"));
        userListEntities.add(new UserListEntity(R.mipmap.user_icon_ticket,"我的电子票"));
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_invitation,"邀请分享"));
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_callcenter,"客服中心"));
        userListEntities.add(new UserListEntity(R.mipmap.user_channel_icon_feedback,"服务反馈"));
        if (userlistAdapter == null){
            userlistAdapter = new UserlistAdapter(userListEntities);
            userRecyclerView.setAdapter(userlistAdapter);
        }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.userLoginBtn:
                ARouter.getInstance().build(Constant.ROUTER_PATH_LOGIN_ACTIVITY)
                 .navigation();
                break;
            case R.id.userNoPay : // 查看待支付
                startActivity(new Intent(getActivity(), NoPayActivity.class));
            break;
            case R.id.userCollect: // 展示商品收藏
                    startActivity(new Intent(getActivity(), UserCollectActivity.class));
                break;
        }
    }
}
