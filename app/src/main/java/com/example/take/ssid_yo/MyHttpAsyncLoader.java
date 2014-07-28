package com.example.take.ssid_yo;

import android.app.Activity;
import android.app.SearchManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Entity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by take on 7/26/14.
 */
public class MyHttpAsyncLoader extends AsyncTask<String, Integer, Integer> implements SearchManager.OnCancelListener{
    final String TAG = "MyAsyncTask";

    private Activity m_Activity;

    // private static final String url = "http://192.168.0.6:1337/foo/create";
    private static final String urlYoAll = "http://api.justyo.co/yoall/";
    private static final String yoApiToken = "e656668e-7e07-ce3f-c202-4a43ee7b8e4f";


    //クライアント設定
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(urlYoAll);

    List<NameValuePair> nameValuePair;
    HttpResponse response;
    ByteArrayOutputStream byteArrayOutputStream;
    HttpResponse res = null;

    @Override
    public void onCancel() {
        //
    }

    public MyHttpAsyncLoader(Activity activity){
        this.m_Activity = activity;
    }

    @Override
    protected void onPreExecute(){
        Log.d(TAG, "onPreExecute");
    }

    @Override
    protected Integer doInBackground(String... contents){
        Log.d(TAG, "doInBackground - " + contents[0]);
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(urlYoAll);

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
    protected void onProgressUpdate(Integer... values){
        Log.d(TAG, "onProgressUpdate - " + values[0]);
    }

    @Override
    protected void onCancelled(){
        Log.d(TAG, "onCancelled");
    }

    @Override
    protected void onPostExecute(Integer result){
        Log.d(TAG, "onPostExecute - " + result);


        //サーバーからの応答を取得
        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
            Log.d(TAG, "onPostExecute - getStatusCode - SC_OK");
            //デバッグ用にリザルトコードをTextViewに表示させておく
            TextView tv = (TextView) this.m_Activity.findViewById(R.id.ssid);
            tv.setText(""+result);

            tv.setText(result+"\n"+byteArrayOutputStream.toString());

            //サーバーから受けとった文字列の処理
            if(byteArrayOutputStream.toString().equals("1")){
                // Toast.makeText(this.m_Activity, "[ここには処理１] ", Toast.LENGTH_LONG).show();
            }else{
                // Toast.makeText(this.m_Activity, "[ここには処理２] ", Toast.LENGTH_LONG).show();
            }
        }else{
            Log.d(TAG, "onPostExecute - getStatusCode - " + res.getStatusLine().getStatusCode());
            // Toast.makeText(this.m_Activity, "[error]: "+response.getStatusLine(), Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    public void onCancel(DialogInterface dialog){
//        Log.d(TAG, "Dialog onCancell... calling cancel(true)");
//        this.cancel(true);
//    }
}
/*
public class MyHttpAsyncLoader extends AsyncTaskLoader<String> {
    // API の URL
    private String url = null;
    private final String TAG = "MY_HTTP_ASYNC_LOADER";

    public MyHttpAsyncLoader(Context context, String url){
        super(context);
        this.url = url;
    }
    @Override
    public String loadInBackground(){
        final HttpClient mHttpClient = new DefaultHttpClient();
        try {
            Log.v(TAG, "Start HttpClient");
            String mResponseBody = mHttpClient.execute(new HttpGet(this.url),
                new ResponseHandler<String>() {
                    @Override
                    public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                        if(HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()){
                            Log.v(TAG, "Status SC_OK");
                            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                        }
                        return null;
                    }
                }
            );
            return mResponseBody;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mHttpClient.getConnectionManager().shutdown();
        }
        return null;
    }
}
*/