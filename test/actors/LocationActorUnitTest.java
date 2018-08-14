package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import akka.testkit.javadsl.TestKit;

/***
 * test class for location actor
 * @author pranav
 *
 */
public class LocationActorUnitTest {

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
        locationActor = system.actorOf(LocationActor.props());
        locationTweets = new LocationActor.LocationTweets("12","89");
    }

    /***
     * @author pranav
     * Shuts down actor system using actor testkit
     */
    @AfterClass
    public static void teardown() {
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