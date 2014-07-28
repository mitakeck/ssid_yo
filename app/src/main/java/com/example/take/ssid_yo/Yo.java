package com.example.take.ssid_yo;

import android.app.SearchManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by take on 7/28/14.
 */
public class Yo extends AsyncTask<String, Integer, Integer> implements SearchManager.OnCancelListener {
    private static final String TAG = "Yo";

    private static final String urlYoAll = "http://api.justyo.co/yoall/";
    private static final String yoApiToken = "e656668e-7e07-ce3f-c202-4a43ee7b8e4f";

    //クライアント設定
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(urlYoAll);

    List<NameValuePair> nameValuePair;
    HttpResponse response;
    ByteArrayOutputStream byteArrayOutputStream;
    HttpResponse res = null;

    public Yo() {
        super();
    }

    public static int all(){
        Yo yo = new Yo();
        yo.execute("");
        return 200;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... content) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(urlYoAll);

        // API トークンをセット
        ArrayList<NameValuePair> params = new ArrayList <NameValuePair>();
        params.add( new BasicNameValuePair("api_token", yoApiToken));

        try{
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            res = httpClient.execute(post);
            byteArrayOutputStream = new ByteArrayOutputStream();
            res.getEntity().writeTo(byteArrayOutputStream);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return Integer.valueOf(res.getStatusLine().getStatusCode());
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        //サーバーからの応答を取得
        // if(res.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
            // Log.d(TAG, "onPostExecute - getStatusCode - SC_OK");
            //デバッグ用にリザルトコードをTextViewに表示させておく
            // TextView tv = (TextView) this.m_Activity.findViewById(R.id.ssid);
            // tv.setText(""+result);

            // tv.setText(result+"\n"+byteArrayOutputStream.toString());

            //サーバーから受けとった文字列の処理
            // if(byteArrayOutputStream.toString().equals("1")){
                // Toast.makeText(this.m_Activity, "[ここには処理１] ", Toast.LENGTH_LONG).show();
            // }else{
                // Toast.makeText(this.m_Activity, "[ここには処理２] ", Toast.LENGTH_LONG).show();
            // }
        // }else{
            // Log.d(TAG, "onPostExecute - getStatusCode - " + res.getStatusLine().getStatusCode());
            // Toast.makeText(this.m_Activity, "[error]: "+response.getStatusLine(), Toast.LENGTH_LONG).show();
        // }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onCancel() {

    }
}
