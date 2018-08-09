package controllers;

import java.util.concurrent.CompletionStage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.inject.Inject;

import Model.UserModel;
import actors.SentimentActor;
import actors.TweetWordsActor;
import actors.TwitterActor;
import actors.TwitterActor.FindTweets;
import actors.HashtagActor;
import actors.HashtagActor.HashTagTweets;
import actors.LocationActor;
import actors.LocationActor.LocationTweets;
import actors.UserActor;
import actors.UserActor.UserProfile;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.mvc.Controller;
import play.mvc.*;
import static akka.pattern.PatternsCS.ask;
import views.html.*;
import java.util.List;
import twitter4j.Status;
import actors.TwitterActor.FindTweetWords;
import java.util.Map;


public class NewController extends Controller{

	final ActorRef twitterActor, sentimentActor, tweetWordsActor, hashtagActor, locationActor, userActor ;
	
	@Inject public NewController(ActorSystem system) {
		sentimentActor = system.actorOf(SentimentActor.props());
		tweetWordsActor = system.actorOf(TweetWordsActor.props());
		twitterActor = system.actorOf(TwitterActor.props(sentimentActor, tweetWordsActor));
		hashtagActor = system.actorOf(HashtagActor.props());
		locationActor = system.actorOf(LocationActor.props());
		userActor = system.actorOf(UserActor.props());
	}
	
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
	    		 .thenApply(tweetUser -> ok(user.render((UserModel)tweetUser)));
	 }
	 
	 public CompletionStage<Result> getTweetWords (String query){
		 return ask(twitterActor, new FindTweetWords(query), 1000)
				 .thenApply(tweetWordCount -> ok(tweetWords.render((Map<String, Long>)tweetWordCount, query)));
	 }
}
