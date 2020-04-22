package com.example.shopmall.shopmall1710a.home.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    private String title;
    private int select;
    private int unSelect;

    public TabEntity(String title, int select, int unSelect) {
        this.title = title;
        this.select = select;
        this.unSelect = unSelect;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return select;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelect;
    }
}
