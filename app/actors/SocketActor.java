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
	/**
	 * This actor class contains socket actor which is responsible
	 * for creating and communicating with other keywords.
	 * Also schedules an executerService which reactively
	 * fetches tweets in fixed interval.
	 */
	private static ActorRef socketOut;

	/**
	 * This sets the properties of the Socket Actor
	 * @return
	 * @author Simran Sidhu
	 */

	public static Props props(ActorRef socketOut) {
		return Props.create(SocketActor.class, socketOut);
	}
	/**
	 * This is the SocketActor class which sets the socket
	 */
	public SocketActor(ActorRef socketOut){
		System.out.println("creating actor...");
		this.socketOut = socketOut;
	}

	/**
	 * This is the TweetsWithSentiments class which displays the tweets along
	 * with the setiments for the returned list of tweets.
	 */

	static public class TweetsWithSentiments {
		
		private ArrayNode tweetsArrayNode;
		public TweetsWithSentiments (ArrayNode tweetsArrayNode) {
			this.tweetsArrayNode=tweetsArrayNode;
			
		}
		
	}
	/**
	 * This class contains the information of the action that
	 * an actor needs to perform when it gets the message
	 * related to the tweets belonging to the searched keyword
	 * @author Simran Sidhu
	 * @return
	 */
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

	/**
	 * This method fetches the Tweets and computes the sentiments
	 * @param keyword
	 */
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
