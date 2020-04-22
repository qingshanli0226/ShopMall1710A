package com.example.shopmall.shopmall1710a.home.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyTabEntity implements CustomTabEntity {
    String title;
    int selectPic;
    int unSelectPic;

    public MyTabEntity(String title, int selectPic, int unSelectPic) {
        this.title = title;
        this.selectPic = selectPic;
        this.unSelectPic = unSelectPic;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return unSelectPic;
    }

    @Override
    public int getTabUnselectedIcon() {
        return selectPic;
    }
}
