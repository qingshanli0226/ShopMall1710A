package com.example.shopmall.buy.shopcar;

import android.view.View;
import android.widget.Toast;
import com.example.shopmall.buy.R;
import com.example.shopmall.buy.shopcar.presenter.AddShopcarPresenter;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;

import java.util.ArrayList;
import java.util.List;

public class ShopcarFragment extends BaseFragment<String> {
    private AddShopcarPresenter addShopcarPresenter;
    private static int productId = 2;
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
    protected void initView(View rootView) {
        rootView.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShopcarPresenter.addParams(String.valueOf(productId));
                addShopcarPresenter.postHttpDataWithJson(1);
            }
        });
    }

    @Override
    public void onHtttpReceived(int requestCode, String data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopcar;
    }
}
