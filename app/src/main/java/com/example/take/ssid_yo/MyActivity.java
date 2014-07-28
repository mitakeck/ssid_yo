package com.example.take.ssid_yo;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity implements DialogInterface.OnClickListener{
    // Log の tag で使用する文字列を定義
    private final String TAG = "MY_ACTIVITY";

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // none
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // サービスを開始する
        // startService(new Intent(this, MyService.class));

        // SSID を取得
        String Mssid = getSSID();
        final TextView MtextViewSSID = (TextView)findViewById(R.id.ssid);
        MtextViewSSID.setText(Mssid);
        Log.v(TAG, Mssid);
        if(Mssid.equals("\"JellyBeans-A\"")){
            Toast.makeText(getApplicationContext(), "YO SEND", Toast.LENGTH_LONG).show();
            Yo.all();
        }

        // ListView のインスタンスを取得
        ListView list = (ListView)findViewById(R.id.listView);
        // ListView のラベルを格納する ArrayList をインスタンス化
        ArrayList<String> labelList = new ArrayList<String>();
        // とりあえずラベルを 20 個作る
        for(int i=0;i<20;i++){
            labelList.add("SEND #"+ i + "@" + Mssid);
        }
        // Adapter のインスタンス化
        CustomAdapter mAdapter = new CustomAdapter(this, 0, labelList);
        // ListView にアダプタを設定
        list.setAdapter(mAdapter);
        list.setDivider(null);


        // MyHttpAsyncLoader mMyHttpAsyncLoader = new MyHttpAsyncLoader(this);
        // mMyHttpAsyncLoader.execute("hoge");
        // Yo.all();
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
