package com.example.mysmall.app.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.mylib.MyLib;
import com.example.mylib2.MyLib2;

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

        if (id == R.id.action_1) {
            test();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void test() {
        MyLib.test();
        Log.d("locald","mylib2 test " + MyLib2.test());
        /*
        File saveFile =  new File("/sdcard/file_test.txt");
        FileInputStream instream;
        String str = null;
        try
        {
            saveFile.createNewFile();
            instream =  new FileInputStream(saveFile);
            byte[] buf = new byte[instream.available()];
            instream.read(buf);
            instream.close();
            str = new String(buf);
            Log.w("localdebug", "str is " + str);
        }
        catch(FileNotFoundException e)
        {
            Log.w("localdebug", "testFiles FileNotFoundException  \n");
        }
        catch(IOException e)
        {
            Log.w("localdebug", "testFiles IOException : \n" + e.getMessage());
        }*/
    }
}
