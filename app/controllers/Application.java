package controllers;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.inject.*;

import com.fasterxml.jackson.databind.node.ArrayNode;

import Model.UserModel;
import actors.HashtagActor;
import actors.LocationActor;
import actors.SentimentActor;
import actors.TwitterActor;
import actors.TwitterActor.FindTweets;
import actors.TwitterActor.FindTweetWords;
import actors.TweetWordsActor;
import actors.UserActor;
import actors.HashtagActor.HashTagTweets;
import actors.LocationActor.LocationTweets;
import actors.UserActor.UserProfile;
import akka.actor.*;
import play.mvc.*;
import play.twirl.api.Content;
import services.TweetsService;
import twitter4j.Status;
import twitter4j.User;


import static akka.pattern.PatternsCS.ask;
import views.html.*;

@Singleton
public class Application extends Controller {
	final ActorRef twitterActor, sentimentActor, hashtagActor, locationActor, userActor, tweetWordsActor;
	
	@Inject public Application(ActorSystem system) {
		sentimentActor = system.actorOf(SentimentActor.props());
		tweetWordsActor = system.actorOf(TweetWordsActor.props());
		twitterActor = system.actorOf(TwitterActor.props(sentimentActor, tweetWordsActor));
		hashtagActor = system.actorOf(HashtagActor.props());
		locationActor = system.actorOf(LocationActor.props());
		userActor = system.actorOf(UserActor.props());
	}
	  /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     * 
     * This method renders the index page and displays the message passed in the render method().
     * @author Pranav Bhatia
     * @return Result 
     */
    public Result index() {
        return ok(index.render("Welcome to TweetMiner"));
    }
    
	public CompletionStage<Result> search(String keyword){
		return ask(twitterActor, new FindTweets(keyword), 1000)
				.thenApply(tweets -> ok((ArrayNode) tweets)); //ask sends a message asynchronously and returns a Future 
															  //representing a possible result
	}
	
	public CompletionStage<Result> getHashtags(String hashtag){
		return ask(hashtagActor, new HashTagTweets(hashtag), 1000)
				.thenApply(hashtagTweets -> ok(locationTweets.render((List<Status>)hashtagTweets, "Hashtag Tweets")));
	}
	
	public CompletionStage<Result> getLocation(String latitude, String longitude){
        return ask(locationActor, new LocationTweets(latitude, longitude), 1000)
        		.thenApply(tweets -> ok(locationTweets.render((List<Status>)tweets, "Location Tweets")));
    }
	
	 public CompletionStage<Result> getUserProfile(String username) throws Exception{
	     return ask(userActor, new UserProfile(username), 1000)
	    		 .thenApply(tweetUser -> ok(userProfile.render((UserModel)tweetUser)));
	 }
	 
	 public CompletionStage<Result> getTweetWords (String query){
		 return ask(twitterActor, new FindTweetWords(query), 1000)
				 .thenApply(tweetWordCount -> ok(tweetWords.render((Map<String, Long>)tweetWordCount, query)));
	 }
}
