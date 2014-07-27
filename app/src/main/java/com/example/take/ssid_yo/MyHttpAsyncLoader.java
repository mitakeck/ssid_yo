package com.example.take.ssid_yo;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Entity;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by take on 7/26/14.
 */
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
