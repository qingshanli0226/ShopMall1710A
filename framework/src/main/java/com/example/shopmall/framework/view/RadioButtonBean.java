package com.example.shopmall.framework.view;

public class RadioButtonBean {
    private String title;
    private int selectImg = 0;
    private int unSelectImg = 0;

    public RadioButtonBean() {
    }

    public RadioButtonBean(String title, int selectImg, int unSelectImg) {
        this.title = title;
        this.selectImg = selectImg;
        this.unSelectImg = unSelectImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelectImg() {
        return selectImg;
    }

    public void setSelectImg(int selectImg) {
        this.selectImg = selectImg;
    }

    public int getUnSelectImg() {
        return unSelectImg;
    }

    public void setUnSelectImg(int unSelectImg) {
        this.unSelectImg = unSelectImg;
    }

    @Override
    public String toString() {
        return "RadioButtonBean{" +
                "title='" + title + '\'' +
                ", selectImg=" + selectImg +
                ", unSelectImg=" + unSelectImg +
                '}';
    }
}
