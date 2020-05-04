package com.example.shopmall.shopmall1710a.user;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopmall.buy.entity.CollectEntitiy;
import com.example.shopmall.buy.manager.CollectManager;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.mvp.presenter.IPresenter;
import com.example.shopmall.framework.mvp.view.BaseActivity;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.user.adapter.CollectAdapter;

import java.util.List;

public class UserCollectActivity extends BaseActivity {
    private RecyclerView collectRecyclerView;
    private CollectAdapter collectAdapter;
    @Override
   public int bindLayout() {
        return R.layout.activity_usercollect ;
    }

    @Override
    public void initView() {
        collectRecyclerView = findViewById(R.id.collectRecyclerView);
        collectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        List<CollectEntitiy> collectEntitiys = CollectManager.getInstance().queryShort();
        Log.i("boss", "initData: 收藏数据"+collectEntitiys.size());
        if (collectAdapter == null){
            collectAdapter = new CollectAdapter(collectEntitiys);
            collectRecyclerView.setAdapter(collectAdapter);
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
}
