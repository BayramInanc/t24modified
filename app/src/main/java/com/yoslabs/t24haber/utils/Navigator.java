package com.yoslabs.t24haber.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.yoslabs.t24haber.R;
import com.yoslabs.t24haber.fragments.NoConnectionFragment;

public class Navigator {

    private FragmentManager mFragmentManager;
    private AppCompatActivity mActivity;

    public Navigator(FragmentManager fragmentManager, AppCompatActivity activity) {
        mFragmentManager = fragmentManager;
        mActivity = activity;
    }

    public void startFragmentTransaction(Fragment fragment, boolean addToBackStack, boolean isReplaceOrAdd) {
        if (!isNetworkAvailable()) fragment = new NoConnectionFragment();

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        /** replace or add ? **/
        if (isReplaceOrAdd) transaction.replace(R.id.appframe, fragment);
        else transaction.add(R.id.appframe, fragment);

        /** add to back stack ? **/
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
