package services;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import twitter4j.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class TweetsServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTweets() throws Exception{
        CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
        ArrayNode testNode = testNodeFuture.get();
        assertEquals(testNode.get(0).get("userScreenName").asText(), "PranavB83923688");
    }

    @Test
    public void getHashtagTweets() {
        assertEquals(1,1);
    }

    @Test
    public void getLocationTweets() {
    }

    @Test
    public void getUser() {
        CompletableFuture<User> testNodeFuture = TweetsService.getUser("Pranav Bhatia");
        User testUser = null;
        try {
            testUser = testNodeFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
       // assertEquals(testUser.get(0).show());

    }
}