package com.yoslabs.t24haber.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.yoslabs.t24haber.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableDrawerAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> drawerTitles;
    private HashMap<String,ArrayList<String>> drawerChildTitles;


    public ExpandableDrawerAdapter(Context context, List<String> drawerTitles, HashMap<String,ArrayList<String>> drawerChildTitles){
        this.context=context;
        this.drawerTitles=drawerTitles;
        this.drawerChildTitles=drawerChildTitles;
    }

    @Override
    public int getGroupCount() {

        if(this.drawerTitles!=null)
            return this.drawerTitles.size();
        else return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        if(drawerTitles!=null && drawerChildTitles.get(this.drawerTitles.get(i))!=null )
            return this.drawerChildTitles.get(this.drawerTitles.get(i)).size();
        else
            return 0;

    }

    @Override
    public Object getGroup(int i) {
        return this.drawerTitles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.drawerChildTitles.get(this.drawerTitles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        final NewsAdapter.ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_parent,null);
        }

        MaterialLetterIcon icon = (MaterialLetterIcon) convertView.findViewById(R.id.drawerParentIcon);
        TextView          title = (TextView) convertView.findViewById(R.id.drawerParentTextView);
        LinearLayout     layout = (LinearLayout) convertView.findViewById(R.id.drawerParentLayout);

        title.setText(headerTitle);

        icon.setShapeType(MaterialLetterIcon.Shape.ROUND_RECT);
        icon.setRoundRectRx(5);
        icon.setRoundRectRy(5);
        icon.setLettersNumber(0);
        icon.setLetterSize(10);


    if(isExpanded)
        layout.setBackgroundColor(convertView.getResources().getColor(R.color.cs));
     else
        layout.setBackgroundColor(convertView.getResources().getColor(R.color.white));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_item,null);

        }
        MaterialLetterIcon icon = (MaterialLetterIcon) convertView.findViewById(R.id.drawerIcon);
        TextView          title = (TextView)           convertView.findViewById(R.id.drawerTv);

        title.setText(childText);
        String twoLetters = childText.substring(0,2).toUpperCase();
        icon.setShapeType(MaterialLetterIcon.Shape.ROUND_RECT);
        icon.setRoundRectRx(15);
        icon.setRoundRectRy(15);
        icon.setLettersNumber(2);
        icon.setLetter(twoLetters);
        icon.setLetterSize(10);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
