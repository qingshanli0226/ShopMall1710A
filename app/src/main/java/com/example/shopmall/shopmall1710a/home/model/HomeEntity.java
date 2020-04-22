package com.example.shopmall.shopmall1710a.home.model;


public class HomeEntity {

    /**
     * code : 200
     * message : 请求成功
     * result : [{"name":"小裙子","url":"SKIRT_URL.json"},{"name":"夹克衫","url":"JACKET_URL.json"},{"name":"包包","url":"BAG_URL.json"}]
     */



        /**
         * name : 小裙子
         * url : SKIRT_URL.json
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

}
