package com.example.shopmall.shopmall1710a.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.recommend.RecommendActivity;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ResultBean resultBean;
    private LayoutInflater layoutInflater;
    int currentType=0;
    public MyAdapter(Context context, ResultBean resultBean) {
        this.context = context;
        this.resultBean = resultBean;
        layoutInflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            return new BannerViewHolder(layoutInflater.inflate(R.layout.item_layout,null),context,resultBean);
        }else if(i==1){
            return new ChannelViewHolder(layoutInflater.inflate(R.layout.channel_layout,null),context,resultBean);
        }else if(i==2){
            return new ActViewHolder(layoutInflater.inflate(R.layout.item_layout,null),context,resultBean);
        }else if(i==3){
            return new SeckillViewHolder(layoutInflater.inflate(R.layout.seckill_layout,null),context,resultBean);
        }else if(i==4){
            return new RecommendViewHolder(layoutInflater.inflate(R.layout.recommend_layout,null),context,resultBean);
        }else if(i==5){
            return new GoodsViewHolder(layoutInflater.inflate(R.layout.goods_layout,null),context,resultBean);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(getItemViewType(i)==0){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }else if (getItemViewType(i)==1){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) viewHolder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if (getItemViewType(i)==2){
            ActViewHolder actViewHolder = (ActViewHolder) viewHolder;
            actViewHolder.setData(resultBean.getAct_info());
        }else if (getItemViewType(i)==3){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) viewHolder;
            seckillViewHolder.setData(resultBean.getSeckill_info().getList());
        }else if (getItemViewType(i)==4){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) viewHolder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if (getItemViewType(i)==5){
            GoodsViewHolder goodsViewHolder = (GoodsViewHolder) viewHolder;
            goodsViewHolder.setData(resultBean.getHot_info());
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch(position){
            case 0:
                currentType=0;
            break;
            case 1:
                currentType=1;
                break;
            case 2:
                currentType=2;
                break;
            case 3:
                currentType=3;
                break;
            case 4:
                currentType=4;
                break;
            case 5:
                currentType=5;
                break;
            default:
            break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 6;
    }
    class BannerViewHolder extends RecyclerView.ViewHolder{
        public Banner banner;
        public Context mContext;
        private ResultBean resultBean;
        List<String> piclist=new ArrayList<>();

        public BannerViewHolder(@NonNull View itemView, Context mContext, ResultBean resultBean) {
            super(itemView);
            banner=itemView.findViewById(R.id.shop_banner);
            this.mContext = mContext;
            this.resultBean = resultBean;
        }

        public void setData(List<ResultBean.BannerInfoBean> banner_info){
            Log.i("tag", "setData: "+11);
            for (int i = 0; i < banner_info.size(); i++) {
                String image = banner_info.get(i).getImage();
                piclist.add(Constant.BASE_URL+"atguigu/img"+image);
            }
            Log.i("TAG", "onHtttpReceived: "+piclist.get(1));
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }

                @Override
                public ImageView createImageView(Context context) {
                    return new ImageView(context);
                }
            });

            banner.setImages(piclist);
            banner.setDelayTime(1000);
            banner.start();
        }
    }
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public Context mContext;
        private ResultBean resultBean;

        public ChannelViewHolder(@NonNull View itemView, Context mContext, ResultBean resultBean) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.re_view);
            this.mContext = mContext;
            this.resultBean = resultBean;
        }

        public void setData(List<ResultBean.ChannelInfoBean> channelInfoBeans){

            recyclerView.setLayoutManager(new GridLayoutManager(context,5));
            recyclerView.setAdapter(new ChannelAdapter(R.layout.channel_item_layout,channelInfoBeans));
        }
    }
    class ActViewHolder extends RecyclerView.ViewHolder{
        public Banner banner;
        public Context mContext;
        private ResultBean resultBean;
        List<String> piclist=new ArrayList<>();

        public ActViewHolder(@NonNull View itemView, Context mContext, ResultBean resultBean) {
            super(itemView);
            banner=itemView.findViewById(R.id.shop_banner);
            this.mContext = mContext;
            this.resultBean = resultBean;
        }

        public void setData(List<ResultBean.ActInfoBean> actInfoBeans){
            Log.i("tag", "setData: "+11);
            for (int i = 0; i < actInfoBeans.size(); i++) {
                String image = actInfoBeans.get(i).getIcon_url();
                piclist.add(Constant.BASE_URL+"atguigu/img"+image);
            }
            Log.i("TAG", "onHtttpReceived: "+piclist.get(1));
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }

                @Override
                public ImageView createImageView(Context context) {
                    return new ImageView(context);
                }
            });

            banner.setImages(piclist);
            banner.setDelayTime(1000);
            banner.start();
        }
    }
    class SeckillViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public Context mContext;
        private ResultBean resultBean;

        public SeckillViewHolder(@NonNull View itemView, Context mContext, ResultBean resultBean) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.re_view);
            this.mContext = mContext;
            this.resultBean = resultBean;
        }

        public void setData(List<ResultBean.SeckillInfoBean.ListBean> seckillInfoBeans){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new SeckillAdapter(R.layout.seckill_item_layout,seckillInfoBeans));
        }
    }
    class RecommendViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public Context mContext;
        private ResultBean resultBean;

        public RecommendViewHolder(@NonNull View itemView, Context mContext, ResultBean resultBean) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.re_view);
            this.mContext = mContext;
            this.resultBean = resultBean;

        }

        public void setData(final List<ResultBean.RecommendInfoBean> recommendInfoBeans){

            recyclerView.setLayoutManager( new GridLayoutManager(context,3));
            RecommendAdapter recommendAdapter = new RecommendAdapter(R.layout.recommend_item_layout, recommendInfoBeans);

            recyclerView.setAdapter(recommendAdapter);
        }

    }

    class GoodsViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public Context mContext;
        private ResultBean resultBean;

        public GoodsViewHolder(@NonNull View itemView, Context mContext, ResultBean resultBean) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.re_view);
            this.mContext = mContext;
            this.resultBean = resultBean;
        }

        public void setData(final List<ResultBean.HotInfoBean> hotInfoBeans){

            recyclerView.setLayoutManager(new GridLayoutManager(context,2));
            GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.goods_item_layout, hotInfoBeans);
            recyclerView.setAdapter(goodsAdapter);
            goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent();
                    intent.setClass(context,RecommendActivity.class);
                    intent.putExtra("productImageUrl", hotInfoBeans.get(position).getFigure());
                    intent.putExtra("productPrice", hotInfoBeans.get(position).getCover_price());
                    intent.putExtra("productName", hotInfoBeans.get(position).getName());
                    intent.putExtra("productId", hotInfoBeans.get(position).getProduct_id());
                    context.startActivity(intent);
                    Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
