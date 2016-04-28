package com.example.mysmall;

import android.util.Log;

import net.wequick.small.Small;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Small.setBaseUri("http://m.wequick.net/demo/");
        Small.setBaseUri("http://example.com/");
        // Required
        Small.preSetUp(this);
    }
}
