package com.yoslabs.t24haber.models;

import java.util.HashMap;

public class NewsPage {

    /** page of news **/
    public NewsPageData data;

    public NewsPageData getData() {
        return data;
    }

    public class NewsPageData {

        public String text, title;
        private HashMap<String, String> images;
        private HashMap<String, String> urls;

        public String getText() {
            return text;
        }

        public String getTitle() {
            return title;
        }

        public HashMap<String, String> getImages() {
            return images;
        }

        public HashMap<String, String> getUrls() { return urls; }

    }

}
