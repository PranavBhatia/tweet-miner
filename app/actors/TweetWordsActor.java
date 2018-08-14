package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.inject.Guice;
import com.google.inject.Injector;
import services.TweetsService;
import java.util.List;
import twitter4j.User;
import twitter4j.Status;
import Model.TweetWordsModel;
import services.TwitterService;
/**
 * This actor class contains an actor that handles the TweetWords related feature of the application.
 * This return the count of the words from the list of returned tweets
 */
public class TweetWordsActor extends AbstractActor{
	/**
	 * This sets the properties of the TweetWordsActor.
	 * @return
	 * @author Nileesha Fernando
	 */
	public static Props props() {
		return Props.create(TweetWordsActor.class);
	}
	/**
	 * This is the FindTweetWords class which finds the tweet words
	 * and their count related to the list of tweets returned.
	 */
	static public class FindTweetWords{
		public final String query;
		
		public FindTweetWords(String query) {
			this.query = query;
		}
	}
	/**
	 * This contains the information of the action that
	 * an actor needs to perform when it gets the message
	 * related to the tweet words of the tweets belonging to the searched keyword
	 * @author Nileesha Fernando
	 * @return
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(FindTweetWords.class, ftw -> {
					ArrayNode tweetsList = TwitterService.getTweets(ftw.query, 100);
					sender().tell(TweetWordsModel.tweetWords(tweetsList), ActorRef.noSender());
				})
				.build();
	}
}