package com.yoslabs.t24haber.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yoslabs.t24haber.R;
import com.yoslabs.t24haber.models.MainNews;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

	private Activity mActivity;
	private LayoutInflater inflater;
	private ArrayList<NewsViewPagerData> mNewsDatas = new ArrayList<>();

	public ViewPagerAdapter(Activity activity, ArrayList<MainNews.News> datas) {
		mActivity = activity;
//		mNewsPics = newsPics;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for (MainNews.News data:datas)
			if(data.getImages() != null && data.getImages().get("page") != null) mNewsDatas.add(new NewsViewPagerData("http://" + data.getImages().get("page"), Html.fromHtml(data.getTitle()).toString()));
	}

	@Override
	public int getCount() {
		return mNewsDatas.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View itemView = inflater.inflate(R.layout.viewpager_news_item, container, false);
		NewsViewPagerData newsData = mNewsDatas.get(position);
		TextView newsTitleItemTv = (TextView) itemView.findViewById(R.id.newsTitleItemTv);
		ImageView newsPicsIv = (ImageView) itemView.findViewById(R.id.newsPicsIv);
		newsPicsIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {


			}
		});
		Glide.with(mActivity).load(newsData.getNewsPicUrl()).fitCenter().centerCrop().into(newsPicsIv);
		container.addView(itemView);
		newsTitleItemTv.setText(newsData.getNewsTitle());
		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LinearLayout) object);
	}

	class NewsViewPagerData {

		private String newsPicUrl, newsTitle;

		public NewsViewPagerData(String newsPicUrl, String newsTitle) {
			this.newsPicUrl = newsPicUrl;
			this.newsTitle = newsTitle;
		}

		public String getNewsPicUrl() {
			return newsPicUrl;
		}

		public String getNewsTitle() {
			return newsTitle;
		}

	}

}
