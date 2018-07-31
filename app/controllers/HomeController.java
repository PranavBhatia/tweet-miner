package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;

import Model.SentimentCompute;
import play.libs.Json;
import Model.TweetWordsModel;
import play.mvc.*;


import services.TweetsService;
import services.TwitterObject;
import twitter4j.*;
import views.html.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * 
 * This class contains the methods to fetch data from the twitter API
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     * 
     * This method renders the index page and displays the message passed in the render method().
     * @author pranav
     * @return Result 
     */
    public Result index() {
        return ok(index.render("Welcome to TweetMiner"));
    }

    /**
     * @author pranav
     * An action that returns a HTML page with data from 10 tweets
     * @param keywords user search query
     * @return 
     * @throws Exception
     */
    public CompletionStage<Result> search(String keywords) throws Exception{
    	 return TweetsService.getTweets(keywords,100)
         		.thenCompose( (f) -> CompletableFuture.supplyAsync( () -> SentimentCompute.smileyLevelStatistic(f) ))     
         		.thenApplyAsync( (tweets) -> ok(tweets));
    }

    /**
     * @author shireen
     * An action that renders a HTML page with tweets for a hashtag query
     * @param hashtag the hashtag with which the query is run
     * @return a Future of a result to be rendered to the HTML page
     */
    public CompletionStage<Result> getHashtags(String hashtag) throws Exception{
        return TweetsService.getHashtagTweets(hashtag).thenApplyAsync(tweets -> ok(locationTweets.render(tweets, "Hashtag Tweets")));
    }

    /**
     * @author pranav
     * An action that returns a HTML page with tweets from the specific geolocation
     * @param latitude  geolocation attribute of the owner of the tweet
     * @param longitude geolocation attribute of the owner of the tweet
     * @return a Future of a result to be rendered to the HTML page
     */
    public CompletionStage<Result> getLocation(String latitude, String longitude) throws Exception{
        return TweetsService.getLocationTweets(latitude, longitude).thenApplyAsync(tweets -> ok(locationTweets.render(tweets, "Location Tweets")));
    }

    /**
     * @author kritika
     * An action that returns a HTML page with the profile of the tweet owner
     * @param username the name of the user whose profile is retrieved
     * @return  a Future of a result to be rendered to the HTML page
     * @throws TwitterException
     */
    public CompletionStage<Result> getUserProfile(String username) throws Exception{
        return TweetsService.getUser(username).thenApplyAsync(tweetuser -> ok(userProfile.render(tweetuser, getUserTweets(username))));
    }

    /**
     * @author kritika
     * Gets user tweets based on username
     * @param username tweets retrieved from user with this username
     * @return a list of status objects storing tweets
     */
    public List<Status> getUserTweets(String username){

        Twitter twitter = TwitterObject.getInstance();
        ArrayList<Status> userTweets = new ArrayList<>();
        try {
            userTweets = (ArrayList<Status>) twitter.getUserTimeline(username,new Paging(1,10));
        }catch (TwitterException e){
            e.printStackTrace();
        }
        return userTweets;
    }

    /**
     * @author nileesha
     * An action that renders a HTML page with word level statistics for an individual query
     * @param query search terms for which word level statistics are generated
     * @return a future of a result to be rendered to an HTML page
     * @throws Exception
     */
    public CompletableFuture<Result> getTweetWords(String query) throws Exception {
    	return TweetsService.getTweets(query, 100)
    	    	.thenCompose(tweetsList -> CompletableFuture.supplyAsync(
    	    						()->TweetWordsModel.tweetWords(tweetsList))
    	    			    		.thenApply(tweets->ok(tweetWords.render(tweets, query))));
    }
}