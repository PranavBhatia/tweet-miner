package Model;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import services.TweetsService;
import services.TwitterObject;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
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
     * tests computeHappy method in SentimentCompute class
     */
    @Test
    public void testcomputeHappy() {
        List<String> happy_List=Arrays.asList("I am happy \uD83D\uDE42","Today is a Submission Day \uD83D\uDE14");
        Long result=1L;
        assertEquals(SentimentCompute.computeHappy(happy_List),result);
    }

    /**
     * @author Simran
     * tests computeSad method in SentimentCompute class
     */

    @Test
    public void testcomputeSad() {
        List<String> sad_List=Arrays.asList("Today is a Submission Day \uD83D\uDE1E","I am happy \uD83D\uDE42");
        Long result=1L;
        assertEquals(SentimentCompute.computeSad(sad_List),result);
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