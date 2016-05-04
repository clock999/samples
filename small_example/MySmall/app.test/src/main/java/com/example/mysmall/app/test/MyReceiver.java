package com.example.mysmall.app.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private final String MyReceiver_ACTION = "MyReceiver123";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("locald"," MyReceiver onReceive 0 ");
        if (intent.getAction().equals(MyReceiver_ACTION)) {
            Log.e("locald"," MyReceiver onReceive ");
        }
    }
}
