package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import actors.SentimentActor.ComputeSentiment;

import static org.junit.Assert.*;
import akka.testkit.javadsl.TestKit;
import play.libs.Json;
import services.TwitterObject;

public class SentimentActorUnitTest {
	public static ActorSystem system;
    public static ActorRef sentimentActor;
    public static ComputeSentiment computeSentiment;
    static ArrayNode arrayNode;
    /**
     * @author simran
     * Sets up test environment by creating actor system using the actor testkit
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        TwitterObject.testCase = true;
        TwitterObject.emotion = 1;
    	 ObjectNode tempTweetsObjectNode1 = Json.newObject();
         tempTweetsObjectNode1.put("tweetsText", "I am happy 🙂");
         ObjectNode tempTweetsObjectNode2 = Json.newObject();
         tempTweetsObjectNode2.put("tweetsText", "I am happy 🙂");
         
         arrayNode = Json.newArray();
         
         arrayNode.add(tempTweetsObjectNode1);
         arrayNode.add(tempTweetsObjectNode2);
    	
        system = ActorSystem.create();
        sentimentActor = system.actorOf(TweetWordsActor.props());
        computeSentiment = new ComputeSentiment(arrayNode);
    }
    
    /***
     * @author simran
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
     * @author simran
     * test instantiating sentiments actor
     */    
    @Test
    public void testProps() {
        computeSentiment = new SentimentActor.ComputeSentiment(arrayNode);
        assertNotNull(computeSentiment);
    }
    
    /***
     * @author simran
     * tests message passing in the sentiment actor
     */
    @Test
    public void testCreateReceive() {
    	sentimentActor.tell(computeSentiment, ActorRef.noSender());
    	sentimentActor.tell(sentimentActor, ActorRef.noSender());
    	sentimentActor.tell(SentimentActor.class, ActorRef.noSender());
        assertNotNull(sentimentActor);
    }
    

}
