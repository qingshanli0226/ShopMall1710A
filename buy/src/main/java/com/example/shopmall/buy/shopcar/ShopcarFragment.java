package com.example.shopmall.buy.shopcar;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.shopcar.presenter.AddShopcarPresenter;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.framework.manager.CacheManager;

import java.util.ArrayList;
import java.util.List;


public class ShopcarFragment extends BaseFragment<String> implements CacheManager.IShopCountRecevedLisener{
    private AddShopcarPresenter addShopcarPresenter;
    private static int productId = 2;
    private TextView textView;
    private Handler handler = new Handler();
    @Override
    protected List<IPresenter<String>> getPresenter() {
        addShopcarPresenter = new AddShopcarPresenter();
        List<IPresenter<String>> iPresenterList = new ArrayList<>();
        iPresenterList.add(addShopcarPresenter);
        return iPresenterList;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onHtttpReceived(int requestCode, String data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopcar;
    }

    @Override
    protected void initView() {
        CacheManager.getInstance().registerShopCountListener(this);
        textView = inflate.findViewById(R.id.tv_sum);
        inflate.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShopcarPresenter.addParams(String.valueOf(productId));
                addShopcarPresenter.postHttpDataWithJson(1);
                textView.setText(SpUtil.getShopcarCount(getContext()) +"");
            }
        });


    }

    @Override
    public void onShopcarCountReceived(final int conunt) {
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterShopCountListener(this);
    }
}
