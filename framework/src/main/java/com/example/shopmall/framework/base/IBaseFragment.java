package com.example.shopmall.framework.base;

import android.support.annotation.IdRes;
import android.view.View;

public interface IBaseFragment extends IBaseActivity {
    <T extends View> T findViewById(@IdRes int id);

}
