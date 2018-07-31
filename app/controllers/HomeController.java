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
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Welcome to TweetMiner"));
    }


    public CompletionStage<Result> search(String keywords) throws Exception{
    	 return TweetsService.getTweets(keywords,100)
         		.thenCompose( (f) -> CompletableFuture.supplyAsync( () -> SentimentCompute.smileyLevelStatistic(f) ))     
         		.thenApplyAsync( (tweets) -> ok(tweets));
    }

    public CompletionStage<Result> getHashtags(String hashtag) {
        return TweetsService.getHashtagTweets(hashtag).thenApplyAsync(tweets -> ok(locationTweets.render(tweets, "Hashtag Tweets")));
    }

    public CompletionStage<Result> getLocation(String latitude, String longitude){
        return TweetsService.getLocationTweets(latitude, longitude).thenApplyAsync(tweets -> ok(locationTweets.render(tweets, "Location Tweets")));
    }

    public CompletionStage<Result> getUserProfile(String username) throws TwitterException{
        return TweetsService.getUser(username).thenApplyAsync(tweetuser -> ok(userProfile.render(tweetuser, getUserTweets(username))));
    }

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

    public CompletableFuture<Result> getTweetWords(String query) throws Exception {
        return TweetsService.getTweets(query, 100)
                .thenApply(tweets->ok(tweetWords.render(TweetWordsModel.tweetWords(tweets), query)));
    }

    public CompletionStage<Result> getSentiment(String keywords) throws Exception {
    	
    	//SentimentCompute.smileyLevelStatistic(TweetsService.getTweets(keywords,100).get());
    
    			
        return TweetsService.getTweets(keywords,100).thenApplyAsync(tweets -> ok(tweets)); //TwitterService.getUserName
    }
}