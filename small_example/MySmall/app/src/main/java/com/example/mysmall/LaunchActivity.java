package com.example.mysmall;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import net.wequick.small.Small;

import com.example.mylib.MyLib;
import com.example.mylib2.MyLib2;

public class LaunchActivity extends Activity {
    private View mContentView;
    private View mControlsView;
    private Button mButton1;
    private Button mButton2;
    private Button mUpdate;
    private Button mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Small.setBaseUri("http://example.com/");
        Small.setUp(this, new net.wequick.small.Small.OnCompleteListener() {
            @Override
            public void onComplete() {
            }
        });
    }

    private void initUI() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Small.openUri("main", LaunchActivity.this);
            }
        });

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("locald","button2 onclick ..");
                Small.openUri("test", LaunchActivity.this);
            }
        });

        mUpdate = (Button) findViewById(R.id.update);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new SmallUpgradeManager(getThis()).checkUpgrade();
            }
        });

        mTest = (Button) findViewById(R.id.test);
        mTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                test();
            }
        });

        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private Activity getThis() {
        return this;
    }

    private void test() {
        MyLib.test();
        Log.d("locald","mylib2 test " + MyLib2.test());
    }
}
