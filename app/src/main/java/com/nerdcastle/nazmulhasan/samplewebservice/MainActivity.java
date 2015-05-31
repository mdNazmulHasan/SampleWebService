package com.nerdcastle.nazmulhasan.samplewebservice;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends ActionBarActivity {
	JSONArray addonArray;
	ArrayList<String> addonsList;
	ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.listView);
        addonsList=new ArrayList<String>();
        showService();
    }
    
    
    private void showService() {
    	StringRequest stringrequest = new StringRequest(Method.GET,
				"http://nerdcastlebd.com/web_service/banglapoems/index.php/poets/all/format/json",
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							addonArray =jsonObject.getJSONArray("poets");
							for (int i = 0; i < addonArray.length(); i++) {

							 String addonsName = addonArray.getJSONObject(i)
										.getString("name");
							 final String biography = addonArray.getJSONObject(i)
										.getString("biography");
								addonsList.add(addonsName);
								ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,addonsList);
								listView.setAdapter(adapter);
								listView.setOnItemClickListener(new OnItemClickListener() {

									@Override
									public void onItemClick(
											AdapterView<?> parent, View view,
											int position, long id) {
									String bio=addonsList.get(position);
									
										Toast.makeText(getApplicationContext(), addonsList.get(position),Toast.LENGTH_LONG).show();
									}
								});

							

							}

						} catch (JSONException e) {

							e.printStackTrace();
						}

					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {

					}
				});

		AppController.getInstance().addToRequestQueue(stringrequest);

		
	}


}
