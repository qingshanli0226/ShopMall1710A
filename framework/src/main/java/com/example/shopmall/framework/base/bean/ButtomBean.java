package com.example.shopmall.framework.base.bean;

public class ButtomBean {
    private String title;
    private int pitch;
    private int selected;

    public ButtomBean(String title, int pitch, int selected) {
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
