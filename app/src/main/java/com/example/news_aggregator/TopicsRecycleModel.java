package com.example.news_aggregator;

public class TopicsRecycleModel {

    private String topics;
    private String topicsImageUrl;

    public TopicsRecycleModel(String topics, String topicsImageUrl) {
        this.topics = topics;
        this.topicsImageUrl = topicsImageUrl;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getTopicsImageUrl() {
        return topicsImageUrl;
    }

    public void setTopicsImageUrl(String topicsImageUrl) {
        this.topicsImageUrl = topicsImageUrl;
    }

    //using topics instead of category & categoryImageURL; might cause errors later
}
