package actors;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;

import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.TwitterModule;
import services.TweetsService;
import java.util.List;
import twitter4j.User;
import twitter4j.Status;
import Model.UserModel;


public class UserActor extends AbstractActor {
	public static Props props() {
		return Props.create(UserActor.class);
	}
	
	static public class UserProfile{
		public final String username;
		
		public UserProfile(String username) {
			this.username = username;
		}
	}


	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(UserProfile.class, up -> {
					Injector guice = Guice.createInjector(new TwitterModule());
					TweetsService service = guice.getInstance(TweetsService.class);
					
					UserModel userProfile = service.getUser(up.username);
										
					sender().tell(userProfile, self());
					
				})
				
				.build();
	}
}
