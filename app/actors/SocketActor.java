package actors;

import static akka.pattern.PatternsCS.ask;

import java.util.List;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import actors.SentimentActor.ComputeSentiment;
import Model.SentimentCompute;
import controllers.NewController;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import play.libs.Json;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import services.TwitterService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SocketActor extends AbstractActor {
	
	private static ActorRef socketOut;
	
	public static Props props(ActorRef socketOut) {
		return Props.create(SocketActor.class, socketOut);
	}

	public SocketActor(ActorRef socketOut){
		System.out.println("creating actor...");
		this.socketOut = socketOut;
	}
	
	static public class TweetsWithSentiments {
		
		private ArrayNode tweetsArrayNode;
		public TweetsWithSentiments (ArrayNode tweetsArrayNode) {
			this.tweetsArrayNode=tweetsArrayNode;
			
		}
		
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, keyword -> {
					
					fetchTweets(keyword);
				})
				.match(TweetsWithSentiments.class, tws -> {
					socketOut.tell(tws.tweetsArrayNode.toString(), self());
					
				})
				.build();
	}

	private void fetchTweets(String keyword) {
		Runnable tweetJob = new Runnable() {

			@Override
			public void run() {

				
				try {
										
					System.out.println("Reached socket actor "+keyword);
					ArrayNode tweetsList = TwitterService.getTweets(keyword, 100);
					NewController.sentimentActor.tell(new ComputeSentiment(tweetsList), self());
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				
			}
		};
		 ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		 executor.scheduleAtFixedRate(tweetJob, 0, 10, TimeUnit.SECONDS);
	}
}
