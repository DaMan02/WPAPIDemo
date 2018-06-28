package com.dayal.wpapidemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<PostItem> postItems;
    private PostAdapter postAdapter;
    private RequestQueue queue;
//    Context context;

    private static final String BASE_URL = "http://www.json-generator.com/api/json/get/cfbxuXPaMi?indent=2";
    public static final String POSTER_URL="https://searchengineland.com/figz/wp-content/seloads/2015/08/movie-film-video-production-ss-1920-800x450.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        listView = (ListView) findViewById(R.id.list_view);

        postItems = new ArrayList<>();
        postAdapter = new PostAdapter(postItems,MainActivity.this);
        listView.setAdapter(postAdapter);
        postItems = getPost();
//        if (context!=null){


//        }




    }

    public List<PostItem> getPost() {
//        postItems.clear();
//        PostItem p=new PostItem();
//        p.setDescription("Description");
//        p.setDate("Date");
//
//        postItems.add(p);
//        postAdapter.notifyDataSetChanged();

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET,BASE_URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray movieArray=response.getJSONArray("search");
                    for (int i=0;i<movieArray.length();i++){
                        JSONObject jsonObject = movieArray.getJSONObject(i);
                        PostItem p=new PostItem();
                        p.setDescription(jsonObject.getString("title"));
                        p.setDate(jsonObject.getString("userId"));
                        p.setImageUrl(jsonObject.getString(POSTER_URL));

                         postItems.add(p);
                        postAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.w("log","Error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"Server not responding",Toast.LENGTH_LONG).show();
            }
        });
//        JsonArrayRequest getRequest= new JsonArrayRequest(Request.Method.GET,BASE_URL,null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.w("log", response.toString());
//
//                        // Parsing json
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//
//                                JSONObject obj = response.getJSONObject(i);
//                                PostItem p = new PostItem();
//                                p.setDate(obj.getString("userID"));
//                                p.setDescription(obj.getString("title"));
//                                p.setImageUrl(obj.getString(POSTER_URL));
//
//                                postItems.add(p);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
////                        postAdapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("log", "Error: " + error.getMessage());
//            }
//        });


        queue.add(objectRequest);
        return postItems;
    }
}