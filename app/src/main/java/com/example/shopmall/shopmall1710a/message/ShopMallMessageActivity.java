package com.example.shopmall.shopmall1710a.message;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.shopmall.framework.base.BaseActivity;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.message.ShopMallMessage;
import com.example.shopmall.shopmall1710a.R;

import java.util.List;

//显示消息列表的页面
public class ShopMallMessageActivity extends BaseActivity<Object> implements CacheManager.IShopMessageChangedListener {
    private RecyclerView shopmallMessageRV;
    protected ShopmallMessageAdapter shopmallMessageAdapter;


    @Override
    protected void initData() {
        List<ShopMallMessage> shopMallMessageList = CacheManager.getInstance().getShopMallMessageList();
        shopmallMessageAdapter.updateShopMallMessages(shopMallMessageList);
        CacheManager.getInstance().registerShopMessageChangedListener(this);//去监听消息数据的变化
    }

    @Override
    protected List<IPresenter<Object>> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        shopmallMessageRV = findViewById(R.id.shopmallMessageRV);
        shopmallMessageRV.setLayoutManager(new LinearLayoutManager(this));
        shopmallMessageAdapter = new ShopmallMessageAdapter();
        shopmallMessageRV.setAdapter(shopmallMessageAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopmall_message;
    }

    @Override
    protected void destroy() {

    }

    //需要更新所有数据
    @Override
    public void onShopMessageChanged(final List<ShopMallMessage> shopMallMessageList, int unReadCount, int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shopmallMessageAdapter.updateShopMallMessages(shopMallMessageList);
            }
        });
    }

    @Override
    public void onShopMessageAdded(final ShopMallMessage shopMallMessage, int unReadCount, final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shopmallMessageAdapter.addOneShopMallMessage(shopMallMessage, index);
            }
        });

    }

    @Override
    public void onShopMessageDelted(final ShopMallMessage shopMallMessage, int unReadCount, final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shopmallMessageAdapter.deleteOneShopMallMessage(shopMallMessage, index);
            }
        });
    }

    @Override
    public void onShopMessageUpdated(final ShopMallMessage shopMallMessage, int unReadCount, final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shopmallMessageAdapter.updateOneShopMallMessage(shopMallMessage, index);
            }
        });

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ShopMallMessageActivity.class);
        activity.startActivity(intent);
    }
}
