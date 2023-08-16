package com.example.news_aggregator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopicsRecycleAdapter extends RecyclerView.Adapter<TopicsRecycleAdapter.ViewHolder> {
    private ArrayList<TopicsRecycleModel> topicsRecycleModels;
    private Context context;
    private TopicClickInterface topicClickInterface;

    public TopicsRecycleAdapter(ArrayList<TopicsRecycleModel> topicsRecycleModels, Context context, TopicClickInterface topicClickInterface) {
        this.topicsRecycleModels = topicsRecycleModels;
        this.context = context;
        this.topicClickInterface = topicClickInterface;
    }

    @NonNull
    @Override
    public TopicsRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topics_recycle,parent,false);
        return new TopicsRecycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsRecycleAdapter.ViewHolder holder, int position) {
        TopicsRecycleModel topicsRecycleModel = topicsRecycleModels.get(position);
        holder.topicTextView.setText(topicsRecycleModel.getTopics());
        Picasso.get().load(topicsRecycleModel.getTopicsImageUrl()).into(holder.topicImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicClickInterface.onTopicClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicsRecycleModels.size();
    }

    //Refresh feed once a topic is selected
    public interface TopicClickInterface{
        void onTopicClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView topicTextView;
        private ImageView topicImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topicTextView = itemView.findViewById(R.id.idTextViewTopic);
            topicImageView = itemView.findViewById(R.id.idImageViewTopic);
        }
    }
}
