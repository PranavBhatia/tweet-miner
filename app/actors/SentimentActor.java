package actors;

import com.fasterxml.jackson.databind.node.ArrayNode;

import akka.actor.AbstractActor;
import akka.actor.Props;
import modules.TwitterModule;
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
					sender().tell(SentimentCompute.smileyLevelStatistic(x.tweetsList), self());
				})
				.build();
	}

}
