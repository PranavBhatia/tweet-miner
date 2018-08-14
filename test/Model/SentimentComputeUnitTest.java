package Model;
import com.fasterxml.jackson.databind.node.ArrayNode;
import twitter4j.*;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import services.TwitterApi;
import services.TwitterService;
import services.TwitterObject;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import mockClasses.PseudoTwitterHappy;
import mockClasses.PseudoTwitterNeutral;
import mockClasses.PseudoTwitterSad;
import static play.inject.Bindings.bind;
import play.test.Helpers;
/**
 * @author Simran
 * Class to test all the method in SentimentCompute class
 */

public class SentimentComputeUnitTest {

	
	@BeforeClass
	public static void setUp() throws Exception {
   	 TwitterObject.testCase = true;

   }
	
    /**
     * @author Simran
     * Resets the environment by setting the TwitterObject's Parameter - testCase to false ensuring the remaining calls hit the Live Twitter API.
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
       	TwitterObject.testCase = false;
       	TwitterObject.emotion=0;
       	Helpers.stop(TwitterObject.testApp);
     }
    
    
    
    @Test
    public void testgetTweetEmojiHappy() throws Exception{
    	
    	TwitterObject.emotion=1;
    	ArrayNode testNode = TwitterService.getTweets("dermicool", 10);
    	String tweet=testNode.get(0).get("tweetsText").textValue();
        String expected_emotion=":-)";
        assertEquals(SentimentCompute.getTweetEmoji(tweet),expected_emotion);
    }
    
    
    @Test
    public void testgetTweetEmojiSad() throws Exception{
    	
    	TwitterObject.emotion=-1;
    	ArrayNode testNode = TwitterService.getTweets("dermicool", 10);
    	String tweet=testNode.get(0).get("tweetsText").textValue();         	
        String expected_emotion=":-(";
        assertEquals(SentimentCompute.getTweetEmoji(tweet),expected_emotion);
    }
    
  
    
    @Test
    public void testgetTweetEmojiNeutral() throws Exception{
    	
    	TwitterObject.emotion=2;
    	ArrayNode testNode = TwitterService.getTweets("dermicool", 10);
    	String tweet=testNode.get(0).get("tweetsText").textValue();    	
        String expected_emotion=":-|";
        assertEquals(SentimentCompute.getTweetEmoji(tweet),expected_emotion);
    }
    

    /**
     * @author Simran
     * tests smileyLevelStatistic method for :-) sentiment
     * @throws Exception
     */

    @Test
    public void testsmileyLevelStatistic_happy() throws Exception,TwitterException{
    	
    	TwitterObject.emotion=1;    	
        ArrayNode input = TwitterService.getTweets("dermicool", 10);
        System.out.println("Fetched :"+input.get(0).get("tweetsText").textValue());
        System.out.println("----------------------------------");
        ArrayNode output= SentimentCompute.smileyLevelStatistic(input);
        //System.out.print(output.get(0).get("sentiments").textValue());
        assertEquals(output.get(0).get("sentiments").textValue(),":-)");

    }
    
    /**
     * @author Simran
     * tests smileyLevelStatistic method for :-( sentiment
     * @throws Exception
     */

    @Test
    public void testsmileyLevelStatistic_sad() throws Exception{
    	TwitterObject.emotion=-1;    
        ArrayNode input = TwitterService.getTweets("dermicool", 10);
        System.out.println("Fetched :"+input.get(0).get("tweetsText").textValue());
        System.out.println("----------------------------------");
        ArrayNode output= SentimentCompute.smileyLevelStatistic(input);
       // System.out.print(output.get(0).get("sentiments").textValue());
        assertEquals(output.get(0).get("sentiments").textValue(),":-(");
    }
    
    /**
     * @author Simran
     * tests smileyLevelStatistic method for :-| sentiment
     * @throws Exception
     */

    @Test
    public void testsmileyLevelStatistic_neutral() throws Exception{
      
    	TwitterObject.emotion=2;
    	ArrayNode input = TwitterService.getTweets("dermicool", 10);
        System.out.println("Fetched :"+input.get(0).get("tweetsText").textValue());
        System.out.println("----------------------------------");
        ArrayNode output= SentimentCompute.smileyLevelStatistic(input);
       // System.out.print(output.get(0).get("sentiments").textValue());
        assertEquals(output.get(0).get("sentiments").textValue(),":-|");
    }


}