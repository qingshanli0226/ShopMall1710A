package com.example.shopmall.framework.bean;

import java.util.List;

public class ShopCartBean {


    /**
     * code : 200
     * message : 请求成功
     * result : [{"productId":"9356","productName":"现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪","productNum":"1","url":"/1477984921265.jpg","productPrice":"159.00","productSelected":true},{"productId":"10136","productName":"【高冷猫】暗黑系软妹病娇药丸少女秋装假俩件加厚卫衣帽衫  预售","productNum":"5","url":"/1477360350123.png","productPrice":"143.10","productSelected":true},{"productId":"9414","productName":"【现货】【GIRLISM少女主义】 第4期 2016夏秋刊 lolita","productNum":"1","url":"/1474370572805.jpg","productPrice":"70.00","productSelected":true}]
     */

    private String code;
    private String message;
    private List<ShopcarData> result;

    public void add(ShopcarData shopcarData) {
        result.add(shopcarData);
    }

    public void remove(ShopcarData shopcarData) {
        if (result.contains(shopcarData)) {
            result.remove(shopcarData);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ShopcarData> getResult() {
        return result;
    }

    public void setResult(List<ShopcarData> result) {
        this.result = result;
    }

    public static class ShopcarData {
        /**
         * productId : 9356
         * productName : 现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪
         * productNum : 1
         * url : /1477984921265.jpg
         * productPrice : 159.00
         * productSelected : true
         */

        private String productId;
        private String productName;
        private String productNum;
        private String url;
        private String productPrice;
        private boolean productSelected;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public boolean isProductSelected() {
            return productSelected;
        }

        public void setProductSelected(boolean productSelected) {
            this.productSelected = productSelected;
        }
    }
}
