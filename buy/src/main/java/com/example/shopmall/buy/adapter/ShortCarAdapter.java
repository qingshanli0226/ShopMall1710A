package com.example.shopmall.buy.adapter;



import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.buy.R;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.manager.AppCore;

import java.util.List;


public class ShortCarAdapter extends BaseQuickAdapter<ShortCarEntity.ResultBean, BaseViewHolder> {

    public ShortCarAdapter(@Nullable List<ShortCarEntity.ResultBean> data) {
        super(R.layout.item_short_car , data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ShortCarEntity.ResultBean item) {
        CheckBox itemShortCarCheckBox = helper.getView(R.id.itemShortCarCheckBox);
        itemShortCarCheckBox.setChecked(item.isProductSelected());
        Glide.with(AppCore.getInstance().getApp())
                .load(Constant.BASE_IMG+item.getUrl())
                .into((ImageView) helper.getView(R.id.itemShortCarImage));
        helper.setText(R.id.itemShortCarNum,item.getProductNum());
        helper.setText(R.id.itemShortCarMoney,item.getProductPrice());

        TextView itemShortCarRemove = helper.getView(R.id.itemShortCarRemove);
        TextView itemShortCarAdd = helper.getView(R.id.itemShortCarAdd);


        itemShortCarCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShortCarClickLisener != null){
                    onShortCarClickLisener.onChangeCheckState(helper.getAdapterPosition());
                }
            }
        });
        itemShortCarRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShortCarClickLisener != null){
                    onShortCarClickLisener.onReduceNum(helper.getAdapterPosition());
                }
            }
        });
        itemShortCarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShortCarClickLisener != null){
                    onShortCarClickLisener.onAddNum(helper.getAdapterPosition());
                }
            }
        });
    }



    public interface OnShortCarClickLisener{
        void onReduceNum(int position); // 减少商品数量
        void onAddNum(int position); // 增加商品数量
        void onChangeCheckState(int position); // 选中状态的改变
    }

    private OnShortCarClickLisener onShortCarClickLisener;

    public void setOnShortCarClickLisener(OnShortCarClickLisener onShortCarClickLisener) {
        this.onShortCarClickLisener = onShortCarClickLisener;
    }
}
