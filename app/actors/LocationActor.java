package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.google.inject.Guice;
import com.google.inject.Injector;
import services.TwitterService;

import java.util.List;

import twitter4j.Status;

/**
 * This actor class contains an actor that handles the Location related feature of the application.
 * All the tweets corresponding to the location are received and
 * displayed on the screen.
 */
public class LocationActor extends AbstractActor {
    //final ActorRef locationActor = system.actorOf(LocationActor.props());
    /**
     * This sets the properties of the Location actor.
     * @return
     * @author Pranav Bhatia
     */
    public static Props props() {
        return Props.create(LocationActor.class);
    }
    /**
     * This is the LocationTweets class which sets the location latitude and longitude
     */
    static public class LocationTweets {
        public final String latitude, longitude;

        public LocationTweets(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
    /**
     * This class contains the information of the action that
     * an actor needs to perform when it gets the message
     * related to the location tweets belonging to that latitude and longitude
     * @author Pranav Bhatia
     * @return
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(LocationTweets.class, lt -> {
                    List<Status> locationTweets = TwitterService.getLocationTweets(lt.latitude, lt.longitude);
                    sender().tell(locationTweets, ActorRef.noSender());
                })
                .build();
    }
}