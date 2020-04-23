package com.example.shopmall.shopmall1710a;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEnitity implements CustomTabEntity {
    private String tabTitle;
    private int tabSelectedIcon;
    private int tabUnselectedIcon;

    public TabEnitity(String tabTitle, int tabUnselectedIcon, int  tabSelectedIcon) {
        this.tabTitle = tabTitle;
        this.tabSelectedIcon = tabSelectedIcon;
        this.tabUnselectedIcon = tabUnselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return tabSelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return tabUnselectedIcon;
    }
}
