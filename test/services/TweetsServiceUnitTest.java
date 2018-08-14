package services;

import com.fasterxml.jackson.databind.node.ArrayNode;

import Model.UserModel;

import org.junit.*;
import play.test.Helpers;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * @author pranav
 *  Contains various test scenarios for TweetService Class which uses
 *  the twitter api to retrieve tweets based on a keyword
 */

public class TweetsServiceUnitTest {
    /**
     * @author pranav
     *  Creates the environment for testing the Tweets Service Class
     *  by setting the testCase variable as true
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
    	
       TwitterObject.testCase = true;
       TwitterObject.emotion = 1;
    }

    /**
     * Sets up the teardown face after all the test methods are run
     * @author pranav
     *  Sets the testcase variable as false
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
        TwitterObject.testCase = false;
        TwitterObject.emotion=0;
        Helpers.stop(TwitterObject.testApp);
    }

    /**
     * checks the username returned from the getTweets() method in the twitter API
     * @author pranav
     * Tests the getTweets method and
     * asserts that the user is same
     * @throws Exception
     */
    @Test
    public void testGetTweets_checkUser() throws Exception{
    	
        ArrayNode testNode = TwitterService.getTweets("dermicool", 10);
        assertEquals(testNode.get(0).get("userName").asText(), "Rodolfo");
    }

    /**
     * @author pranav
     * Tests the getTweets method and
     * asserts that at least one tweet is returned as result
     * @throws Exception
     */
    @Test
    public void testGetTweets_checkSize() throws Exception{
    	
        ArrayNode testNode = TwitterService.getTweets("dermicool", 10);
        assertTrue(testNode.size() > 0);
    }

    /**
     * @author pranav
     * Tests that at least one tweet is returned as a result when queried for
     * the hashtags
     * @throws Exception
     */
    @Test
    public void getHashtagTweets() throws Exception{
    	
        List<Status> statusArrayList = TwitterService.getHashtagTweets("dermicool");
        assertTrue(statusArrayList.size() > 0);
    }

    /**
     * @author pranav
     * tests that at least one tweet is returned as a result when queried
     * on the basis of the geoLocation
     * @throws Exception
     */
    @Test
    public void testGetLocationTweets_locationNull() throws Exception{
       
        List<Status> list = TwitterService.getLocationTweets("45.5363999", "-73.5614825");
        assertTrue(list.size() > 0);
    }

   /* @Test
    public void testGetLocationTweets_locationPresent() throws Exception{
        TwitterObject.emotion = 0;
        CompletableFuture<List<Status>> listCompletableFuture = TweetsService.getLocationTweets("45.5363999", "-73.5614825");
        assertTrue(listCompletableFuture.get().size() > 0);
    }
    */

    /**
     * @author pranav
     * tests for the user of the tweet
     * @throws Exception
     */
    @Test
    public void getUser() throws Exception{
        
        UserModel testNodeFuture = TwitterService.getUser("Rodolfo");
        assertNotNull(testNodeFuture);

    }
}