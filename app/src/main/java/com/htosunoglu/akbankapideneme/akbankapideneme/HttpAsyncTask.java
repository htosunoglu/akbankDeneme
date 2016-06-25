package com.htosunoglu.akbankapideneme.akbankapideneme;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Harun on 1.5.2016.
 */
public class HttpAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    private String apiKey;
    private OnEndPost onEndPost;

    public HttpAsyncTask(Context cont, String apiKeyF, OnEndPost onEndPost) {
        this.context = cont;
        this.apiKey = apiKeyF;
        this.onEndPost = onEndPost;
    }

    @Override
    protected String doInBackground(String... urls) {
        return GET(urls[0]);
    }

    // onPostExecute displays the results of the AsyncTask.

    protected void onPostExecute(String result) {
        onEndPost.Finish(result);
    }

    public static String GET(String url) {
        String result = "";
        HttpClient httpClient = new DefaultHttpClient();

        try {

            HttpGet request = new HttpGet(url);

            request.addHeader("content-type", "application/json");
            request.addHeader("apikey", "l7xx97102a1ad93a47a19c438a68b5d9bd1a");
            //request.setEntity(new StringEntity(body, HTTP.UTF_8));

            HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String sResponse = "";


            while ((sResponse = reader.readLine()) != null) {
                result += sResponse;
            }

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
}