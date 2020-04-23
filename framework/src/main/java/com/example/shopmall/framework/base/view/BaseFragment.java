package com.example.shopmall.framework.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.shopmall.framework.base.presenter.IBasePresenter;

public abstract class BaseFragment<P extends IBasePresenter, T> extends Fragment implements IBaseView<T>, IBaseFragment {

    protected P mPresenter;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(bindLayout(), container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        inject();
        initData();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.destroy();
            System.gc();
        }
    }
}
