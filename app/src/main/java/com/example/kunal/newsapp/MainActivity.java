package com.example.kunal.newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kunal.newsapp.Adapter.MyAdapter;
import com.example.kunal.newsapp.Model.News;
import com.example.kunal.newsapp.Model.Result;
import com.example.kunal.newsapp.Model.Source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyTask myTask = new MyTask();
        myTask.execute("https://newsapi.org/v2/top-headlines?country=in&apiKey=30872d5ed599452fa9e455a8bfd4f531");


    }

    public Result convertJsonToResponse(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String status = jsonObject.getString("status");
            int totalResults = jsonObject.getInt("totalResults");

            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            ArrayList<News> articleArrayList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject articleObject = jsonArray.getJSONObject(i);
                String author = articleObject.getString("author");
                String title = articleObject.getString("title");
                String desc = articleObject.getString("description");
                String url = articleObject.getString("url");
                String imageUrl = articleObject.getString("urlToImage");
                String published = articleObject.getString("publishedAt");

                JSONObject source = articleObject.getJSONObject("source");
                String id = source.getString("id");
                String name = source.getString("name");
                Source sourceJava = new Source(id, name);

                News article = new News(author, title, desc, url, imageUrl, published, sourceJava);
                articleArrayList.add(article);
            }

            Result result = new Result(status, totalResults, articleArrayList);
            return result;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String string = strings[0];
            try {
                //get url
                URL url = new URL(string);
                //open a http connection
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //get the input stream from http connection
                InputStream inputStream = httpURLConnection.getInputStream();
                //convert the inputstream to scanner to get data
                Scanner scanner = new Scanner(inputStream);
                //read the data
                scanner.useDelimiter("\\A");
                String result = "";
                //if data is there than store in a string to print
                if (scanner.hasNext())
                    result = scanner.next();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //if error
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Result result=convertJsonToResponse(s);
            final ArrayList<News> arrayList=result.getArticles();
            final RecyclerView recyclerView=findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            MyAdapter myAdapter=new MyAdapter(arrayList,getBaseContext());
            recyclerView.setAdapter(myAdapter);
            final SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.lay);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // cancle the Visual indication of a refresh
                            swipeRefreshLayout.setRefreshing(false);
                            // set the number value in TextView
                            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                            MyAdapter myAdapter=new MyAdapter(arrayList,getBaseContext());
                            recyclerView.setAdapter(myAdapter);
                        }
                    }, 3000);
                }
            });


        }
    }
}

