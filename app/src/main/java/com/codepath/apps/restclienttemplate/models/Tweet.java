package com.codepath.apps.restclienttemplate.models;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
    public String body;
    public long uid;
    public User user;
    public String createdAt;
    public Entity entity;
    public boolean hasEntities;

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        JSONObject entities = jsonObject.getJSONObject("entities");
        if (entities.has("media")) {
            JSONArray mediaEndPoint = entities.getJSONArray("media");
            if (mediaEndPoint != null && mediaEndPoint.length() != 0) {
                tweet.entity = Entity.fromJSON(jsonObject.getJSONObject("entities"));
                tweet.hasEntities = true;
//                tweet.imageURL = mediaEndPoint.getJSONObject(0).getString("media_url_https");
            } else {
                tweet.hasEntities = false;
            }
        }

        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        //tweet.entity = Entity.fromJSON(jsonObject);
        return tweet;
    }
}
