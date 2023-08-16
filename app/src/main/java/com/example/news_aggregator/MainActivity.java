package com.example.news_aggregator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TopicsRecycleAdapter.TopicClickInterface{
    //HTTP REST-API KEY: 2c0d80a0aa0c4c99bddef8152cf1b4fb

    private RecyclerView newsRecycleView,topicRecycleView;
    private ProgressBar loadingProgress;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<TopicsRecycleModel> topicsRecycleModelArrayList;
    private TopicsRecycleAdapter topicsRecycleAdapter;
    private NewsRecycleAdapter newsRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRecycleView = findViewById(R.id.RecycleNews);
        topicRecycleView = findViewById(R.id.RecycleTopics);
        loadingProgress = findViewById(R.id.ProgressBarLoading);
        articlesArrayList = new ArrayList<>();
        topicsRecycleModelArrayList = new ArrayList<>();
        newsRecycleAdapter = new NewsRecycleAdapter(articlesArrayList,this);
        topicsRecycleAdapter = new TopicsRecycleAdapter(topicsRecycleModelArrayList,this,this::onTopicClick);
        newsRecycleView.setLayoutManager(new LinearLayoutManager(this));
        newsRecycleView.setAdapter(newsRecycleAdapter);
        topicRecycleView.setAdapter(topicsRecycleAdapter);
        getTopics();
        getNews("Breaking News");
        newsRecycleAdapter.notifyDataSetChanged();

    }

    //Topic Background Images Url
    private void getTopics(){

        topicsRecycleModelArrayList.add(new TopicsRecycleModel("Breaking News","https://images.unsplash.com/photo-1504711434969-e33886168f5c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"));
        topicsRecycleModelArrayList.add(new TopicsRecycleModel("Immigration","https://images.unsplash.com/photo-1535483102974-fa1e64d0ca86?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8aW1taWdyYXRpb258ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=1400&q=60"));
        topicsRecycleModelArrayList.add(new TopicsRecycleModel("Housing","https://images.unsplash.com/photo-1568632234170-9adf33c23dd9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8bGF0aW4lMjBhbWVyaWNhfGVufDB8fDB8fHww&auto=format&fit=crop&w=1400&q=60"));
        topicsRecycleModelArrayList.add(new TopicsRecycleModel("Employment","https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8d29ya3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=1400&q=60"));
        topicsRecycleModelArrayList.add(new TopicsRecycleModel("La Colaborativa","https://la-colaborativa.org/wp-content/uploads/2023/03/LaColab2212Pantry__2178-scaled.jpg"));
        topicsRecycleModelArrayList.add(new TopicsRecycleModel("Chelsea","https://seagrant.noaa.gov/Portals/0/EasyDNNnews/435/541chelsea.jpg"));
        topicsRecycleModelArrayList.add(new TopicsRecycleModel("Latin America","https://images.unsplash.com/photo-1544906243-a69767cc000b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8bGF0aW4lMjBhbWVyaWNhfGVufDB8fDB8fHww&auto=format&fit=crop&w=1400&q=60"));
        topicsRecycleAdapter.notifyDataSetChanged();

    }

    private void getNews(String topic){
        //Pulling news data, passing query topic
        loadingProgress.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        // String topicURL = "https://newsapi.org/v2/everything?q=" +topic+ "&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb";
        String breakingNewsURL = "https://newsapi.org/v2/top-headlines?category=general&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb"; // &country=+us+...?
        String immigrationURL = "https://newsapi.org/v2/everything?q=+immigration+latin&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb";
        String housingURL = "https://newsapi.org/v2/everything?q=+housing+latin&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb";
        String employmentURL = "https://newsapi.org/v2/everything?q=+employment+immigrants+boston&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb";
        String colaborativaURL = "https://newsapi.org/v2/everything?q=+colaborativa+chelsea&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb";
        String chelseaURL = "https://newsapi.org/v2/everything?q=chelsea&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb";
        String laURL = "https://newsapi.org/v2/everything?q=+latin+america&apiKey=2c0d80a0aa0c4c99bddef8152cf1b4fb";
        //Test^^ new in boston housing/immigration past two weeks
        String Base_URL = "https://newsapi.org/";
        //Retrofit to manage HTTPS calls
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI restAPI = retrofit.create(RestAPI.class);
        Call<NewsModel> call;

        //restAPI.getNewsTopics() might not work with new method
        if(topic.equals("Immigration")){
            call = restAPI.getNewsTopics(immigrationURL);
            //call = restAPI.getBreakingNews(breakingNewsURL);
        } else if (topic.equals("Housing")) {
            call = restAPI.getNewsTopics(housingURL);
        } else if (topic.equals("Employment")) {
            call = restAPI.getNewsTopics(employmentURL);
        } else if (topic.equals("La Colaborativa")) {
            call = restAPI.getNewsTopics(colaborativaURL);
        } else if (topic.equals("Chelsea")) {
            call = restAPI.getNewsTopics(chelseaURL);
        } else if (topic.equals("Latin America")) {
            call = restAPI.getNewsTopics(laURL);
        } else {
            call = restAPI.getBreakingNews(breakingNewsURL);
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                loadingProgress.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModel.getArticles();
                for(int i=0; i<articles.size(); i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl(), articles.get(i).getContent()));
                }

                newsRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sorry! It looks like there are no current articles for this topic.", Toast.LENGTH_SHORT).show();
            }
        });

    }
//Adding new topics: Need url string & else if statement in getNews()
    //And array list in get topics
    @Override
    public void onTopicClick(int position) {
        String topic = topicsRecycleModelArrayList.get(position).getTopics();
        getNews(topic);
    }
}