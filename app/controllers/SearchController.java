package controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import Model.TweetModel;
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
        return TwitterService.getTweets(keywords).thenApplyAsync((tweets->ok(search.render(tweets))));
    }

}
