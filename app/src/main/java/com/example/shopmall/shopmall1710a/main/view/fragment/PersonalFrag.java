package com.example.shopmall.shopmall1710a.main.view.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.BaseFragment;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.login.view.BetterLoginActivity;
import com.example.shopmall.shopmall1710a.main.view.activity.FailOrderActivity;

public class PersonalFrag extends BaseFragment implements View.OnClickListener {


    private ScrollView scrollview;
    private ImageButton ibUserIconAvator;
    private TextView tvAllOrder;
    private TextView tvUserPay;
    private TextView tvUserReceive;
    private TextView tvUserFinish;
    private TextView tvUserDrawback;
    private TextView tvUserLocation;
    private TextView tvUserCollect;
    private TextView tvUserCoupon;
    private TextView tvUserScore;
    private TextView tvUserPrize;
    private TextView tvUserTicket;
    private TextView tvUserInvitation;
    private TextView tvUserCallcenter;
    private TextView tvUserFeedback;
    private TextView tvUsercenter;
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;
    private TextView tvUsername;

    @Override
    public int bindLayout() {
        return R.layout.frag_person;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView() {

        scrollview = (ScrollView) findViewById(R.id.scrollview);
        ibUserIconAvator = (ImageButton) findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvAllOrder = (TextView) findViewById(R.id.tv_all_order);
        tvUserPay = (TextView) findViewById(R.id.tv_user_pay);
        tvUserReceive = (TextView) findViewById(R.id.tv_user_receive);
        tvUserFinish = (TextView) findViewById(R.id.tv_user_finish);
        tvUserDrawback = (TextView) findViewById(R.id.tv_user_drawback);
        tvUserLocation = (TextView) findViewById(R.id.tv_user_location);
        tvUserCollect = (TextView) findViewById(R.id.tv_user_collect);
        tvUserCoupon = (TextView) findViewById(R.id.tv_user_coupon);
        tvUserScore = (TextView) findViewById(R.id.tv_user_score);
        tvUserPrize = (TextView) findViewById(R.id.tv_user_prize);
        tvUserTicket = (TextView) findViewById(R.id.tv_user_ticket);
        tvUserInvitation = (TextView) findViewById(R.id.tv_user_invitation);
        tvUserCallcenter = (TextView) findViewById(R.id.tv_user_callcenter);
        tvUserFeedback = (TextView) findViewById(R.id.tv_user_feedback);
        tvUsercenter = (TextView) findViewById(R.id.tv_usercenter);
        ibUserSetting = (ImageButton) findViewById(R.id.ib_user_setting);
        ibUserMessage = (ImageButton) findViewById(R.id.ib_user_message);

        ibUserIconAvator.setOnClickListener(this);
        tvUserPay.setOnClickListener(this);

    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {


    }

    @Override
    public void onHtttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_user_icon_avator:
                Intent intent = new Intent(getActivity(), BetterLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_user_pay:
                Intent intent1 = new Intent(getActivity(), FailOrderActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
