package controllers;

import java.util.List;

import play.mvc.*;

import views.html.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;
import services.TwitterService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class SearchController extends Controller {

    public Result search(String keywords){
        List<Status> tweets = TwitterService.getTweets(keywords);
        return ok(tweets.get(0).toString());
    }

}
