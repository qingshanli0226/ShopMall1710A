package com.example.shopmall.shopmall1710a.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.common.ErrorBean;
import com.example.shopmall.framework.base.BaseFragment;
import com.example.shopmall.framework.base.IPresenter;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.home.base.ResultBean;
import com.example.shopmall.shopmall1710a.home.presenter.BannerPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<ResultBean> {
    private Banner banner;
    private RecyclerView reView;
    BannerPresenter bannerPresenter;
    List<String> piclist=new ArrayList<>();
    @Override
    protected void initData() {
        bannerPresenter.getHttpData(1);
    }

    @Override
    protected List<IPresenter<ResultBean>> getPresenter() {
        ArrayList<IPresenter<ResultBean>> iPresenters = new ArrayList<>();

        iPresenters.add(bannerPresenter);
        return iPresenters;
    }

    @Override
    protected void initView() {
        bannerPresenter = new BannerPresenter();
        banner = (Banner) rootView.findViewById(R.id.banner);
        reView = (RecyclerView) rootView.findViewById(R.id.re_view);
        loadingBar = rootView.findViewById(R.id.progressBar);

        bannerPresenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }

    @Override
    protected void destroy() {
        bannerPresenter.detachView();
    }


    @Override
    public void onHtttpReceived(int requestCode, ResultBean data) {
        List<ResultBean.BannerInfoBean> banner_info = data.getBanner_info();
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

    @Override
    public void onHttpReceivedFailed(int requestCode, ErrorBean errorBean) {

    }
}
