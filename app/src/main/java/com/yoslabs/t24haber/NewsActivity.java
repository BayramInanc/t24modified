package com.yoslabs.t24haber;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.yoslabs.t24haber.adapter.ExpandableDrawerAdapter;
import com.yoslabs.t24haber.fragments.MainNewsFragment;
import com.yoslabs.t24haber.models.NewsCats;
import com.yoslabs.t24haber.utils.Navigator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsActivity extends AppCompatActivity /*implements ListView.OnItemClickListener*/{

    private ExpandableListView expandableDataListView;
    private ListView dataListView;
    private DrawerLayout drawerLayout = null;
    private ArrayList<NewsCats.NewsCat> mNewsCats;
    InterstitialAd mInterstitialAd;
    private int adCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();

            }
        });

        requestNewInterstitial();



        //dataListView = (ListView) findViewById(R.id.dataListView);
        expandableDataListView = (ExpandableListView) findViewById(R.id.dataListView);
        ViewGroup drawerHeader = (ViewGroup)getLayoutInflater().inflate(R.layout.drawer_header, expandableDataListView,false);
        expandableDataListView.addHeaderView(drawerHeader);
        /** start transaction for main news fragment **/
        startTransactionFromActivity(new MainNewsFragment(), false, true);

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("D9XXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }



    public void startTransactionFromActivity(Fragment fragment, boolean addToBackStack, boolean isReplaceOrAdd) {
        /** start some transaction **/
        Navigator navigator = new Navigator(getSupportFragmentManager(), this);
        navigator.startFragmentTransaction(fragment, addToBackStack, isReplaceOrAdd);
    }

    /** set drawer layout **/
    public void setDrawer(Toolbar toolbar) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    /** set drawer layouts data **/
   /* public void setDrawerData(ArrayList<NewsCats.NewsCat> newsCats) {
        mNewsCats = newsCats;
        dataListView.setAdapter(new DrawerAdapter(this, R.layout.drawer_item, newsCats));
        dataListView.setOnItemClickListener(this);
    }*/


    public void setDrawerData(List<String> titles, HashMap<String,ArrayList<String>> children, ArrayList<NewsCats.NewsCat> newsCats) {

        mNewsCats = newsCats;
        ArrayList<String> categories =  children.get("Kategoriler");
        ExpandableDrawerAdapter adapter = new ExpandableDrawerAdapter(this,titles,children);




        expandableDataListView.setAdapter(adapter);
        expandableDataListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    openNewsPage(mNewsCats.get(childPosition).getId(), childPosition, mNewsCats.get(childPosition).getName());
                    if(drawerLayout != null) drawerLayout.closeDrawer(expandableDataListView);

                return false;
            }
        });
        expandableDataListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                ExpandableDrawerAdapter innerAdapter = (ExpandableDrawerAdapter) expandableDataListView.getExpandableListAdapter();
                if(innerAdapter.getChildrenCount(i) == 0) {

                    Snackbar snackbar = Snackbar.make(drawerLayout,"Yok ki :(", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryTransDark));
                    expandableDataListView.collapseGroup(i);
                }

            }
        });

    }





    /** up touched **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** when pressed back **/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    /** item click on drawers layout **/
  /*  @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        openNewsPage(mNewsCats.get(position).getId(), position, mNewsCats.get(position).getName());

        if(drawerLayout != null) drawerLayout.closeDrawer(dataListView);

    }*/

    /** check item in drawer layyout **/
    public void setItemChecked(int position) {
        //dataListView.setItemChecked(position, true);
    }

    /** open news page **/
    private void openNewsPage(String catId, int position, String name) {

        if(mInterstitialAd.isLoaded() && adCounter % 3 == 0)
            mInterstitialAd.show();
        adCounter++;
        Fragment newsFragment = new MainNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("catId", catId);
        bundle.putInt("position", position);
        bundle.putString("name", name);
        newsFragment.setArguments(bundle);
        startTransactionFromActivity(newsFragment, false, true);
    }


}
