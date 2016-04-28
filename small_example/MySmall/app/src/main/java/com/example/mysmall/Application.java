package com.example.mysmall;

import android.util.Log;

import net.wequick.small.Small;

/**
 * Created by galen on 15/11/3.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Options
        //Small.setBaseUri("http://m.wequick.net/demo/");
        Small.setBaseUri("http://example.com/");
        Log.d("locald","preSetUp ...");
        // Required
        Small.preSetUp(this);
    }
}
