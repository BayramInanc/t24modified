package com.yoslabs.t24haber.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.yoslabs.t24haber.utils.GetData;

public class SyncService extends IntentService {

    public SyncService() {
        super("Sync Service");
    }

    /** service to sync data **/
    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String completeUrl = intent.getStringExtra("url");
        int resultCode = intent.getIntExtra("resultCode", 0);
        String getData = new GetData().run(completeUrl);
        Bundle b = new Bundle();
        b.putString("getData", getData);
        receiver.send(resultCode, b);
    }

}
