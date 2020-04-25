package com.example.shopmall.framework.mvp.view;

import android.view.View;
import androidx.annotation.IdRes;

public interface IBaseFragment extends IBaseActivity {
    // 提供 findViewById 的方法
    <T extends View> T findViewById(@IdRes int id);

}
