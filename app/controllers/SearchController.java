package controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.swing.text.html.HTML;

import Model.TweetModel;
import Model.TweetWordsModel;
import play.mvc.*;

import views.html.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import services.TwitterService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class SearchController extends Controller {

    public CompletionStage<Result> search(String keywords){
        return TwitterService.getTweets(keywords, 10).thenApply(tweets->ok(search.render(tweets, keywords)));
    }
    
    public CompletionStage<Result> tweetWords(String searchWords) {
        return TwitterService.getTweets(searchWords, 100).thenApply(
        					tweets->TweetWordsModel.wordLevelStatistic(tweets))
        					.thenApply(words -> ok(tweetWords.render(words)));
    }
    
    public Result locationTweets(String location) {
    	return ok(location); //TwitterService.getLocationTweets
    }
    
    public Result userProfile(String username) {
    	return ok(username); //TwitterService.getProfile
    }
    
    public Result hashtags(String hashtag) {
    	return ok(hashtag); //TwitterService.getHashtagTweets
    }
}
