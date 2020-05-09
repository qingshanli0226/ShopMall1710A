package com.example.shopmall.shopmall1710a.message;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.framework.message.Constant;
import com.example.shopmall.framework.message.ShopMallMessage;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.product.view.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ShopmallMessageAdapter extends RecyclerView.Adapter<ShopmallMessageAdapter.MessageViewHolder> {
    private List<ShopMallMessage> shopMallMessages = new ArrayList<>();


    public void updateShopMallMessages(List<ShopMallMessage> shopMallMessageList) {
        shopMallMessages.clear();
        shopMallMessages.addAll(shopMallMessageList);
        notifyDataSetChanged();
    }

    //局部刷新,在某个位置添加数据
    public void addOneShopMallMessage(ShopMallMessage shopMallMessage, int index) {
        shopMallMessages.add(index, shopMallMessage);
        notifyItemInserted(index);
    }

    //局部刷新,在某个位置删除数据
    public void deleteOneShopMallMessage(ShopMallMessage shopMallMessage, int index) {
        shopMallMessages.remove(index);
        notifyItemRemoved(index);
    }

    //局部刷新,某一个位置的已读未读状态改变
    public void updateOneShopMallMessage(ShopMallMessage shopMallMessage, int index) {
        shopMallMessages.get(index).setIsRead(true);//现在只有这一种更新
        notifyItemChanged(index);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {

        View rootView;

        if (itemType == 0) {
            rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shopmall_message_push,viewGroup,false);
        } else {
            rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shopmall_message_zhifu,viewGroup,false);
        }
        return new MessageViewHolder(itemType,rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder messageViewHolder, final int posintion) {

        if (shopMallMessages.get(posintion).getIsRead()) {
            messageViewHolder.readStatusTv.setText(messageViewHolder.contentTv.getResources().getString(R.string.readead));
        } else {
            messageViewHolder.readStatusTv.setText(messageViewHolder.contentTv.getResources().getString(R.string.unRead));
        }

        messageViewHolder.titleTv.setText(shopMallMessages.get(posintion).getTitle());
        messageViewHolder.contentTv.setText(shopMallMessages.get(posintion).getContent());

        if (shopMallMessages.get(posintion).getType().equals(Constant.MESSAGE_TYPE_PUSH)) {
            Glide.with(messageViewHolder.contentTv.getContext()).load(shopMallMessages.get(posintion).getProductImageUrl()).into(messageViewHolder.messageImg);
        }

        messageViewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果消息是未读，改成已读
                if (!shopMallMessages.get(posintion).getIsRead()) {
                    CacheManager.getInstance().executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            CacheManager.getInstance().updateShopMallMessageToReadStatus(shopMallMessages.get(posintion));
                        }
                    });
                }
                //根据不同的消息类型，跳转到不同页面
                if (shopMallMessages.get(posintion).getType().equals(Constant.MESSAGE_TYPE_PUSH)) {
                    //跳转到产品详情页
                    ProductDetailActivity.launch((Activity)messageViewHolder.root.getContext(),
                            shopMallMessages.get(posintion).getProductId(),
                            shopMallMessages.get(posintion).getProductName(),
                            shopMallMessages.get(posintion).getProductPrice(),
                            shopMallMessages.get(posintion).getProductImageUrl());
                }
            }
        });

        //长按删除一条数据
        messageViewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CacheManager.getInstance().executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        CacheManager.getInstance().deleteShopMessage(shopMallMessages.get(posintion));
                    }
                });

                return false;
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        if (shopMallMessages.get(position).getType().equals(Constant.MESSAGE_TYPE_PUSH)) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return shopMallMessages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;
        public ImageView messageImg;
        public TextView contentTv;
        public TextView readStatusTv;
        public LinearLayout root;

        public MessageViewHolder(int itemType,View rootView) {
            super(rootView);
            root = rootView.findViewById(R.id.root);
            titleTv = rootView.findViewById(R.id.titleTv);
            contentTv = rootView.findViewById(R.id.contentTv);
            readStatusTv = rootView.findViewById(R.id.readStatusTv);
            if (itemType == 0) {
                 messageImg= rootView.findViewById(R.id.shopmall_message_img);
            }

        }
    }
}
