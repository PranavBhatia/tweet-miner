package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import akka.testkit.javadsl.TestKit;

import org.junit.BeforeClass;

import actors.TweetWordsActor;
import actors.TweetWordsActor.FindTweetWords;

/***
 * Class to test TweetWords actor
 * @author nileesha
 *
 */
public class TweetWordsActorTest {
	public static ActorSystem system;
    public static ActorRef tweetWordsActor;
    public static FindTweetWords tweetWords;
    
    /**
     * @author nileesha
     * Sets up test environment by creating actor system using the actor testkit
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        system = ActorSystem.create();
        tweetWordsActor = system.actorOf(TweetWordsActor.props());
        tweetWords = new FindTweetWords("Canada");
    }
    
    /***
     * @author nileesha
     * Shuts down actor system using actor testkit
     */
    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }
    
    /***
     * @author nileesha
     * test instantiating tweet words actor
     */    
    @Test
    public void testProps() {
        tweetWords = new TweetWordsActor.FindTweetWords("Canada");
        assertNotNull(tweetWords);
    }
    
    /***
     * @author nileesha
     * tests message passing in the tweetwords actor
     */
    @Test
    public void testCreateReceive() {
    	tweetWordsActor.tell(tweetWords, ActorRef.noSender());
    	tweetWordsActor.tell(tweetWordsActor, ActorRef.noSender());
    	tweetWordsActor.tell(FindTweetWords.class, ActorRef.noSender());
        assertNotNull(tweetWordsActor);
    }
    
    @Test
    public void testFindTweetWords() {
    	String query = "Hello";
    	FindTweetWords ftw = new FindTweetWords(query);
    	assertEquals(ftw.query, query);
    }

}
