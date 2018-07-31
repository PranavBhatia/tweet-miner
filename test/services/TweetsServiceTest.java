package services;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.*;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class TweetsServiceTest {
    @BeforeClass
    public static void setUp() throws Exception {
       TwitterObject.testCase = true;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        TwitterObject.testCase = false;
    }

    @Test
    public void testGetTweets_checkUser() throws Exception{
        CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
        ArrayNode testNode = testNodeFuture.get();
        assertEquals(testNode.get(0).get("userName").asText(), "Rodolfo");
    }

    public void testGetTweets_checkSize() throws Exception{
        CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
        ArrayNode testNode = testNodeFuture.get();
        assertTrue(testNode.size() > 0);
    }

    @Test
    public void getHashtagTweets() throws Exception{
        CompletableFuture<List<Status>> listCompletableFuture = TweetsService.getHashtagTweets("dermicool");
        ArrayList<Status> statusArrayList = (ArrayList<Status>) listCompletableFuture.get();
        assertTrue(statusArrayList.size() > 0);
    }

    @Test
    public void testGetLocationTweets_locationNull() throws Exception{
        TwitterObject.emotion = 1;
        CompletableFuture<List<Status>> listCompletableFuture = TweetsService.getLocationTweets("45.5363999", "-73.5614825");
        assertTrue(listCompletableFuture.get().size() > 0);
    }

   /* @Test
    public void testGetLocationTweets_locationPresent() throws Exception{
        TwitterObject.emotion = 0;
        CompletableFuture<List<Status>> listCompletableFuture = TweetsService.getLocationTweets("45.5363999", "-73.5614825");
        assertTrue(listCompletableFuture.get().size() > 0);
    }
    */

    @Test
    public void getUser() throws Exception{
        TwitterObject.emotion = 1;
        CompletableFuture<User> testNodeFuture = TweetsService.getUser("Rodolfo");
        System.out.println(testNodeFuture.get());
    }
}