package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import services.TweetsService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.TwitterModule;
import java.util.List;
import twitter4j.Status;

public class HashtagActor extends AbstractActor {
	public static Props props() {
		return Props.create(HashtagActor.class);
	}
	
	static public class HashTagTweets {
		
		public final String hashtag;
		
		public HashTagTweets(String hashtag) {
			this.hashtag = hashtag;
		}
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(HashTagTweets.class, htt->{
					Injector guice = Guice.createInjector(new TwitterModule());
					TweetsService service = guice.getInstance(TweetsService.class);
					
					List<Status> tweets = service.getHashtagTweets(htt.hashtag);
					
					sender().tell(tweets, ActorRef.noSender());
					
				})
				.build();
	}
}
