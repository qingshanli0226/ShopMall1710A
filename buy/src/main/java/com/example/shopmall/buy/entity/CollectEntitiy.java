package com.example.shopmall.buy.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CollectEntitiy {
    private String price;
    private String name;
    private String img;
    @Generated(hash = 592338216)
    public CollectEntitiy(String price, String name, String img) {
        this.price = price;
        this.name = name;
        this.img = img;
    }
    @Generated(hash = 179039275)
    public CollectEntitiy() {
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }

}
