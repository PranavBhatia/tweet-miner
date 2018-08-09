package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.TwitterModule;
import services.TweetsService;
import java.util.List;
import twitter4j.Status;


public class LocationActor extends AbstractActor{

	public static Props props() {
		return Props.create(LocationActor.class);
	}
	
	static public class LocationTweets{
		public final String latitude, longitude;
		
		public LocationTweets(String latitude, String longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(LocationTweets.class, lt -> {
					Injector guice = Guice.createInjector(new TwitterModule());
					TweetsService service = guice.getInstance(TweetsService.class);
					
					List<Status> locationTweets = service.getLocationTweets(lt.latitude, lt.longitude);
					
					sender().tell(locationTweets, ActorRef.noSender());
					
				})
				.build();
	}

}
