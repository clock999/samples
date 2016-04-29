package com.example.mysmall;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import net.wequick.small.Small;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class SmallUpgradeManager {

    private static class UpgradeInfo {
        public String packageName;
        public String downloadUrl;
        public String filePath;
        public boolean isLocal;
        public String patchName;
    }

    private interface OnResponseListener {
        void onResponse(Object object);
    }

    private interface OnUpgradeListener {
        void onUpgrade();
    }

    private Context mContext;
    private ProgressDialog mProgressDlg;

    public SmallUpgradeManager(Context context) {
        mContext = context;
    }

    public void checkUpgrade() {
        mProgressDlg = ProgressDialog.show(mContext, "Small", "Checking for updates...");
        requestUpgradeInfo(Small.getBundleVersions(), new OnResponseListener() {
            @Override
            public void onResponse(Object object) {
                UpgradeInfo info = (UpgradeInfo) object;
                final net.wequick.small.Bundle bundle = Small.getBundle(info.packageName);
                Log.d("locald", "checkUpgrade package name " + info.packageName
                        + " download url " + info.downloadUrl);
                mProgressDlg.setMessage("Upgrading...");
                if(info.isLocal != true) {
                    upgradeBundle(bundle, info.downloadUrl, bundle.getPatchFile(),
                            new OnUpgradeListener() {
                                @Override
                                public void onUpgrade() {
                                    mProgressDlg.dismiss();
                                    mProgressDlg = null;
                                    Toast.makeText(mContext,
                                            "Upgrade Success! Restart and Click `Call lib.utils` to see changes.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    upgradeBundleLocal(bundle, info.filePath, bundle.getPatchFile(),
                            new OnUpgradeListener() {
                                @Override
                                public void onUpgrade() {
                                    mProgressDlg.dismiss();
                                    mProgressDlg = null;
                                    Toast.makeText(mContext,
                                            "Upgrade Success! Restart and Click `Call lib.utils` to see changes.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    /**
     *
     * @param versions
     * @param listener
     */
    private void requestUpgradeInfo(Map versions, OnResponseListener listener) {
        // Just for example, you can replace this with a real HTTP request.
        System.out.println(versions);
        UpgradeInfo info = new UpgradeInfo();
        info.packageName = "com.example.mysmall.app.test";
        info.downloadUrl = "http://code.wequick.net/small/upgrade/" +
                "libnet_wequick_example_small_lib_utils.so";
        info.filePath = "/sdcard/libcom_example_mysmall_app_test.so";
        info.patchName = "libcom_example_mysmall_app_test.so";
        info.isLocal = true;
        listener.onResponse(info);
    }

    private static class DownloadHandler extends Handler {
        private OnUpgradeListener mListener;
        public DownloadHandler(OnUpgradeListener listener) {
            mListener = listener;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mListener.onUpgrade();
                    break;
            }
        }
    }

    private DownloadHandler mHandler;

    private void upgradeBundleLocal(final net.wequick.small.Bundle bundle,
                               final String fileStr, final File file,
                               final OnUpgradeListener listener) {
        // Just for example, you can do this by OkHttp or something.
        mHandler = new DownloadHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    byte[] buf = null;
                    // Save
                    //OutputStream os = new FileOutputStream(file);
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                    File soFile =  new File(fileStr);
                    FileInputStream instream;
                    try
                    {
                        soFile.createNewFile();
                        instream =  new FileInputStream(soFile);
                        buf = new byte[instream.available()];
                        instream.read(buf);
                        os.write(buf, 0, buf.length);
                        os.flush();
                        os.close();
                        instream.close();
                    }
                    catch(FileNotFoundException e)
                    {
                        Log.w("localdebug", "testFiles FileNotFoundException  \n");
                    }
                    catch(IOException e)
                    {
                        Log.w("localdebug", "testFiles IOException : \n"+e.getMessage());
                    }

                    // While you finish downloading patch file, call this
                    bundle.upgrade();

                    Message.obtain(mHandler, 1).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void upgradeBundle(final net.wequick.small.Bundle bundle,
                               final String urlStr, final File file,
                               final OnUpgradeListener listener) {
        // Just for example, you can do this by OkHttp or something.
        mHandler = new DownloadHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConn.getInputStream();
                    // Save
                    OutputStream os = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        os.write(buffer, 0, length);
                    }
                    os.flush();
                    os.close();
                    is.close();

                    // While you finish downloading patch file, call this
                    bundle.upgrade();

                    Message.obtain(mHandler, 1).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}