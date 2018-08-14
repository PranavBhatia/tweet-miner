package actors;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;

import com.google.inject.Guice;
import com.google.inject.Injector;
import services.TwitterService;
import java.util.List;
import twitter4j.User;
import twitter4j.Status;
import Model.UserModel;

/**
 * This actor class contains an actor that handles the UserActor related feature of the application.
 * This returns the details of the twitter user.
 */
public class UserActor extends AbstractActor {
	/**
	 * This sets the properties of the User Actor.
	 * @return
	 * @author Kritika Sharma
	 */
	public static Props props() {
		return Props.create(UserActor.class);
	}
	/**
	 * This is the UserProfile class which finds the details of the user
	 * and sets their user name
	 */
	static public class UserProfile{
		public final String username;
		
		public UserProfile(String username) {
			this.username = username;
		}
	}
	/**
	 * This contains the information of the action that
	 * an actor needs to perform when it gets the message
	 * related to the user profile of the twitter user belonging to the searched keyword
	 * @author Kritika Sharma
	 * @return
	 */

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(UserProfile.class, up -> {
					
					UserModel userProfile = TwitterService.getUser(up.username);
										
					sender().tell(userProfile, self());
					
				})
				
				.build();
	}
}