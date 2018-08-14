package actors;

import com.fasterxml.jackson.databind.node.ArrayNode;
import actors.SocketActor.TweetsWithSentiments;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import Model.SentimentCompute;

/**
 * This actor class contains an actor that handles the sentiments related feature of the application.
 * The sentiments of the tweets received corresponding to the searched keyword
 */
public class SentimentActor extends AbstractActor {
	/**
	 * This sets the properties of the Sentiment Actor.
	 * @return
	 * @author Simran Sidhu
	 */
	public static Props props() {
		return Props.create(SentimentActor.class);
	}
	/**
	 * This is the ComputeSentiment class which sets the tweetList
	 */
	static public class ComputeSentiment {
		public final ArrayNode tweetsList;
		
		public ComputeSentiment(ArrayNode tweetList) {
			this.tweetsList = tweetList;
		}
	}

	/**
	 * This contains the information of the action that
	 * an actor needs to perform when it gets the message
	 * related to the sentiments of the tweets belonging to the searched keyword
	 * @author Simran Sidhu
	 * @return
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(ComputeSentiment.class, x -> {					
					sender().tell(new TweetsWithSentiments(SentimentCompute.smileyLevelStatistic(x.tweetsList)), ActorRef.noSender());
				})
				.build();
	}

}