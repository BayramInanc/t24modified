package com.yoslabs.t24haber.utils;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by mkemaltas on 11.08.2016.
 */
public class DrawerTitleFactory {

    ArrayList<String> titles;

    public DrawerTitleFactory(){
        titles = new ArrayList<String>();
    }

    public ArrayList<String> Fill(){
        titles.add(0,"Kategoriler");
        titles.add(1, "Köşe Yazarları");
        titles.add(2, "En çok okunanlar");
        titles.add(3, "Daha nelerneler");
        return titles;
    }

}
