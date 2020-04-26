package com.example.shopmall.shopmall1710a.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.manager.ShopUserManager;
import com.example.shopmall.shopmall1710a.R;

public class HomeFragment extends Fragment implements CacheManager.IShopCountRecevedLisener {
    private TextView countTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        countTV = rootView.findViewById(R.id.count);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CacheManager.getInstance().registerShopCountListener(this);
        //如果登录成功，sp中已经存储了购物产品的数量
        if (ShopUserManager.getInstance().isUserLogin()) {
            countTV.setText(String.valueOf(SpUtil.getShopcarCount(getActivity())));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterShopCountListener(this);
    }

    @Override
    public void onShopcarCountReceived(int conunt) {
        countTV.post(new Runnable() {
            @Override
            public void run() {
                countTV.setText(String.valueOf(SpUtil.getShopcarCount(getActivity())));
            }
        });

    }
}
