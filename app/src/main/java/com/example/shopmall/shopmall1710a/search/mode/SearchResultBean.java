package com.example.shopmall.shopmall1710a.search.mode;

import java.util.List;

public class SearchResultBean {

    /**
     * p_catalog_id : 69
     * parent_id : 0
     * name : 配件
     * pic :
     * is_deleted : 0
     * child : [{"p_catalog_id":"70","parent_id":"69","name":"帽子","pic":"/product_catalog/1446017867549.jpg","is_deleted":"0"},{"p_catalog_id":"105","parent_id":"69","name":"围巾","pic":"/product_catalog/1461642090192.jpg","is_deleted":"0"},{"p_catalog_id":"71","parent_id":"69","name":"配饰","pic":"/product_catalog/1446017886090.jpg","is_deleted":"0"},{"p_catalog_id":"72","parent_id":"69","name":"手表","pic":"/product_catalog/1446017897843.jpg","is_deleted":"0"},{"p_catalog_id":"73","parent_id":"69","name":"首饰","pic":"/product_catalog/1446017920333.jpg","is_deleted":"0"},{"p_catalog_id":"74","parent_id":"69","name":"鞋子","pic":"/product_catalog/1446017931323.jpg","is_deleted":"0"},{"p_catalog_id":"84","parent_id":"69","name":"袜子","pic":"/product_catalog/1446017947046.jpg","is_deleted":"0"}]
     * hot_product_list : [{"product_id":"8312","channel_id":"3","brand_id":"72","p_catalog_id":"71","supplier_type":"1","supplier_code":"300011","name":"【艾漫】全职高手-夏日水手服徽章套","cover_price":"30.00","brief":"到货啦~","figure":"/1469184599346.jpg","sell_time_start":"1469116800","sell_time_end":"1469721600"},{"product_id":"3831","channel_id":"8","brand_id":"429","p_catalog_id":"84","supplier_type":"1","supplier_code":"1101035","name":"【喵鹿酱】超萌 假透肉 拼接 踩脚过膝打底袜 裤袜-加绒保暖","cover_price":"29.00","brief":"","figure":"/1452161899947.jpg","sell_time_start":"1477563362","sell_time_end":"1452182400"},{"product_id":"1969","channel_id":"6","brand_id":"80","p_catalog_id":"71","supplier_type":"1","supplier_code":"1101037","name":"【画影】古风 头饰 Lolita 羊尾巴 装饰毛钱流苏发夹","cover_price":"12.00","brief":"","figure":"/1443527992618.jpg","sell_time_start":"1443456000","sell_time_end":"1444060800"},{"product_id":"3845","channel_id":"8","brand_id":"183","p_catalog_id":"70","supplier_type":"2","supplier_code":"100003","name":"预售 【世界线的彼岸】炸鸡块 颜文字 羊毛呢贝雷帽 圆润烧饼帽 保暖","cover_price":"49.00","brief":"","figure":"/1451967280855.jpg","sell_time_start":"1451923200","sell_time_end":"1452528000"},{"product_id":"1752","channel_id":"12","brand_id":"3","p_catalog_id":"73","supplier_type":"1","supplier_code":"0","name":"剑网3门派衍生同人原创手链 ","cover_price":"68.00","brief":"","figure":"/1450754469244.jpg","sell_time_start":"1443024000","sell_time_end":"1443628800"},{"product_id":"4947","channel_id":"8","brand_id":"5","p_catalog_id":"71","supplier_type":"2","supplier_code":"1101026","name":"【USEE哟喜】 原创纹身贴防水 黑白彩色持久男女森女蝴蝶鹿 特惠套装","cover_price":"22.00","brief":"","figure":"/1467361365770.jpg","sell_time_start":"1456848000","sell_time_end":"1457452800"}]
     */

    private String p_catalog_id;
    private String parent_id;
    private String name;
    private String pic;
    private String is_deleted;
    private List<ChildBean> child;
    private List<HotProductListBean> hot_product_list;

    public String getP_catalog_id() {
        return p_catalog_id;
    }

    public void setP_catalog_id(String p_catalog_id) {
        this.p_catalog_id = p_catalog_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    public List<HotProductListBean> getHot_product_list() {
        return hot_product_list;
    }

    public void setHot_product_list(List<HotProductListBean> hot_product_list) {
        this.hot_product_list = hot_product_list;
    }

    public static class ChildBean {
        /**
         * p_catalog_id : 70
         * parent_id : 69
         * name : 帽子
         * pic : /product_catalog/1446017867549.jpg
         * is_deleted : 0
         */

        private String p_catalog_id;
        private String parent_id;
        private String name;
        private String pic;
        private String is_deleted;

        public String getP_catalog_id() {
            return p_catalog_id;
        }

        public void setP_catalog_id(String p_catalog_id) {
            this.p_catalog_id = p_catalog_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }
    }

    public static class HotProductListBean {
        /**
         * product_id : 8312
         * channel_id : 3
         * brand_id : 72
         * p_catalog_id : 71
         * supplier_type : 1
         * supplier_code : 300011
         * name : 【艾漫】全职高手-夏日水手服徽章套
         * cover_price : 30.00
         * brief : 到货啦~
         * figure : /1469184599346.jpg
         * sell_time_start : 1469116800
         * sell_time_end : 1469721600
         */

        private String product_id;
        private String channel_id;
        private String brand_id;
        private String p_catalog_id;
        private String supplier_type;
        private String supplier_code;
        private String name;
        private String cover_price;
        private String brief;
        private String figure;
        private String sell_time_start;
        private String sell_time_end;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getP_catalog_id() {
            return p_catalog_id;
        }

        public void setP_catalog_id(String p_catalog_id) {
            this.p_catalog_id = p_catalog_id;
        }

        public String getSupplier_type() {
            return supplier_type;
        }

        public void setSupplier_type(String supplier_type) {
            this.supplier_type = supplier_type;
        }

        public String getSupplier_code() {
            return supplier_code;
        }

        public void setSupplier_code(String supplier_code) {
            this.supplier_code = supplier_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover_price() {
            return cover_price;
        }

        public void setCover_price(String cover_price) {
            this.cover_price = cover_price;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getFigure() {
            return figure;
        }

        public void setFigure(String figure) {
            this.figure = figure;
        }

        public String getSell_time_start() {
            return sell_time_start;
        }

        public void setSell_time_start(String sell_time_start) {
            this.sell_time_start = sell_time_start;
        }

        public String getSell_time_end() {
            return sell_time_end;
        }

        public void setSell_time_end(String sell_time_end) {
            this.sell_time_end = sell_time_end;
        }
    }
}
