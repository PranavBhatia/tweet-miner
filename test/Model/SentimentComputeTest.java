package Model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import services.TwitterService;
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

public class SentimentComputeTest {


    @BeforeClass
    public static void setUp() throws Exception {
        TwitterObject.testCase = true;

    }

    /**
     * @throws Exception
     * @author Simran
     * Resets the environment by setting the TwitterObject's Parameter - testCase to false ensuring the remaining calls hit the Live Twitter API.
     */
    @AfterClass
    public static void tearDown() throws Exception {
        TwitterObject.testCase = false;
        TwitterObject.emotion = 0;
        Helpers.stop(TwitterObject.testApp);
    }


    @Test
    public void testgetTweetEmojiHappy() throws Exception {
        TwitterObject.emotion = 1;
        ArrayNode testNodeFuture = TwitterService.getTweets("dermicool", 10);
        ArrayNode testNode = testNodeFuture;
        String tweet = testNode.get(0).get("tweetsText").textValue();
        String expected_emotion = ":-)";
        assertEquals(SentimentCompute.getTweetEmoji(tweet), expected_emotion);
    }


    @Test
    public void testgetTweetEmojiSad() throws Exception {
        TwitterObject.emotion = -1;
        ArrayNode testNodeFuture = TwitterService.getTweets("dermicool", 10);
        ArrayNode testNode = testNodeFuture;
        String tweet = testNode.get(0).get("tweetsText").textValue();
        String expected_emotion = ":-(";
        assertEquals(SentimentCompute.getTweetEmoji(tweet), expected_emotion);
    }


    @Test
    public void testgetTweetEmojiNeutral() throws Exception {
        TwitterObject.emotion = 2;
        ArrayNode testNodeFuture = TwitterService.getTweets("dermicool", 10);
        ArrayNode testNode = testNodeFuture;
        String tweet = testNode.get(0).get("tweetsText").textValue();
        String expected_emotion = ":-|";
        assertEquals(SentimentCompute.getTweetEmoji(tweet), expected_emotion);
    }


    /**
     * @throws Exception
     * @author Simran
     * tests smileyLevelStatistic method for :-) sentiment
     */

    @Test
    public void testsmileyLevelStatistic_happy() throws Exception, TwitterException {
        TwitterObject.emotion = 1;
        ArrayNode testNodeFuture = TwitterService.getTweets("dermicool", 10);
        ArrayNode input = testNodeFuture;
        System.out.println("Fetched :" + input.get(0).get("tweetsText").textValue());
        System.out.println("----------------------------------");
        ArrayNode output = SentimentCompute.smileyLevelStatistic(input);
        //System.out.print(output.get(0).get("sentiments").textValue());
        assertEquals(output.get(0).get("sentiments").textValue(), ":-)");

    }

    /**
     * @throws Exception
     * @author Simran
     * tests smileyLevelStatistic method for :-( sentiment
     */

    @Test
    public void testsmileyLevelStatistic_sad() throws Exception {
        TwitterObject.emotion = -1;
        ArrayNode testNodeFuture = TwitterService.getTweets("dermicool", 10);
        ArrayNode input = testNodeFuture;
        System.out.println("Fetched :" + input.get(0).get("tweetsText").textValue());
        System.out.println("----------------------------------");
        ArrayNode output = SentimentCompute.smileyLevelStatistic(input);
        // System.out.print(output.get(0).get("sentiments").textValue());
        assertEquals(output.get(0).get("sentiments").textValue(), ":-(");
    }

    /**
     * @throws Exception
     * @author Simran
     * tests smileyLevelStatistic method for :-| sentiment
     */

    @Test
    public void testsmileyLevelStatistic_neutral() throws Exception {
        TwitterObject.emotion = 2;
        ArrayNode input = TwitterService.getTweets("dermicool", 10);
        TwitterObject.emotion = 2;
        ArrayNode testNodeFuture = TwitterService.getTweets("dermicool", 10);
        System.out.println("Fetched :" + input.get(0).get("tweetsText").textValue());
        System.out.println("----------------------------------");
        ArrayNode output = SentimentCompute.smileyLevelStatistic(input);
        assertEquals(output.get(0).get("sentiments").textValue(), ":-|");
    }

}