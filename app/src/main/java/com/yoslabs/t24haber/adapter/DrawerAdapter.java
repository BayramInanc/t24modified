package com.yoslabs.t24haber.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.yoslabs.t24haber.R;
import com.yoslabs.t24haber.models.NewsCats;

import java.util.ArrayList;

public class DrawerAdapter extends ArrayAdapter<NewsCats.NewsCat> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<NewsCats.NewsCat> newsCats;

    /** draw items of navigation drawer **/
    public DrawerAdapter(Context context, int layoutResourceId, ArrayList<NewsCats.NewsCat> newsCats) {
        super(context, layoutResourceId, newsCats);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.newsCats = newsCats;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(layoutResourceId, parent, false);

        TextView drawerTitleTv = (TextView) convertView.findViewById(R.id.drawerTv);


        String twoLetters = newsCats.get(position).getName().substring(0,2).toUpperCase();
        MaterialLetterIcon icon = (MaterialLetterIcon) convertView.findViewById(R.id.drawerIcon);
        icon.setShapeType(MaterialLetterIcon.Shape.ROUND_RECT);
        icon.setRoundRectRx(15);
        icon.setRoundRectRy(15);
        icon.setLettersNumber(2);
        icon.setLetter(twoLetters);
        icon.setLetterSize(10);



        drawerTitleTv.setText(newsCats.get(position).getName());

        return convertView;
    }

}
