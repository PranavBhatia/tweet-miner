package services;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Model.SentimentCompute;
import play.libs.Json;
import twitter4j.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentSkipListMap;

public class TweetsService {


    public static CompletableFuture<ArrayNode> getTweets(String keyword, int limit){
        CompletableFuture<ArrayNode> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        Query query = new Query(keyword);
        query.setCount(limit);

        QueryResult result = null;
        try {
            result = twitter.search(query);
        }catch (TwitterException e){
            e.printStackTrace();
        }

        List<Status> tweets = result.getTweets();
        String sentiments=SentimentCompute.smileyLevelStatistic(tweets);
        ArrayNode tweetsArrayNode = Json.newArray();

        tweets.forEach((tweet) -> {
            ObjectNode tempTweetsObjectNode = Json.newObject();
            tempTweetsObjectNode.put("tweetsText", tweet.getText());
            tempTweetsObjectNode.put("userName", tweet.getUser().getName());
            tempTweetsObjectNode.put("userScreenName", tweet.getUser().getScreenName());
            tempTweetsObjectNode.put("userLocation", tweet.getUser().getLocation());
            tempTweetsObjectNode.put("sentiment", sentiments);

            if(tweet.getGeoLocation()!=null) {
                tempTweetsObjectNode.put("geolocationLatitude", tweet.getGeoLocation().getLatitude());
                tempTweetsObjectNode.put("geolocationLongitude", tweet.getGeoLocation().getLongitude());
            }else{
                tempTweetsObjectNode.put("geolocationLatitude", 0);
                tempTweetsObjectNode.put("geolocationLongitude", 0);
            }
            ArrayNode tempHashtagArrayNode = Json.newArray();
            StringBuilder s = new StringBuilder();
            for (HashtagEntity hashtagEntity: tweet.getHashtagEntities()) {
                s = s.append(hashtagEntity.getText() + ",");
            }
            tempTweetsObjectNode.put("getHashtags", s.toString());
            tweetsArrayNode.add(tempTweetsObjectNode);
        });
        System.out.println("TweetsText:"+tweetsArrayNode.get(0).get("tweetsText").asText());

        future.complete(tweetsArrayNode);
        return future;
    }

    public static CompletableFuture<List<Status>> getHashtagTweets(String location){
        CompletableFuture<List<Status>> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        Query query = new Query(location);
        query.setCount(10);
        QueryResult result = null;
        try {
            result = twitter.search(query);
        }catch (TwitterException e){
            e.printStackTrace();
        }
        List<Status> tweets = result.getTweets();
        future.complete(tweets);
        return future;
    }

    public static CompletableFuture<List<Status>> getLocationTweets(String latitude, String longitude){
        //For testing -> http://localhost:9000/getLocation/28.56929189/%2077.31774961
        CompletableFuture<List<Status>> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        ArrayList<Status> tweets = new ArrayList<>();
        if (!latitude.equals("0")){
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);
            double res = 10;
            String resUnit = "mi";
            Query query = new Query().geoCode(new GeoLocation(lat,lon), res, resUnit);
            query.setCount(10);
            QueryResult result = null;
            try {
                result = twitter.search(query);
            }catch (TwitterException e){
                e.printStackTrace();
            }
            tweets = (ArrayList<Status>) result.getTweets();
        }
        future.complete(tweets);
        return future;
    }

    public static CompletableFuture<User> getUser(String username){
        CompletableFuture<User> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        User user=null;
        try {
            user = (twitter.showUser(username));
        }catch (TwitterException e) {
            e.printStackTrace();
        }
        future.complete(user);
        System.out.println("User = " + user);
        return future;
    }
}