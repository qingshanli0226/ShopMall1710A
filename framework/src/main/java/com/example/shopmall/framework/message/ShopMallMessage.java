package com.example.shopmall.framework.message;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopMallMessage {
    @Id(autoincrement = true)
    private Long id;

    //基础消息
    private String type;//消息类型 "PUSH" "ZHIFU" "POINT"
    private String title;//消息头
    private String content;//消息内容
    private long time;//时间戳
    private boolean isRead;

    //推送消息字段
    private String productId;
    private String productPrice;
    private String productName;
    private String productImageUrl;

    //支付消息字段
    private String outTradeNo;//订单号
    private String totalPrice;

    //积分消息字段
    private String addPoint;//增加的积分数
    private String steps;//当天跑的步数
    @Generated(hash = 1266477387)
    public ShopMallMessage(Long id, String type, String title, String content,
            long time, boolean isRead, String productId, String productPrice,
            String productName, String productImageUrl, String outTradeNo,
            String totalPrice, String addPoint, String steps) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isRead = isRead;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.outTradeNo = outTradeNo;
        this.totalPrice = totalPrice;
        this.addPoint = addPoint;
        this.steps = steps;
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
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getProductId() {
        return this.productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductPrice() {
        return this.productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductImageUrl() {
        return this.productImageUrl;
    }
    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
    public String getOutTradeNo() {
        return this.outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getAddPoint() {
        return this.addPoint;
    }
    public void setAddPoint(String addPoint) {
        this.addPoint = addPoint;
    }
    public String getSteps() {
        return this.steps;
    }
    public void setSteps(String steps) {
        this.steps = steps;
    }
    public boolean getIsRead() {
        return this.isRead;
    }
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

}
