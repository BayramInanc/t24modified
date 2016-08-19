package com.yoslabs.t24haber.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yoslabs.t24haber.R;
import com.yoslabs.t24haber.models.NewsPage;


public class NewsFragment extends ParentFragment implements SwipeRefreshLayout.OnRefreshListener {

    private Gson gson = new Gson();
    private TextView pageContentTv, newsTitleTv;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView newsPageIv;
    private ImageButton shareButton;
    private LinearLayout shareLayout;
    private String shareUrl = "";





    @Override
    public int getLayoutResource() {
        return R.layout.fragment_news;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.news;
    }

    @Override
    public void loadResults(int resultCode, String getData) {
        if (resultCode == 0) {

            NewsPage mainNews = gson.fromJson(getData, NewsPage.class);

            if(mainNews != null && mainNews.getData() != null && mainNews.getData().getTitle() != null) {


                NewsPage.NewsPageData newsPageData = mainNews.getData();

                /** news content **/
                pageContentTv.setText(Html.fromHtml(newsPageData.getText()));

                /** set title of collapsing toolbar **/
                collapsingToolbarLayout.setTitle(Html.fromHtml(newsPageData.getTitle()));

                /** news title **/
                newsTitleTv.setText(Html.fromHtml(newsPageData.getTitle()));

                /** place img **/
                Glide.with(getActivityFromParent())
                        .load("http:" + newsPageData.getImages().get("page"))
                        .fitCenter().centerCrop().into(newsPageIv);

                /** share url for page **/
                shareUrl = newsPageData.getUrls().get("web");

            } else {
                newsTitleTv.setText(R.string.datanotfound);
                pageContentTv.setText(R.string.datanot);
            }

        }
    }





    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

       /* Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolBar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);*/

        /** home set as up **/
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(true);




        newsPageIv              = (ImageView) view.findViewById(R.id.newsPageIv);
        pageContentTv           = (TextView) view.findViewById(R.id.pageContentTv);
        newsTitleTv             = (TextView) view.findViewById(R.id.newsTitleTv);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsingToolbarLayout);
        shareButton             = (ImageButton) view.findViewById(R.id.shareButton);
        shareLayout             = (LinearLayout) view.findViewById(R.id.shareNewsLayout);

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!shareUrl.equals("")) shareTextUrl(shareUrl);
            }
        });
        shareLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    shareLayout.setBackgroundColor(getResources().getColor((R.color.white)));
                    shareButton.setBackgroundColor(getResources().getColor((R.color.white)));
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    shareLayout.setBackgroundColor(getResources().getColor((R.color.ColorPrimaryDark)));
                    shareButton.setBackgroundColor(getResources().getColor((R.color.ColorPrimaryDark)));
                }
                return false;
            }
        });





        /** get arg of news id **/
        Bundle bundleArgs = getArguments();
        if (bundleArgs  != null && bundleArgs.containsKey("newsId"))  {
            String newsId = bundleArgs.getString("newsId");
            serviceForSync(getStringFromResource(R.string.getnewspage) + newsId, 0);
        }

        /** set listener for refresh layout **/
        getRefreshLayout().setOnRefreshListener(this);



    }

    /** create options for fragment **/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /** share option is clicked **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    /** share news url **/
    private void shareTextUrl(String urlToShare) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.putExtra(Intent.EXTRA_SUBJECT, "Haber Linki");
        share.putExtra(Intent.EXTRA_TEXT, urlToShare);
        startActivity(Intent.createChooser(share, "Haberi Paylaş"));
    }

    /** when refreshed **/
    @Override
    public void onRefresh() {
        getRefreshLayout().setRefreshing(false);
    }

    public static NewsFragment newInstance(String newsId) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle b = new Bundle();
        b.putString("newsId", newsId);
        newsFragment.setArguments(b);
        return newsFragment;
    }

}
