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
import java.util.concurrent.ExecutionException;

public class TweetsService {


	/**
	 * @author v6
	 * use the twitter api to retrieve tweets based on a keyword
	 * @param keyword query from which results will be obtained
	 * @param limit number of tweets to be returned
	 * @return future of a list of json objects
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
    public static CompletableFuture<ArrayNode> getTweets(String keyword, int limit) throws Exception{
        CompletableFuture<ArrayNode> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        Query query = new Query(keyword);
        query.setCount(limit);

        QueryResult result = null;
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
  
        ArrayNode tweetsArrayNode = Json.newArray();

        tweets.forEach((tweet) -> {
            ObjectNode tempTweetsObjectNode = Json.newObject();
            tempTweetsObjectNode.put("tweetsText", tweet.getText());
            tempTweetsObjectNode.put("userName", tweet.getUser().getName());
            tempTweetsObjectNode.put("userScreenName", tweet.getUser().getScreenName());
            tempTweetsObjectNode.put("userLocation", tweet.getUser().getLocation());
            
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
        future.complete(tweetsArrayNode);
        return future;
    }

    /**
     * @author shireen
     * Obtains tweets from twitter api based on user location
     * @param hashtag tweets will be based on this location
     * @return future of a list of json objects containing tweets
     */

    public static CompletableFuture<List<Status>> getHashtagTweets(String hashtag) throws Exception{
        CompletableFuture<List<Status>> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        Query query = new Query(hashtag);
        query.setCount(10);
        QueryResult result = null;
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        future.complete(tweets);
        return future;
    }

    /**
     * @author pranav
     * Obtains tweets from twitter api based on user's geolocation
     * @param latitude geolocation attribute of tweeting user
     * @param longitude
     * @return
     */
    public static CompletableFuture<List<Status>> getLocationTweets(String latitude, String longitude) throws Exception{

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
            result = twitter.search(query);
            tweets = (ArrayList<Status>) result.getTweets();
        }
        future.complete(tweets);
        return future;
    }

    /**
     * Gets future of user objects
     * @author Kritika
     * @param username name of user for which profile is generated
     * @return future of user object 
     */

    public static CompletableFuture<User> getUser(String username) throws Exception{
        CompletableFuture<User> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        User user=null;
        user = twitter.showUser(username);
        future.complete(user);
        System.out.println("User = " + user);
        return future;
    }
}