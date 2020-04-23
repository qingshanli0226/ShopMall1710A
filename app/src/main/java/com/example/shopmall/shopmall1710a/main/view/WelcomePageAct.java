package com.example.shopmall.shopmall1710a.main.view;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.presenter.IPresenter;
import com.example.shopmall.framework.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomePageAct extends BaseActivity {
    private Timer timer;
    private int count;
    private Handler handler = new Handler();
    @Override
    protected List<IPresenter> getPresenter() {
        return null;
    }

    @Override
   public int bindLayout() {
        return R.layout.act_welcome;
    }

    @Override
    public void initView() {
        welcomIv.setVisibility(View.VISIBLE);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("happy", "run: ----------------");
                if (count == 3){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            count = 0;
                            welcomIv.setVisibility(View.GONE);
                            startActivity(new Intent(WelcomePageAct.this,MainAct.class));
                            timer.cancel();
                            timer = null;
                            finish();
                        }
                    });
                }
                count++;
            }
        },0,1000);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onHttpReceived(int requestCode, Object data) {

    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }


}
