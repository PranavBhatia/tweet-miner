package services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Guice;
import com.google.inject.Injector;

import Model.UserModel;
import play.libs.Json;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

public class TwitterService {
	
	/**
	 * @author v6
	 * use the twitter api to retrieve tweets based on a keyword
	 * @param keyword query from which results will be obtained
	 * @param limit number of tweets to be returned
	 * @return future of a list of json objects
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	//TwitterModule
	static Injector guice = Guice.createInjector(new TwitterModule());
	static TwitterObject twitserv = guice.getInstance(TwitterObject.class);
	
    public static ArrayNode getTweets(String keyword, int limit) throws Exception{
    	   	  	    	
        System.out.println("getTweets");
        System.out.println("twitserv: "+twitserv.hashCode());
        Twitter twitter = twitserv.getTwitterInstance();
        System.out.println("Injected by Class: "+ twitserv.injectedByClass);
        System.out.println("twitter :"+ twitter.hashCode());
        
        
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
        return tweetsArrayNode;
    }

    /**
     * @author shireen
     * Obtains tweets from twitter api based on hashtags
     * @param hashtag tweets will be based on hashtags
     * @return future of a list of json objects containing tweets
     */

    public static List<Status> getHashtagTweets(String hashtag) throws Exception{
        System.out.println("getHashtagTweets");
        System.out.println("twitserv: "+twitserv.hashCode());
        System.out.println("Injected by Class: "+ twitserv.injectedByClass);
        Twitter twitter = twitserv.getTwitterInstance();
        System.out.println("twitter :"+ twitter.hashCode());
        System.out.println("----------------------------------");
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
    public static List<Status> getLocationTweets(String latitude, String longitude) throws Exception{

        //For testing -> http://localhost:9000/getLocation/28.56929189/%2077.31774961
        System.out.println("getLocationTweets");
        System.out.println("twitserv: "+twitserv.hashCode());
        System.out.println("Injected by Class: "+ twitserv.injectedByClass);
        Twitter twitter = twitserv.getTwitterInstance();
        System.out.println("twitter :"+ twitter.hashCode());
        System.out.println("----------------------------------");
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

    public static UserModel getUser(String username) throws Exception{
        Twitter twitter = twitserv.getTwitterInstance();
        User user=twitter.showUser(username);
        List<Status> userTweets = (ArrayList<Status>) twitter.getUserTimeline(username,new Paging(1,10));

        UserModel userModel = new UserModel(user, userTweets);
        return userModel;
    } 


}