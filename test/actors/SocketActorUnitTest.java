package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import actors.SocketActor.TweetsWithSentiments;

import static org.junit.Assert.*;
import akka.testkit.javadsl.TestKit;
import play.libs.Json;

/**
 * testing the socket actor
 * @author v6
 *
 */
public class SocketActorUnitTest {
	public static ActorSystem system;
	public static ActorRef socketActor;
	public static SocketActor.TweetsWithSentiments tws;
    static ArrayNode arrayNode;


	/**
     * @author v6
     * Sets up test environment by creating actor system using the actor testkit
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        system = ActorSystem.create();
        socketActor = system.actorOf(SocketActor.props(socketActor));
        
        ObjectNode tempTweetsObjectNode1 = Json.newObject();
        tempTweetsObjectNode1.put("tweetsText", "I am happy 🙂");
        ObjectNode tempTweetsObjectNode2 = Json.newObject();
        tempTweetsObjectNode2.put("tweetsText", "I am happy 🙂");
        
        arrayNode = Json.newArray();
        
        arrayNode.add(tempTweetsObjectNode1);
        arrayNode.add(tempTweetsObjectNode2);
   	
        tws = new SocketActor.TweetsWithSentiments(arrayNode);
    }
    /***
     * @author v6
     * Shuts down actor system using actor testkit
     */
    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }
    
    /***
     * @author v6
     * test instantiating sentiments actor
     */    
    @Test
    public void testProps() {
    	tws = new SocketActor.TweetsWithSentiments(arrayNode);
        assertNotNull(tws);
    }
    /***
     * @author v6
     * tests message passing in the socket actor
     */
    @Test
    public void testCreateReceive() {
    	socketActor.tell(tws, ActorRef.noSender());
    	socketActor.tell(socketActor, ActorRef.noSender());
    	socketActor.tell(TweetsWithSentiments.class, ActorRef.noSender());
        assertNotNull(socketActor);
    }
    
}
