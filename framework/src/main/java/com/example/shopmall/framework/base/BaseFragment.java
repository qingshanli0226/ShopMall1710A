package com.example.shopmall.framework.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public abstract class BaseFragment<P extends BasePresenter,T> extends Fragment implements IBaseView<T>, IBaseFragment {
    protected  P mPresenter;
    private View mView;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mView = inflater.inflate(bindLayout(),container,false);
        initView();
        return mView;
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        inject();
        initData();
    }

    @Override
    public void inject() {

    }

    @Override
    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter = null;
            System.gc();
        }
    }

}
