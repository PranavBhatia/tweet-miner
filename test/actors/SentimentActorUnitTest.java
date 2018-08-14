package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static akka.pattern.PatternsCS.ask;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import actors.SentimentActor.ComputeSentiment;

import static org.junit.Assert.*;
import play.test.Helpers;
import java.util.concurrent.CompletionStage;

import akka.testkit.javadsl.TestKit;
import play.libs.Json;
import services.TwitterObject;
import services.TwitterService;

public class SentimentActorUnitTest {
	
	public static ActorSystem system;
    public static ActorRef sentimentActor;
    public static ComputeSentiment computeSentiment;
    public static ArrayNode arrayNode;
	
	
    /**
     * @author v6
     * Sets up test environment by creating actor system using the actor testkit
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
    	  TwitterObject.testCase = true;
          TwitterObject.emotion = 1;
          system = ActorSystem.create();
          sentimentActor = system.actorOf(SentimentActor.props());
          arrayNode=TwitterService.getTweets("dermicool", 10);
          computeSentiment = new ComputeSentiment(arrayNode);
    }
    
    /***
     * @author v6
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
     * @author v6
     * test instantiating sentiments actor
     */    
    @Test
    public void testProps() {
    	assertNotNull(SentimentActor.props());
    }
    
    /***
     * @author v6
     * tests message passing in the sentiment actor.
     */
    @Test
    public void testCreateReceive() {
    	
    	TestKit probe = new TestKit(system);    		
    	sentimentActor.tell(computeSentiment, probe.getRef());
        SocketActor.TweetsWithSentiments result=probe.expectMsgClass(SocketActor.TweetsWithSentiments.class);
    	String actual_result=result.getTweetsArrayNode().get(0).get("sentiments").textValue();
        String expected_sentiment=":-)";
        assertEquals(actual_result,expected_sentiment);
    }
}
