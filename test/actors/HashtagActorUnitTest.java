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
 * test class for hashtag actor
 * @author shireen
 *
 */
public class HashtagActorUnitTest {
	 public static ActorSystem system;
	    public static ActorRef hashtagActor;
	    public static HashtagActor.HashTagTweets hashtagTweets;

	    /**
	     * @author shireen
	     * Sets up test environment by creating actor system using the actor testkit
	     * @throws Exception
	     */
	    @BeforeClass
	    public static void setUp() throws Exception {
	        system = ActorSystem.create();
			TwitterObject.testCase = true;
			TwitterObject.emotion = 1;
	        hashtagActor = system.actorOf(UserActor.props());
	        hashtagTweets = new HashtagActor.HashTagTweets("dermicool");
	    }

	    /***
	     * @author shireen
	     * Shuts down actor system using actor testkit
	     */
	    @AfterClass
	    public static void teardown() {
            TwitterObject.testCase = false;
            TwitterObject.emotion = 0;
	        TestKit.shutdownActorSystem(system);
	        system = null;
	    }

	    /**
	     * @author shireen
	     * test instantiating hashtag actor
	     */
	    @Test
	    public void testProps() {
	        hashtagTweets = new HashtagActor.HashTagTweets("dermicool");
	        assertNotNull(hashtagTweets);
	    }

	    /***
	     * @author simran
	     * tests message passing in the hashtag actor
	     */
	    @Test
	    public void testCreateReceive() {
	        hashtagActor.tell(hashtagTweets, ActorRef.noSender());
	        hashtagActor.tell(hashtagActor, ActorRef.noSender());
	        hashtagActor.tell(HashtagActor.HashTagTweets.class, ActorRef.noSender());
	        assertNotNull(hashtagActor);
	    }
}
