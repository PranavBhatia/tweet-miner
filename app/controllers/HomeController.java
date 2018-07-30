package controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Model.SentimentCompute;
import Model.TweetWordsModel;
import play.libs.Json;
import play.mvc.*;


import services.TweetsService;
import twitter4j.*;
import twitter4j.api.SearchResource;
import views.html.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

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

//    public CompletionStage<Result> search(String keywords){
//        return TwitterService.getTweets(keywords, 10).thenApplyAsync(tweets -> {
//            ObjectNode result = Json.newObject();
//            result.put("data", result);
//            return ok(result);
//        });
//    }

    public CompletionStage<Result> search(String keywords) throws Exception {
    	SentimentCompute.smileyLevelStatistic(TweetsService.getTweets(keywords,10).get());
        return TweetsService.getTweets(keywords, 10).thenApplyAsync(tweets -> ok(tweets));
    }

    public Result getHashtags(String hashtag) {
        return ok("Reached : " + hashtag); //TwitterService.getHashtagTweets

    }
    public Result getLocation(String location) {
        return ok("Reached : " + location); //TwitterService.getLocation
    }

    public Result getUserName(String username) {
        return ok("Reached : " + username); //TwitterService.getUserName
    }

    public CompletableFuture<Result> getTweetWords(String query) throws Exception {
    		return TweetsService.getTweets(query, 100)
    		.thenApply(tweets->ok(tweetWords.render(TweetWordsModel.tweetWords(tweets), query)));
    	
    }

    public Result getSentiment(String query) {
        return ok("Reached : " + query); //TwitterService.getUserName
    }
 /*   public CompletionStage<Result> message() {
        return ok("PB's welcome page ");
    }*/
}