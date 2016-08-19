package com.yoslabs.t24haber.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.yoslabs.t24haber.NewsActivity;
import com.yoslabs.t24haber.R;

public class NoConnectionFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_connectagain, container, false);
        Button connectAgainBt = (Button) mView.findViewById(R.id.connectAgainBt);
        connectAgainBt.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connectAgainBt:
                ((NewsActivity) getActivity()).startTransactionFromActivity(new MainNewsFragment(), false, true);
                break;
        }
    }

}
