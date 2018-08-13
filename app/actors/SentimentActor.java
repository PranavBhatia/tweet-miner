package actors;

import com.fasterxml.jackson.databind.node.ArrayNode;
import actors.SocketActor.TweetsWithSentiments;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import Model.SentimentCompute;

public class SentimentActor extends AbstractActor {

	public static Props props() {
		return Props.create(SentimentActor.class);
	}
	
	static public class ComputeSentiment {
		public final ArrayNode tweetsList;
		
		public ComputeSentiment(ArrayNode tweetList) {
			this.tweetsList = tweetList;
		}
	}
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(ComputeSentiment.class, x -> {					
					sender().tell(new TweetsWithSentiments(SentimentCompute.smileyLevelStatistic(x.tweetsList)), ActorRef.noSender());
				})
				.build();
	}

}