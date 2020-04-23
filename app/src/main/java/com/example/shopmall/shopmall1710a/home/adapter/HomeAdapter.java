package com.example.shopmall.shopmall1710a.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.mode.ResultBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ResultBean resultBean;
    private int currenType = 0;
    public HomeAdapter(Context context, ResultBean resultBean) {
        this.context = context;
        this.resultBean = resultBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.e("TAG", "onCreateViewHolder: ");
        if (i==0){
            BannerVH bannerVH = new BannerVH(LayoutInflater.from(context).inflate(R.layout.banner_layout, null), context, resultBean);
            return bannerVH;
        }
        else if (i==2){
            ActVH actVH = new ActVH(LayoutInflater.from(context).inflate(R.layout.act_layout, null), context, resultBean);
            return actVH;
        }
        else if (i==1){
            ChannelVH channelVH = new ChannelVH(LayoutInflater.from(context).inflate(R.layout.channel_layout, null), context, resultBean);
            return channelVH;
        }
        else if (i==3){
            SeckillVH seckillVH = new SeckillVH(LayoutInflater.from(context).inflate(R.layout.seckill_layout, null), context, resultBean);
            return seckillVH;
        }
        else if (i==4){
            RecommondVH recommondVH = new RecommondVH(LayoutInflater.from(context).inflate(R.layout.recommond_layout, null), context, resultBean);
            return recommondVH;
        }
        else if (i==5){
            HotVH hotVH = new HotVH(LayoutInflater.from(context).inflate(R.layout.hot_layout, null), context, resultBean);
            return hotVH;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                currenType = 0;
                break;
            case 1:
                currenType = 1;
                break;
            case 2:
                currenType = 2;
                break;
            case 3:
                currenType = 3;
                break;
            case 4:
                currenType = 4;
                break;
            case 5:
                currenType = 5;
                break;

        }
        return currenType;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i)==0){
            BannerVH bannerVH = (BannerVH) viewHolder;
            bannerVH.setData(resultBean.getBanner_info());
        }
        if (getItemViewType(i)==2){
            ActVH actVH = (ActVH) viewHolder;
            actVH.setData(resultBean.getAct_info());
        }
        if (getItemViewType(i)==3){
            SeckillVH seckillVH = (SeckillVH) viewHolder;
            seckillVH.setData(resultBean.getSeckill_info().getList());
        }
        if (getItemViewType(i)==1){
            ChannelVH channelVH = (ChannelVH) viewHolder;
            channelVH.setData(resultBean.getChannel_info());
        }
        if (getItemViewType(i)==4){
            RecommondVH recommondVH = (RecommondVH) viewHolder;
            recommondVH.setData(resultBean.getRecommend_info());
        }
        if (getItemViewType(i)==5){
            HotVH hotVH = (HotVH) viewHolder;
            hotVH.setData(resultBean.getHot_info());
        }
    }


    @Override
    public int getItemCount() {
        return 6;
    }

    class BannerVH extends RecyclerView.ViewHolder{
        public Banner banner;
        public Context mContext;
        public ResultBean resultBean;
        public BannerVH(@NonNull View itemView, Context context, ResultBean resultBean) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
            this.mContext = context;
            this.resultBean = resultBean;
        }
        public void setData(final List<ResultBean.BannerInfoBean> banner_info) {
            Log.e("TAG", "setData: ");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                list.add(banner_info.get(i).getImage());
            }
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load("http://49.233.93.155:8080/atguigu/img"+path).into(imageView);
                }

                @Override
                public ImageView createImageView(Context context) {
                    return new ImageView(context);
                }
            });
            banner.setImages(list);
            banner.setDelayTime(1000);
            banner.start();
        }
    }

    class ActVH extends RecyclerView.ViewHolder{
        public Banner banner;
        public Context mContext;
        public ResultBean resultBean;
        public ActVH(@NonNull View itemView, Context context, ResultBean resultBean) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.act_banner);
            this.mContext = context;
            this.resultBean = resultBean;
        }
        public void setData(final List<ResultBean.ActInfoBean> actInfoBeans) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < actInfoBeans.size(); i++) {
                list.add(actInfoBeans.get(i).getIcon_url());
            }
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load("http://49.233.93.155:8080/atguigu/img"+path).into(imageView);
                }

                @Override
                public ImageView createImageView(Context context) {
                    return new ImageView(context);
                }
            });
            banner.setImages(list);
            banner.setDelayTime(1000);
            banner.start();
        }
    }


    class SeckillVH extends RecyclerView.ViewHolder{
        public TextView textView;
        public RecyclerView recyclerView;
        public Context mContext;
        public ResultBean resultBean;
        public SeckillVH(@NonNull View itemView, Context context, ResultBean resultBean) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.Seckill_show);
            textView = (TextView) itemView.findViewById(R.id.Seckill_title);
            this.mContext = HomeAdapter.this.context;
            this.resultBean = resultBean;
        }
        public void setData(List<ResultBean.SeckillInfoBean.ListBean> list) {
            textView.setText("今日闪购");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new SeckillAdapter(R.layout.seckill_item,list));
        }
    }

    class ChannelVH extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;
        public Context mContext;
        public ResultBean resultBean;
        public ChannelVH(@NonNull View itemView, Context context, ResultBean resultBean) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.channel_show);
            this.mContext = HomeAdapter.this.context;
            this.resultBean = resultBean;
        }
        public void setData(List<ResultBean.ChannelInfoBean> list) {
            recyclerView.setLayoutManager(new GridLayoutManager(context,5));
            recyclerView.setAdapter(new ChannelAdapter(R.layout.channel_item,list));
        }
    }


    class RecommondVH extends RecyclerView.ViewHolder{
        public TextView textView;
        public RecyclerView recyclerView;
        public Context mContext;
        public ResultBean resultBean;
        public RecommondVH(@NonNull View itemView, Context context, ResultBean resultBean) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.Recommond_show);
            textView = (TextView) itemView.findViewById(R.id.Recommmond_title);
            this.mContext = HomeAdapter.this.context;
            this.resultBean = resultBean;
        }
        public void setData(List<ResultBean.RecommendInfoBean> list) {
            textView.setText("今日推荐");
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new RecommondAdapter(R.layout.recommond_item,list));
        }
    }

    class HotVH extends RecyclerView.ViewHolder{
        public TextView textView;
        public RecyclerView recyclerView;
        public Context mContext;
        public ResultBean resultBean;
        public HotVH(@NonNull View itemView, Context context, ResultBean resultBean) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.Hot_show);
            textView = (TextView) itemView.findViewById(R.id.Hot_title);
            this.mContext = HomeAdapter.this.context;
            this.resultBean = resultBean;
        }
        public void setData(List<ResultBean.HotInfoBean> list) {
            textView.setText("这些都是买的棒棒哒");
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new HotAdapter(R.layout.hot_item,list));
        }
    }
}
