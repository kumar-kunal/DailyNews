package com.example.kunal.newsapp.Utility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.example.kunal.newsapp.R;
import com.example.kunal.newsapp.Utility.MyWebViewClient;

public class NewsDetail extends AppCompatActivity{

    WebView newsDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        String clickedArticleUrl= getIntent().getStringExtra("ARTICLE_URL");
        newsDetail = findViewById(R.id.webView);
        newsDetail.getSettings().setJavaScriptEnabled(true);
        MyWebViewClient myWebViewClient = new MyWebViewClient(this);
        newsDetail.setWebViewClient(myWebViewClient);
        newsDetail.loadUrl(clickedArticleUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.newsDetail.canGoBack()) {
            this.newsDetail.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
