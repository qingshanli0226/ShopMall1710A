package com.example.shopmall.framework.customView.bean;

public class BottomBean {
    private String title; // 标题
    private int pitch; // 选中 图标
    private int selected; // 位选中 图标

    public BottomBean(String title, int pitch, int selected) {
        this.title = title;
        this.pitch = pitch;
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
