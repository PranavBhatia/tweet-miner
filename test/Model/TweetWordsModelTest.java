package Model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.Application;
import play.libs.Json;
import play.test.Helpers;
import services.TwitterService;
import services.TwitterApi;
import services.TwitterObject;
import static play.inject.Bindings.bind;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import mockClasses.PseudoTwitterHappy;
import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.*;
/**
 * @author pranav
 * This class test the various scenarios of TweetWords Model Class
 * @throws Exception
 */
public class TweetWordsModelTest {

    static Application testApp;

    /**
     * @throws Exception
     * @author pranav
     * Creates the environment for testing the TweetWords Model Class
     * by setting the testCase variable of TwitterObject as true
     */
    @BeforeClass
    public static void setUp() throws Exception {
        TwitterObject.testCase = true;


    }

    /**
     * @throws Exception
     * @author pranav
     * Sets the testcase variable as false
     */
    @AfterClass
    public static void tearDown() throws Exception {
        TwitterObject.testCase = false;
        TwitterObject.emotion = 0;
        Helpers.stop(TwitterObject.testApp);
    }

    /**
     * @throws Exception
     * @author pranav
     * Tests that the method returns the word level statistics
     */
    @Test
    public void tweetWords() throws Exception {
        System.out.println("cnkjhgc vbnkjhugvg b");
        TwitterObject.emotion = 1; //Making call to PseudoHappy class
        ArrayNode testNodeFuture = TwitterService.getTweets("dermicool", 10);
        ArrayNode testNode = testNodeFuture;
        Map<String, Long> actualMap = TweetWordsModel.tweetWords(testNode);
        assertEquals(4, actualMap.size());
    }
}