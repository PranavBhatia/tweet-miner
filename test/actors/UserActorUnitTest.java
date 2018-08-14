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
 * @author Kritika
 * test class for user actor
 */
public class UserActorUnitTest {

    public static ActorSystem system;
    public static ActorRef userActor;
    public static UserActor.UserProfile userTweets;

    /**
     * @author kritika
     * Sets up test environment by creating actor system using the actor testkit
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        system = ActorSystem.create();
        TwitterObject.testCase = true;
        TwitterObject.emotion = 1;
        userActor = system.actorOf(UserActor.props());
        userTweets = new UserActor.UserProfile("Nileesha");
    }

    /**
     * @author kritika
     * Sets up test environment by creating actor system using the actor testkit
     * @throws Exception
     */
    @AfterClass
    public static void teardown() {
        TwitterObject.testCase = false;
        TwitterObject.emotion = 0;
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    /**
     * @author kritika
     * test instantiating user actor
     */
    @Test
    public void testProps() {
        userTweets = new UserActor.UserProfile("Nileesha");
        assertNotNull(userTweets);
    }
    /**
     * tests message passing in user actor
     */
    @Test
    public void testCreateReceive() {
        userActor.tell(userTweets, ActorRef.noSender());
        userActor.tell(userActor, ActorRef.noSender());
        userActor.tell(UserActor.UserProfile.class, ActorRef.noSender());
        assertNotNull(userActor);
    }
}