package com.example.mysmall.app.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mylib.MyLib;
import com.example.mysmall.lib.utils.LibUtils;

import com.example.mysmall.lib.utils.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mysmall.app.main.R.layout.activity_main);
        MyLib.test();
        Log.d("locald", "app.main oncreate ..");
        LibUtils.test();
    }
}
