package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.news.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    Toolbar toolbar2;
    WebView webView;
    TextView txt_title, txt_author, txt_content, txt_time, txt_detail;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_title = findViewById(R.id.text_detail_title);
        txt_author = findViewById(R.id.text_detail_author);
        txt_detail = findViewById(R.id.text_detail_detail);
        txt_content = findViewById(R.id.text_detail_content);
        txt_time = findViewById(R.id.text_detail_time);
        img_news = findViewById(R.id.img_detail_news);

        webView = findViewById(R.id.web_view);
        toolbar2  = findViewById(R.id.toolbar2);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        setSupportActionBar(toolbar2);

        txt_title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_time.setText(headlines.getPublishedAt());
        txt_detail.setText(headlines.getDescription());
        txt_content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(img_news);

        img_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri myUri = Uri.parse(headlines.getUrl());
                //String loadUrl = headlines.getUrl();
                //Intent intent = getIntent();
                //String urlLoad = intent.getStringExtra(loadUrl);
                /*webView = findViewById(R.id.web_view);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(urlLoad);*/
                //Toast.makeText(DetailsActivity.this, urlLoad, Toast.LENGTH_SHORT).show();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(DetailsActivity.this,myUri );
            }
        });

    }
    private void setSupportActionBar(Toolbar toolbar2) {
        this.toolbar2 = toolbar2;
    }
}