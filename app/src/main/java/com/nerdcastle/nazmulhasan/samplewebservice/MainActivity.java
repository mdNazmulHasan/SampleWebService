package com.nerdcastle.nazmulhasan.samplewebservice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends Activity {
    String url="http://nerdcastlebd.com/web_service/banglapoems/index.php/poets/all/format/json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWebData();
        mReadJsonData("test");
    }

    private void getWebData() {
        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                "http://nerdcastlebd.com/_old-site/web_service/banglapoems/index.php/poets/all/format/json",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        mCreateAndSaveFile("test",response);

                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {

            }
        });

        AppController.getInstance().addToRequestQueue(stringrequest);

    }

    public void mCreateAndSaveFile(String params, String mJsonResponse) {
        try {
            FileWriter file = new FileWriter("/data/data/" + getApplicationContext().getPackageName() + "/" + params);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mReadJsonData(String params) {
        try {
            File f = new File("/data/data/" + getPackageName() + "/" + params);
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String mResponse = new String(buffer);
            Toast.makeText(MainActivity.this, mResponse, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
