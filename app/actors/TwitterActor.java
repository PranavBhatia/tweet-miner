package actors;


import com.google.inject.Guice;
import com.google.inject.Injector;
import services.TweetsService;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import services.TwitterApi;
import modules.TwitterModule;
import actors.SentimentActor.ComputeSentiment; 
import com.fasterxml.jackson.databind.node.ArrayNode;
import actors.TweetWordsActor.TweetWords;

public class TwitterActor extends AbstractActor{
	
	public static Props props(ActorRef sentimentActor, ActorRef tweetWordActor) {
		return Props.create(TwitterActor.class, ()->new TwitterActor(sentimentActor, tweetWordActor));
	}
	
	private ActorRef sentimentActor;
	private ActorRef tweetWordActor;
	
	public TwitterActor(ActorRef sentimentActor, ActorRef tweetWordActor) {
		this.sentimentActor = sentimentActor;
		this.tweetWordActor = tweetWordActor;
	}
	
	Injector guice = Guice.createInjector(new TwitterModule());
	TweetsService service = guice.getInstance(TweetsService.class);
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(FindTweets.class, ft ->{
					ArrayNode tweetsList = service.getTweets(ft.query, 100);
					sentimentActor.tell(new ComputeSentiment(tweetsList), sender());
				})
				.match(FindTweetWords.class, ftw -> {
					ArrayNode tweetsList = service.getTweets(ftw.query, 100);
					tweetWordActor.tell(new TweetWords(ftw.query, tweetsList), sender());
				})
				.build();
	}

	static public class FindTweets{
		public final String query;
		
		public FindTweets(String query) {
			this.query = query;
		}
	}
	
	static public class FindTweetWords{
		public final String query;
		
		public FindTweetWords(String query) {
			this.query = query;
		}
	}
	
}
