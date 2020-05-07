package com.example.shopmall.shopmall1710a.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.shopmall.common.Constant;
import com.example.shopmall.shopmall1710a.R;
import com.example.shopmall.shopmall1710a.main.bean.HomeBean;
import com.example.shopmall.shopmall1710a.product.view.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<HomeBean.ResultBean.HotInfoBean> hotInfoBeanList = new ArrayList<>();
    private HomeFragment homeFragment;

    public HomeAdapter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public void addData(List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans) {
        hotInfoBeanList.clear();
        hotInfoBeanList.addAll(hotInfoBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = inflater.inflate(R.layout.item_home_layout,viewGroup,false);
        return new HomeViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder homeViewHolder, final int i) {
        Glide.with(homeViewHolder.imageView.getContext()).load(Constant.BASE_URL+"atguigu/img" +
        hotInfoBeanList.get(i).getFigure()).into(homeViewHolder.imageView);
        homeViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(homeViewHolder.imageView.getContext(),ProductDetailActivity.class);
                intent.putExtra("productImageUrl", hotInfoBeanList.get(i).getFigure());
                intent.putExtra("productPrice", hotInfoBeanList.get(i).getCover_price());
                intent.putExtra("productName", hotInfoBeanList.get(i).getName());
                intent.putExtra("productId", hotInfoBeanList.get(i).getProduct_id());
                homeViewHolder.imageView.getContext().startActivity(intent);
                Toast.makeText(homeViewHolder.imageView.getContext(), "点击", Toast.LENGTH_SHORT).show();
                //homeFragment.getActivity().finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotInfoBeanList.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public HomeViewHolder(View rootView) {
            super(rootView);
            imageView = rootView.findViewById(R.id.img);
        }
    }
}
