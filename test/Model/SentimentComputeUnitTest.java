package Model;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import services.TweetsService;
import services.TwitterObject;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Simran
 * Class to test all the method in SentimentCompute class
 */

public class SentimentComputeUnitTest {

    /**
     * @author Simran
     * Sets up the environment by making the TwitterObject's parameter - testCase to true.
     * This parameter ensure that the call is made to the mock Classes and not to the live Twitter API for testing.
     * @throws Exception
     */
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
    }

    /**
     * @author Simran
     * Test : getTweetEmoji returns Happy Sentiment for the given input
     */
    @Test
    public void testgetTweetEmojiHappy() {
        
    	String happyTweet="I am happy \uD83D\uDE42";
        String expected_emotion=":-)";
        assertEquals(SentimentCompute.getTweetEmoji(happyTweet),expected_emotion);
    }
    
    /**
     * @author Simran
     * Test : getTweetEmoji returns Sad Sentiment for the given input
     */
    @Test
    public void testgetTweetEmojiSad() {
        
    	String sadTweet="I am happy \uD83D\uDE14";
        String expected_emotion=":-(";
        assertEquals(SentimentCompute.getTweetEmoji(sadTweet),expected_emotion);
    }
    
    /**
     * @author Simran
     * Test : getTweetEmoji returns Neutral Sentiment for the given input
     */
    
    @Test
    public void testgetTweetEmojiNeutral() {
        
    	String neutralTweet="\uD83D\uDE42 I am happy \uD83D\uDE14";
        String expected_emotion=":-|";
        assertEquals(SentimentCompute.getTweetEmoji(neutralTweet),expected_emotion);
    }

    /**
     * @author Simran
     * Test: computeEmoji return the sentiment expressed in more than 70% of Tweets.
     * Assumptions : Total Tweet Count = 3. 
     */

    @Test
    public void testcomputeEmojiHappy() {
        
        Map<String,Long> input=new HashMap<String,Long>();
        input.put(":-)", 2L);
        input.put(":-(", null);
        input.put(":-|", null);
        int threshold=1;
        String expected_result=":-)";
        assertEquals(SentimentCompute.computeEmoji(input,threshold),expected_result);
    }

    /**
     * @author Simran
     * Test: computeEmoji return the sentiment expressed in more than 70% of Tweets.
     * Assumptions : Total Tweet Count = 3.
     */

    @Test
    public void testcomputeEmojiSad() {

        Map<String,Long> input=new HashMap<String,Long>();
        input.put(":-)", null);
        input.put(":-(", 2L);
        input.put(":-|", null);
        int threshold=1;
        String expected_result=":-(";
        assertEquals(SentimentCompute.computeEmoji(input,threshold),expected_result);
    }

    /**
     * @author Simran
     * Test: computeEmoji return the sentiment expressed in more than 70% of Tweets.
     * Assumptions : Total Tweet Count = 3.
     */

    @Test
    public void testcomputeEmojiNeutral() {

        Map<String,Long> input=new HashMap<String,Long>();
        input.put(":-)", null);
        input.put(":-(", null);
        input.put(":-|", 2L);
        int threshold=1;
        String expected_result=":-|";
        assertEquals(SentimentCompute.computeEmoji(input,threshold),expected_result);
    }



    /**
     * @author Simran
     * tests smileyLevelStatistic method for :-) sentiment
     * @throws Exception
     */

    @Test
    public void testsmileyLevelStatistic_happy() throws Exception{

        CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
        ArrayNode input=testNodeFuture.get();
        ArrayNode output= SentimentCompute.smileyLevelStatistic(input);
        System.out.print(output.get(0).get("sentiments").textValue());
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
        CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
        ArrayNode input=testNodeFuture.get();
        ArrayNode output= SentimentCompute.smileyLevelStatistic(input);
        System.out.print(output.get(0).get("sentiments").textValue());
        assertEquals(output.get(0).get("sentiments").textValue(),":-(");
    }
    
    /**
     * @author Simran
     * tests smileyLevelStatistic method for :-| sentiment
     * @throws Exception
     */

    @Test
    public void testsmileyLevelStatistic_neutral() throws Exception{
        TwitterObject.emotion=0;
        CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
        ArrayNode input=testNodeFuture.get();
        ArrayNode output= SentimentCompute.smileyLevelStatistic(input);
        System.out.print(output.get(0).get("sentiments").textValue());
        assertEquals(output.get(0).get("sentiments").textValue(),":-|");
    }


}