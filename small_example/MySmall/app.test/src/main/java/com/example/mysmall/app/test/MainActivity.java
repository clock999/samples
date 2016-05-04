package com.example.mysmall.app.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Toast.makeText(this, "settings show toast", Toast.LENGTH_SHORT)
            .show();
            return true;
        }

        if (id == R.id.action_subactivity) {
            Intent i = new Intent();
            i.setClass(this, SubActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_0) {
            Intent intent = new Intent("MyReceiver123");
            sendBroadcast(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
