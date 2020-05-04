package com.example.shopmall.shopmall1710a.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Product {
    @Id
    private long id;
    private String name;
    @Generated(hash = 1781280952)
    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1890278724)
    public Product() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
