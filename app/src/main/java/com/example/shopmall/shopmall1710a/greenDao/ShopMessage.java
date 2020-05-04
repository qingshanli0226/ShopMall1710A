package com.example.shopmall.shopmall1710a.greenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopMessage {
   String productImageUrl;
   String productPrice;
   String productId;
   String productName;
   @Generated(hash = 411648939)
   public ShopMessage(String productImageUrl, String productPrice,
           String productId, String productName) {
       this.productImageUrl = productImageUrl;
       this.productPrice = productPrice;
       this.productId = productId;
       this.productName = productName;
   }
   @Generated(hash = 691502139)
   public ShopMessage() {
   }
   public String getProductImageUrl() {
       return this.productImageUrl;
   }
   public void setProductImageUrl(String productImageUrl) {
       this.productImageUrl = productImageUrl;
   }
   public String getProductPrice() {
       return this.productPrice;
   }
   public void setProductPrice(String productPrice) {
       this.productPrice = productPrice;
   }
   public String getProductId() {
       return this.productId;
   }
   public void setProductId(String productId) {
       this.productId = productId;
   }
   public String getProductName() {
       return this.productName;
   }
   public void setProductName(String productName) {
       this.productName = productName;
   }

   
}
