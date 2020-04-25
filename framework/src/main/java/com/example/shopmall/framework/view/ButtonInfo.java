package com.example.shopmall.framework.view;

public class ButtonInfo {
    private String title;
    private int selectPic = 0;
    private int unSelectPic = 0;

    public ButtonInfo(String title, int unSelectPic) {
        this.title = title;
        this.unSelectPic = unSelectPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelectPic() {
        return selectPic;
    }

    public void setSelectPic(int selectPic) {
        this.selectPic = selectPic;
    }

    public int getUnSelectPic() {
        return unSelectPic;
    }

    public void setUnSelectPic(int unSelectPic) {
        this.unSelectPic = unSelectPic;
    }

    public ButtonInfo(String title, int unSelectPic, int selectPic) {
        this.title = title;
        this.selectPic = selectPic;
        this.unSelectPic = unSelectPic;
    }
}
