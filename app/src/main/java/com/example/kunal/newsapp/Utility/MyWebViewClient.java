package com.example.kunal.newsapp.Utility;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.kunal.newsapp.R;

public class MyWebViewClient extends WebViewClient {
    ProgressBar progressBar;
    private Activity _activity;
    public MyWebViewClient(Activity activity)
    {
        _activity = activity;
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        progressBar = _activity.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        return false;
    }
    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub
        super.onPageFinished(view, url);

        progressBar.setVisibility(View.GONE);
    }
}
