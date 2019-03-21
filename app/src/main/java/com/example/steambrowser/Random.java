package com.example.steambrowser;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class Random extends AppCompatActivity{

    private TextView mGameName;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        mProgressBar = findViewById(R.id.progress_bar_random);

//        mGameName = findViewById(R.id.tv_game_text);
        new RandomGetTask().execute(AppUtils.getAppUrl());
    }

    public class RandomGetTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... urls) {
            String searchURL = urls[0];
            String results = null;
            try {
                results = NetworkUtils.doHTTPGet(searchURL);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgressBar.setVisibility(View.INVISIBLE);
            if(s != null) {
                int appid = AppUtils.getRandomAppID(s);

                WebView webView=findViewById(R.id.random_view);
                // load the game detail website
                webView.loadUrl("https://store.steampowered.com/app/"+appid);

                // set ProgressBar invisible after loading website successfully
                webView.setWebViewClient(new WebViewClient()
                {
                    @Override
                    public void onPageFinished(WebView view, String url)
                    {
                        super.onPageFinished(view, url);
                    }
                });
            }
        }
    }


}
