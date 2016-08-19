package com.yoslabs.t24haber.models;

import java.util.ArrayList;

public class NewsCats {

    /** categories of news **/
    public ArrayList<NewsCat> data;

    public ArrayList<NewsCat> getData() {
        return data;
    }
    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();
        for(int i = 0; i < data.size(); i++)
            names.add(i,data.get(i).getName());
        return names;
    }

    public class NewsCat {

        private String id, name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

}
