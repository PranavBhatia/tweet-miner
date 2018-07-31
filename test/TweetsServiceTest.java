import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.TweetsService;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

public class TweetsServiceTest {


    TweetsService tweetsService;

    @Before
    public void setUp() throws Exception {
        tweetsService = new TweetsService();
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
        //CompletableFuture<List<Status>> hashTagTweets =
    }

    @Test
    public void getLocationTweets() {
    }

    @Test
    public void getUser() {
        //CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
    }
}