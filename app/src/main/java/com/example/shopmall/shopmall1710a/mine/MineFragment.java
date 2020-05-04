package com.example.shopmall.shopmall1710a.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.view.ToorBar;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.example.shopmall.shopmall1710a.login.view.LoginActivity;

import java.util.List;

public class MineFragment extends BaseFragment<ResultBean> {
    private ImageButton ibUserIconAvator;
    @Override
    protected void initData() {

    }

    @Override
    protected List<IPresenter<ResultBean>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        toorBar = (ToorBar)rootView.findViewById(R.id.toor_bar);
        ibUserIconAvator = (ImageButton) rootView.findViewById(R.id.ib_user_icon_avator);

        toorBar.setLeftImg(R.drawable.new_user_setting);
        toorBar.setRightImg(R.drawable.new_message_icon);
        toorBar.setTitle(R.string.mine_title);
        toorBar.setTextSize(R.id.ToolBarTitle,20);
        ibUserIconAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.minefragment;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, ResultBean data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
