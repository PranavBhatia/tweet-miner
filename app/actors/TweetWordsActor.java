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

public class TweetWordsActor extends AbstractActor{
	public static Props props() {
		return Props.create(TweetWordsActor.class);
	}
	
	static public class FindTweetWords{
		public final String query;
		
		public FindTweetWords(String query) {
			this.query = query;
		}
	}
	
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