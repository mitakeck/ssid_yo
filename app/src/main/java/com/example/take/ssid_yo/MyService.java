package com.example.take.ssid_yo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
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
        Log.v("TEST", "onHandleIntent");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "onHandleIntent", Toast.LENGTH_LONG).show();
            }
        });
    }

}
