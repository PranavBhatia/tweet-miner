package actors;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, keyword -> {
					System.out.println("Reached socket actor 1");
					fetchTweets(keyword);
				})
				.build();
	}

	private void fetchTweets(String keyword) {
		Runnable tweetJob = new Runnable() {

			@Override
			public void run() {

				ArrayNode tweetsArrayNode = null;
				try {
					System.out.println("Reached socket actor 2"+keyword);
					tweetsArrayNode = TwitterService.getTweets(keyword, 10);
					System.out.println(tweetsArrayNode.get(0).get("tweetsText").textValue());
				} catch (Exception e) {
					e.printStackTrace();
				}

				socketOut.tell(tweetsArrayNode.toString(), self());
			}
		};
		 ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		 executor.scheduleAtFixedRate(tweetJob, 0, 30, TimeUnit.SECONDS);
	}
}
