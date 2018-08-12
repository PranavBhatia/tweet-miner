package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.google.inject.Guice;
import com.google.inject.Injector;
import services.TwitterService;

import java.util.List;

import twitter4j.Status;


public class LocationActor extends AbstractActor {
    //final ActorRef locationActor = system.actorOf(LocationActor.props());

    public static Props props() {
        return Props.create(LocationActor.class);
    }

    static public class LocationTweets {
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
                    List<Status> locationTweets = TwitterService.getLocationTweets(lt.latitude, lt.longitude);
                    sender().tell(locationTweets, ActorRef.noSender());
                })
                .build();
    }
}