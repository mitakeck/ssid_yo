package com.example.take.ssid_yo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;
import android.os.Handler;

import java.util.logging.LogRecord;

/**
 * Created by take on 7/25/14.
 */
public class MyService extends IntentService{
    private Context mContext;
    private Handler mHandler;

    // Log の tag で使用する文字列を定義
    private final String TAG = "MY_SERVICE";

    public MyService(String name){
        super(name);
    }

    // startServis から呼ばれるメソッド
    public MyService(){
        super("MyServis");
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent _intent){
        mContext = getApplicationContext();
        // SSID を取得
        WifiManager mWifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
        final String Mssid = mWifiInfo.getSSID();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), Mssid, Toast.LENGTH_LONG).show();
            }
        });
    }

}
