package com.example.take.ssid_yo;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity implements LoaderManager.LoaderCallbacks<String>{
    // Log の tag で使用する文字列を定義
    private final String TAG = "MY_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // サービスを開始する
        startService(new Intent(this, MyService.class));

        Bundle mBundle = new Bundle();
        mBundle.putString("url", "http://www.drk7.jp/weather/json/27.js");
        getLoaderManager().initLoader(0, mBundle, this);


        // SSID を取得
        String Mssid = getSSID();
        final TextView MtextViewSSID = (TextView)findViewById(R.id.ssid);
        MtextViewSSID.setText(Mssid);
    }

    /***
     * SSID取得する
     * @return SSID
     */
    private String getSSID(){
        // SSID を取得
        WifiManager mWifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
        String Mssid = mWifiInfo.getSSID();
        return Mssid;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle bundle){
        MyHttpAsyncLoader loader = new MyHttpAsyncLoader(this, bundle.getString("url"));
        loader.forceLoad();
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String body){
        if(loader.getId() == 0){
            if(body != null){
                Log.v(TAG, body);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader){
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
