package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.inject.Inject;

import Model.UserModel;
import actors.SentimentActor;
import actors.TweetWordsActor;
import actors.HashtagActor;
import actors.HashtagActor.HashTagTweets;
import actors.LocationActor;
import actors.LocationActor.LocationTweets;
import actors.UserActor;
import actors.SocketActor;
import actors.UserActor.UserProfile;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
/*import akka.event.Logging;
import akka.event.LoggingAdapter;*/
import play.mvc.Controller;
import play.libs.F;
import play.mvc.*;
import static akka.pattern.PatternsCS.ask;
import views.html.*;
import java.util.List;
import twitter4j.Status;
import java.util.Map;
import org.slf4j.Logger;

import play.libs.streams.ActorFlow;

import play.libs.F;
import akka.actor.*;
import akka.stream.*;
import play.mvc.*;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import actors.TweetWordsActor.FindTweetWords;


import akka.actor.AbstractActor;

/***
 * @author v6
 *This controller contains an action to handle HTTP requests
 * to the application's home page.
 * This class contains the methods to fetch data from the twitter API
 */
public class NewController extends Controller{

	public static ActorRef sentimentActor, tweetWordsActor, hashtagActor, locationActor, userActor ;

	@Inject private ActorSystem actorSystem;
    @Inject private Materializer materializer; 
    
	@Inject public NewController(ActorSystem system) {
		sentimentActor = system.actorOf(SentimentActor.props());
		tweetWordsActor = system.actorOf(TweetWordsActor.props());
		hashtagActor = system.actorOf(HashtagActor.props());
		locationActor = system.actorOf(LocationActor.props());
		userActor = system.actorOf(UserActor.props());
	}
	
	/**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     * <p>
     * This method renders the index page and displays the message passed in the render method().
     *
     * @return Result
     * @author v6
     */
	 public Result index() {
	        return ok(index.render("Welcome to TweetMiner"));
	    }
	    
	public CompletionStage<Result> getHashtags(String hashtag){
		return ask(hashtagActor, new HashTagTweets(hashtag), 5000)
				.thenApply(hashtagTweets -> ok(locationTweets.render((List<Status>)hashtagTweets, "Hashtag Tweets")));
	}
	
	public CompletionStage<Result> getLocation(String latitude, String longitude){
        return ask(locationActor, new LocationTweets(latitude, longitude), 5000)
        		.thenApply(tweets -> ok(locationTweets.render((List<Status>)tweets, "Location Tweets")));
    }
	
	 public CompletionStage<Result> getUserProfile(String username) throws Exception{
	     return ask(userActor, new UserProfile(username), 5000)
	    		 .thenApply(tweetUser -> ok(user.render((UserModel)tweetUser)));
	 }
	 
	 public CompletionStage<Result> getTweetWords (String query){
		 return ask(tweetWordsActor, new FindTweetWords(query), 5000)
				 .thenApply(tweetWordCount -> ok(tweetWords.render((Map<String, Long>)tweetWordCount, query)));
	 }
	 
	 public WebSocket ws() {
		 	System.out.println("testing ws...");
	        return WebSocket.Text.acceptOrResult(request -> {
	            if (sameOriginCheck(request)) {
	                return CompletableFuture.completedFuture(
	                        F.Either.Right(ActorFlow.actorRef(SocketActor::props,
	                                actorSystem, materializer)));
	            } else {
	                return CompletableFuture.completedFuture(F.Either.Left(forbidden()));
	            }
	        });
	    }
	 private boolean sameOriginCheck(Http.RequestHeader rh) {
	        final Optional<String> origin = rh.header("Origin");

	        if (! origin.isPresent()) {
	            //logger.error("originCheck: rejecting request because no Origin header found");
	        	System.out.println("originCheck: rejecting request because no Origin header found");
	            return false;
	        } else if (originMatches(origin.get())) {
	            //logger.debug("originCheck: originValue = " + origin);
	        	System.out.println("originCheck: originValue = " + origin);
	            return true;
	        } else {
	            //logger.error("originCheck: rejecting request because Origin header value " + origin + " is not in the same origin");
	        	System.out.println("originCheck: rejecting request because Origin header value " + origin + " is not in the same origin");
	        	return false;
	        }
	    }

	    private boolean originMatches(String origin) {
	        return origin.contains("localhost:9000") || origin.contains("localhost:19001");
	    }
}
