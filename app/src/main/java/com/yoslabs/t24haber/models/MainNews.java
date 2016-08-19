package com.yoslabs.t24haber.models;

import java.util.ArrayList;
import java.util.HashMap;

public class MainNews {

    /**
     * main news model for main page news
     **/

    private ArrayList<News> data;

    public ArrayList<News> getData() {
        return data;
    }

    public static class News {

        private String id, title, publishingDate, excerpt;

        private HashMap<String, String> images;

        private HashMap<String, String> category;

        private boolean isFooter;

        public News(boolean isFooter) {
            this.isFooter = isFooter;
        }

        public boolean isFooter() {
            return isFooter;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getExcerpt(){return excerpt; }

        public String getPublishingDate() {
            return publishingDate;
        }

        public HashMap<String, String> getImages() {
            return images;
        }

        public HashMap<String, String> getCategory() {
            return category;
        }

    }

}
