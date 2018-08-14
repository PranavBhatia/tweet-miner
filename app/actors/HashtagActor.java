package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import services.TwitterService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.List;
import twitter4j.Status;

/**
 * This actor class contains an actor that handles the hashtag feature of the application.
 * All the tweets corresponding to the hashtag are received and
 * displayed on the screen.
 */

public class HashtagActor extends AbstractActor {
	/**
	 * This sets the properties of the hastag actor.
	 * @return
	 * @author Shireen Koul
	 */
	public static Props props() {
		return Props.create(HashtagActor.class);
	}

	/**
	 * This is the HashTagTweets class which sets the hashtag keyword
	 */
	static public class HashTagTweets {
		
		public final String hashtag;
		
		public HashTagTweets(String hashtag) {
			this.hashtag = hashtag;
		}
	}

	/**
	 * This class contains the information of the  action that
	 * an actor needs to perform when it gets the message
	 * @author Shireen Koul
	 * @return
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(HashTagTweets.class, htt->{
					
					List<Status> tweets = TwitterService.getHashtagTweets(htt.hashtag);
					sender().tell(tweets, ActorRef.noSender());
					
				})
				.build();
	}
}