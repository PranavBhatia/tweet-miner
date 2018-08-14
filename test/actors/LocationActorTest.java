package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import akka.testkit.javadsl.TestKit;
import services.TwitterObject;

/***
 * test class for location actor
 * @author pranav
 *
 */

public class LocationActorTest {

    public static ActorSystem system;
    public static ActorRef locationActor;
    public static LocationActor.LocationTweets locationTweets;

    /**
     * @author pranav
     * Sets up test environment by creating actor system using the actor testkit
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        system = ActorSystem.create();
        TwitterObject.testCase = true;
        TwitterObject.emotion = 1;
        locationActor = system.actorOf(LocationActor.props());
        locationTweets = new LocationActor.LocationTweets("12","89");
    }

    /***
     * @author pranav
     * Shuts down actor system using actor testkit
     */
    @AfterClass
    public static void teardown() {
        TwitterObject.testCase = false;
        TwitterObject.emotion = 0;
        TestKit.shutdownActorSystem(system);
        system = null;
    }
    
    /***
     * @author pranav
     * test instantiating location actor
     */    
    @Test
    public void testProps() {
        locationTweets = new LocationActor.LocationTweets("12","89");
        assertNotNull(locationTweets);
    }

    /***
     * @author pranav
     * tests message passing between location actor
     */    
    @Test
    public void testCreateReceive() {
        locationActor.tell(locationTweets, ActorRef.noSender());
        locationActor.tell(locationActor, ActorRef.noSender());
        locationActor.tell(LocationActor.LocationTweets.class, ActorRef.noSender());
        assertNotNull(locationActor);
    }
}