package com.example.shopmall.shopmall1710a.main.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.view.IBaseView;
import com.example.shopmall.shopmall1710a.R;

public class TypeFrag extends Fragment implements IBaseView<String> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.frag_type, container, false);
        return inflate;
    }

    @Override
    public void onHtttpReceived(int requestCode, String data) {

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
}
