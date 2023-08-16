 package com.example.news_aggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsFeatureActivity extends AppCompatActivity {
    String title,description,content,imageURL,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feature);
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

    }
}