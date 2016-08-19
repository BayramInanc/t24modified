package com.yoslabs.t24haber.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoslabs.t24haber.R;

import java.util.ArrayList;

public class NewsPagerFragment extends Fragment {

    private int limit = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_newspager, container, false);

        /** get arg of news id **/
        Bundle bundleArgs = getArguments();
        if (bundleArgs != null && bundleArgs.containsKey("newsId")) {
            String newsId = bundleArgs.getString("newsId");
            limit = bundleArgs.getInt("limit");
            ViewPager pager = (ViewPager) mView.findViewById(R.id.viewPager);
            pager.setAdapter(new NewsPagerAdapter(getChildFragmentManager()));
            pager.setCurrentItem(Integer.parseInt(newsId), false);
        }

        return mView;
    }

    private class NewsPagerAdapter extends FragmentStatePagerAdapter {

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            return NewsFragment.newInstance("" + pos);
        }

        @Override
        public int getCount() {
            return limit;
        }

    }

}
