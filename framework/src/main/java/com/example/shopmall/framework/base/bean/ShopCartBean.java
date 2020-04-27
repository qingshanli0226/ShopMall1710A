package com.example.shopmall.framework.base.bean;

import java.util.List;

public class ShopCartBean {

    private String code;
    private String message;
    private List<ResultBean> result;

    public ShopCartBean(String code, String message, List<ResultBean> result) {
        this.code = code;
        this.message = message;
        this.result = result;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * productId : 1512
         * productName : 琛¬琛«
         * productNum : 2
         * url : http://www.baidu.com
         * productPrice : 20
         */

        private String productId;
        private String productName;
        private String productNum;
        private String url;
        private String productPrice;

        public ResultBean(String productId, String productName, String productNum, String url, String productPrice) {
            this.productId = productId;
            this.productName = productName;
            this.productNum = productNum;
            this.url = url;
            this.productPrice = productPrice;
        }

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
    }
}
