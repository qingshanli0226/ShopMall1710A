package com.example.shopmall.shopmall1710a.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shopmall.common.Constant;
import com.example.shopmall.framework.manager.AppCore;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.entity.HomeEntity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeEntity, BaseViewHolder> {

    public HomeAdapter(List<HomeEntity> data) {
       super(data);
        addItemType(1, R.layout.item_banner );
        addItemType(2,R.layout.item_channel);
        addItemType(3,R.layout.item_act);
        addItemType(4,R.layout.item_seckill);
        addItemType(5,R.layout.item_recommend);
    }

    @Override
    protected void convert(final BaseViewHolder helper, HomeEntity item) {
        switch (helper.getItemViewType()){
            case HomeEntity.HOME_TYPE_BANNER:
                List<String> list = new ArrayList<>();
                for (HomeEntity.ResultBean.BannerInfoBean bannerInfoBean : item.getResult().getBanner_info()) {
                    Log.i("happy", "convert: "+bannerInfoBean.getImage());
                    list.add(bannerInfoBean.getImage());
                }
                Log.i("happy", "convert: adapter成功适配1");
                Banner banner = helper.getView(R.id.item_banner);
                banner.setImageLoader(new ImageLoaderInterface() {
                    @Override
                    public void displayImage(Context context, Object path, View imageView) {
                        Glide.with(context).load(Constant.BASE_IMG +path).into((ImageView) imageView);
                    }

                    @Override
                    public View createImageView(Context context) {
                        return null;
                    }
                }).setImages(list).start();
                break;
            case HomeEntity.HOME_TYPE_CHANNEL:
                RecyclerView recyclerView = helper.getView(R.id.item_channel_rv);
                recyclerView.setLayoutManager(new GridLayoutManager(AppCore.getInstance().getApp(),5));
                ChannelAdapter channelAdapter = new ChannelAdapter(item.getResult().getChannel_info());
                recyclerView.setAdapter(channelAdapter);
                break;
            case HomeEntity.HOME_TYPE_ACT:
                ImageView imageView = (ImageView) helper.getView(R.id.item_act_iv);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(AppCore.getInstance().getApp())
                        .load(Constant.BASE_IMG+item.getResult().getAct_info().get(0).getIcon_url())
                        .into(imageView);
                break;
            case HomeEntity.HOME_TYPE_SECKILL:
                RecyclerView recyclerView1 = helper.getView(R.id.item_hot_rv);
                recyclerView1.setLayoutManager(new GridLayoutManager(AppCore.getInstance().getApp(),1, LinearLayoutManager.HORIZONTAL,true));
                SeckillAdapter hotAdapter = new SeckillAdapter(item.getResult().getSeckill_info().getList());
                recyclerView1.setAdapter(hotAdapter);
//                hotAdapter.setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                        if (startActivityLinsenner != null){
//                            startActivityLinsenner.onStartOk(HomeEntity.HOME_TYPE_RECOMMEND,position);
//                        }
//                    }
//                });

                break;
            case HomeEntity.HOME_TYPE_RECOMMEND:
                RecyclerView recyclerView2 = helper.getView(R.id.item_recommend_rv);
                recyclerView2.setLayoutManager(new GridLayoutManager(AppCore.getInstance().getApp(),3));
                RecommendAdapter recommendAdapter = new RecommendAdapter(item.getResult().getRecommend_info());
                recyclerView2.setAdapter(recommendAdapter);
                recommendAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (startActivityLinsenner != null){
                            startActivityLinsenner.onStartOk(HomeEntity.HOME_TYPE_RECOMMEND,position);
                        }

                    }
                });
                break;
        }
    }

    public interface StartActivityLinsenner{
            void onStartOk(int type, int position);
    }

    public StartActivityLinsenner startActivityLinsenner;

    public void setStartActivityLinsenner(StartActivityLinsenner startActivityLinsenner) {
        this.startActivityLinsenner = startActivityLinsenner;
    }
}
