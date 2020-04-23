package com.example.shopmall.framework.base.view;

import android.view.View;
import androidx.annotation.IdRes;

public interface IBaseFragment extends IBaseActivity {
    <T extends View> T findViewById(@IdRes int id);

}
