package com.example.shopmall.shopmall1710a.main.adapter;


import com.flyco.tablayout.listener.CustomTabEntity;

public class MyTabEntitie implements CustomTabEntity {
    private String title;
    private int ui;
    private int si;

    public MyTabEntitie(String title, int ui, int si) {
        this.title = title;
        this.ui = ui;
        this.si = si;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return si;
    }

    @Override
    public int getTabUnselectedIcon() {
        return ui;
    }
}
