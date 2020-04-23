package com.example.shopmall.shopmall1710a.main.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyTab implements CustomTabEntity {

    private String name;
    private int res1;
    private int res2;

    public MyTab(String name, int res1, int res2) {
        this.name = name;
        this.res1 = res1;
        this.res2 = res2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes1() {
        return res1;
    }

    public void setRes1(int res1) {
        this.res1 = res1;
    }

    public int getRes2() {
        return res2;
    }

    public void setRes2(int res2) {
        this.res2 = res2;
    }

    @Override
    public String getTabTitle() {
        return name;
    }

    @Override
    public int getTabSelectedIcon() {
        return res1;
    }

    @Override
    public int getTabUnselectedIcon() {
        return res2;
    }
}
