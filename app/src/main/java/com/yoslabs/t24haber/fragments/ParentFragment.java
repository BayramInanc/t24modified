package com.yoslabs.t24haber.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yoslabs.t24haber.R;
import com.yoslabs.t24haber.services.ServiceReceiver;
import com.yoslabs.t24haber.services.SyncService;
import com.yoslabs.t24haber.utils.SwipeRefreshLoading;

import java.net.URLEncoder;


public abstract class ParentFragment extends Fragment implements ServiceReceiver.Listener {

    /** layout resource for fragment **/
    public abstract int getLayoutResource();

    /** get toolbar title **/
    public abstract int getToolbarTitle();

    /** load results **/
    public abstract void loadResults(int resultCode, String getData);

    private Activity activity;
    private SwipeRefreshLoading refreshLayout;
    private ServiceReceiver serviceReceiver;

    /** show some toast **/
    public void showToast(int stringRes) {
        Toast.makeText(activity, stringRes, Toast.LENGTH_LONG).show();
    }

    /** show some toast **/
    public void showToast(String toastValue) {
        Toast.makeText(activity, toastValue, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(getLayoutResource(), container, false);
        activity = getActivity();
        refreshLayout = (SwipeRefreshLoading) mView.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.ColorPrimary);
        serviceReceiver = new ServiceReceiver(new Handler());
        serviceReceiver.setListener(this);
        return mView;
    }

    public SwipeRefreshLoading getRefreshLayout() {
        return refreshLayout;
    }

    /** get activity **/
    public Activity getActivityFromParent() { return activity; }

    /** encode url **/
    public String urlEncoder(String stringToEncode) {
        String returnEncoded = "";
        try { returnEncoded = URLEncoder.encode(stringToEncode, "utf-8"); }
        catch (Exception e) {e.printStackTrace(); }
        return returnEncoded;
    }

    /** sync datas **/
    public Intent serviceForSync(String url, int resultCode) {
        startRefreshing();
        Intent intent = new Intent(activity, SyncService.class);
        intent.putExtra("url", url);
        intent.putExtra("resultCode", resultCode);
        intent.putExtra("receiver", serviceReceiver);
        activity.startService(intent);
        return intent;
    }

    /** gets the result of sync **/
    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if(resultData != null && resultData.getString("getData") != null)
            loadResults(resultCode, resultData.getString("getData"));
        stopRefreshing();
    }

    /** get string from resource **/
    public String getStringFromResource(int resourceId) {
        return activity.getString(resourceId);
    }

    /** destroy some view **/
    @Override
    public void onDestroyView() {
        super.onDestroy();
        if (serviceReceiver != null) serviceReceiver.setListener(null);
        stopRefreshing();
    }

    public void startRefreshing() {
        if (!refreshLayout.isRefreshing()) refreshLayout.setRefreshing(true);
    }

    public void stopRefreshing() {
        if (refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
    }

}
