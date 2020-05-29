package com.example.api_0527.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class Topic {
    private int id;
    private String title;
    private String ImageUrl;

    public static Topic getTopicFromJson(JSONObject jsonObject){
        Topic topic = new Topic();

        try {
            topic.id = jsonObject.getInt("id");
            topic.title = jsonObject.getString("title");
            topic.ImageUrl = jsonObject.getString("img_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return topic;
    }
    public Topic(){

    }

    public Topic(int id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        ImageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
