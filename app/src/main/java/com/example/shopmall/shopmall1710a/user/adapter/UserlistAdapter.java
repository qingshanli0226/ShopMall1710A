package com.example.shopmall.shopmall1710a.user.adapter;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.user.entity.UserListEntity;

import java.util.List;

public class UserlistAdapter extends BaseQuickAdapter<UserListEntity, BaseViewHolder> {
    public UserlistAdapter(@Nullable List<UserListEntity> data) {
        super(R.layout.item_user, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserListEntity item) {
        ImageView imageView = helper.getView(R.id.itemUserImg);
        imageView.setImageResource(item.getImg());
        helper.setText(R.id.itemUserText,item.getText());
    }
}
