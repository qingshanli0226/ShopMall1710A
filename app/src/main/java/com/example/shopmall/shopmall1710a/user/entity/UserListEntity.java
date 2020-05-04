package com.example.shopmall.shopmall1710a.user.entity;

import org.w3c.dom.Text;

public class UserListEntity {
    private int img;
    private String text;

    public UserListEntity(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
