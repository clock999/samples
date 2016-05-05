package com.example.mysmall.app.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mylib.MyLib;
import com.example.mylib2.MyLib2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLib.test();
        Log.d("locald", "mylib2 test " + MyLib2.test());
    }
}
