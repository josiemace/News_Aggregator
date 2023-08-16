package com.example.news_aggregator;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRecycleAdapter extends RecyclerView.Adapter<NewsRecycleAdapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsRecycleAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycle,parent,false);
        return new NewsRecycleAdapter.ViewHolder(view);


    }

    @Override
    //Whats visible in the main news recycle view
    public void onBindViewHolder(@NonNull NewsRecycleAdapter.ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.subtitleTextView.setText(articles.getDescription());
        holder.titleTextView.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsImageView);
        //On Click expand article, below
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,NewsFeatureActivity.class);
                //Passing Values from API
                i.putExtra("title",articles.getTitle());
                i.putExtra("description",articles.getDescription());
                i.putExtra("content",articles.getContent());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView, subtitleTextView;
        private ImageView newsImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.idTextViewNewsHeader);
            subtitleTextView = itemView.findViewById(R.id.idTextViewSubheading);
            newsImageView = itemView.findViewById(R.id.idImageViewNews);
        }
    }
}
