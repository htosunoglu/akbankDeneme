package com.htosunoglu.akbankapideneme.akbankapideneme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnEndPost{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getir = (Button) findViewById(R.id.getir);

        assert getir != null;
        getir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String apiKey = "l7xx97102a1ad93a47a19c438a68b5d9bd1a";
                StringBuilder urlBuilder = new StringBuilder();
                //try {
                    CalendarView myCalendar = (CalendarView) findViewById(R.id.tarih);
                    Long dateLong = myCalendar.getDate();
                    Date tarih = new Date(dateLong);
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(tarih);

                    urlBuilder = new StringBuilder("https://apigate.akbank.com/api/mock/exchangeRatesApi/");

                    /*urlBuilder.append("?");
                    urlBuilder.append(URLEncoder.encode("date","UTF-8") + "=" + URLEncoder.encode(dateStr, "UTF-8"));*/
                /*} catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }*/
                new HttpAsyncTask(getApplicationContext(),apiKey, MainActivity.this).execute(urlBuilder.toString());
            }
        });
    }

    @Override
    public void Finish(String s) {
        if(s!=null && !s.isEmpty()){
            JsonElement jelement = new JsonParser().parse(s);
            JsonObject jobject = jelement.getAsJsonObject();
            jobject = jobject.getAsJsonObject("data");


            //in your OnCreate() method
            TextView sellPrice = (TextView) findViewById(R.id.sellPrice);
            TextView buyPrice = (TextView) findViewById(R.id.buyPrice);
            TextView currencyCodeAlpha = (TextView) findViewById(R.id.currencyCodeAlpha);
            sellPrice.setText("Satış Fiyatı : " + jobject.get("sellPrice"));
            buyPrice.setText("Alış Fiyatı : " + jobject.get("buyPrice"));
            currencyCodeAlpha.setText("Döviz Cinsi : " + jobject.get("currencyCodeAlpha"));
        }
    }


}
