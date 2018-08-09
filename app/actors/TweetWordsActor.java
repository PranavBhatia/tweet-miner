package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.TwitterModule;
import services.TweetsService;
import java.util.List;
import twitter4j.User;
import twitter4j.Status;
import Model.TweetWordsModel;

public class TweetWordsActor extends AbstractActor{
	public static Props props() {
		return Props.create(TweetWordsActor.class);
	}
	
	static public class TweetWords{
		public final String query;
		public final ArrayNode tweetList;
		
		public TweetWords(String query, ArrayNode tweetList) {
			this.query = query;
			this.tweetList = tweetList;
		}
	}
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TweetWords.class, tw -> {
					sender().tell(TweetWordsModel.tweetWords(tw.tweetList), ActorRef.noSender());
				})
				.build();
	}
}
