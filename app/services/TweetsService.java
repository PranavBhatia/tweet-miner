package services;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Guice;


import Model.SentimentCompute;
import Model.UserModel;
import modules.TwitterModule;
import play.libs.Json;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class TweetsService  {
	
	@Inject TwitterApi twitterApi;

	/**
	 * @author v6
	 * use the twitter api to retrieve tweets based on a keyword
	 * @param keyword query from which results will be obtained
	 * @param limit number of tweets to be returned
	 * @return future of a list of json objects
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
    public ArrayNode getTweets(String keyword, int limit) throws Exception{
    			
    	Twitter twitterInstance = twitterApi.getTwitterInstance();
    	
        Query query = new Query(keyword);
        query.setCount(limit);

        QueryResult result = null;
        result = twitterInstance.search(query);
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
        return tweetsArrayNode;
    }

    /**
     * @author shireen
     * Obtains tweets from twitter api based on hashtags
     * @param hashtag tweets will be based on hashtags
     * @return future of a list of json objects containing tweets
     */
    
    public List<Status> getHashtagTweets(String hashtag) throws Exception{
        CompletableFuture<List<Status>> future = new CompletableFuture<>();
        Twitter twitter = twitterApi.getTwitterInstance();
        Query query = new Query(hashtag);
        query.setCount(10);
        QueryResult result = null;
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        return tweets;
    }

    /**
     * @author Pranav Bhatia
     * Obtains tweets from twitter api based on user's geolocation
     * @param latitude geolocation attribute of tweeting user
     * @param longitude
     * @return
     */
    
    public List<Status> getLocationTweets(String latitude, String longitude) throws Exception{

        //For testing -> http://localhost:9000/getLocation/28.56929189/%2077.31774961
        Twitter twitter = twitterApi.getTwitterInstance();
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
        return tweets;
    }
	
    /**
     * Gets future of user objects
     * @author Kritika
     * @param username name of user for which profile is generated
     * @return future of user object 
     */

    public UserModel getUser(String username) throws Exception{
        Twitter twitter = twitterApi.getTwitterInstance();
        User user=twitter.showUser(username);
        List<Status> userTweets = (ArrayList<Status>) twitter.getUserTimeline(username,new Paging(1,10));

        UserModel userModel = new UserModel(user, userTweets);
        return userModel;
    } 

    /**
     * @author kritika
     * Gets user tweets based on username
     * @param username tweets retrieved from user with this username
     * @return a list of status objects storing tweets
     */
    public List<Status> getUserTweets(String username) {

        Twitter twitter = twitterApi.getTwitterInstance();
        ArrayList<Status> userTweets = new ArrayList<>();
        try {
            userTweets = (ArrayList<Status>) twitter.getUserTimeline(username,new Paging(1,10));}catch (Exception e){}
        return userTweets;
    } 
}