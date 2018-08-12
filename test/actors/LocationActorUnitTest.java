package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import controllers.HomeController;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import akka.testkit.javadsl.TestKit;

public class LocationActorUnitTest {

    public static ActorSystem system;
    public static ActorRef locationActor;
    public static LocationActor.LocationTweets locationTweets;

    @BeforeClass
    public static void setUp() throws Exception {
        system = ActorSystem.create();
        locationActor = system.actorOf(LocationActor.props());
        locationTweets = new LocationActor.LocationTweets("12","89");
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testProps() {
        locationTweets = new LocationActor.LocationTweets("12","89");
        assertNotNull(locationTweets);
    }

    @Test
    public void testCreateReceive() {
        locationActor.tell(locationTweets, ActorRef.noSender());
        locationActor.tell(locationActor, ActorRef.noSender());
        locationActor.tell(LocationActor.LocationTweets.class, ActorRef.noSender());
        assertNotNull(locationActor);
    }
}