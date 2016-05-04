package com.example.mysmall.app.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Log.d("locald","SubActivity onCreate ..");
    }

    protected void onStart() {
        super.onStart();
        Log.d("locald", "SubActivity onStart ..");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d("locald","SubActivity onDestroy ..");
    }

    protected void onResume() {
        super.onResume();
        Log.d("locald", "SubActivity onResume ..");
    }

    protected void onPause() {
        super.onPause();
        Log.d("locald", "SubActivity onPause ..");
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean r = super.onTouchEvent(event);
        Log.d("locald", "SubActivity onTouchEvent ..");
        return r;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "sub activity settings show toast", Toast.LENGTH_SHORT)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
