package com.example.news_aggregator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//In original, public interface is called retrofitAPI, might cause issues later
public interface RestAPI {
    @GET
    Call<NewsModel> getBreakingNews(@Url String url);
                    //change getALlNews
    @GET
    Call<NewsModel> getNewsTopics(@Url String url);
                    //change by category
}
