package com.example.shopmall.framework.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopMallMessage {
    @Id(autoincrement = true)
    private Long id;

    private String type;
    private String content;
    @Generated(hash = 611109753)
    public ShopMallMessage(Long id, String type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }
    @Generated(hash = 985301536)
    public ShopMallMessage() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
