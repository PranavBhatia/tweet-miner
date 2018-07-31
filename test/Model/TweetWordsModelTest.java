package Model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.libs.Json;
import services.TwitterObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.*;

public class TweetWordsModelTest {

    @BeforeClass
    public static void setUp() throws Exception {
        TwitterObject.testCase = true;

    }

    @AfterClass
    public static void tearDown() throws Exception {
        TwitterObject.testCase = false;
    }

    @Test
    public void tweetWords() {


        ArrayNode arrayNode = Json.newArray();


        ObjectNode tempTweetsObjectNode1 = Json.newObject();
        tempTweetsObjectNode1.put("tweetsText", "I am happy 🙂");
        ObjectNode tempTweetsObjectNode2 = Json.newObject();
        tempTweetsObjectNode2.put("tweetsText", "I am happy 🙂");

        arrayNode.add(tempTweetsObjectNode1);
        arrayNode.add(tempTweetsObjectNode2);


        Map<String, Long> expectedMap = new HashMap();
        expectedMap.put("I",Integer.toUnsignedLong(1));
        expectedMap.put("am",Integer.toUnsignedLong(1));
        expectedMap.put("happy",Integer.toUnsignedLong(1));
        expectedMap.put("🙂",Integer.toUnsignedLong(1));


        Map<String, Long> actualMap = TweetWordsModel.tweetWords(arrayNode);
        assertEquals(4, actualMap.size());
    }

    @Test
    public void findWordLevelStatistic() {
        List<String> tweetsList = Arrays.asList("I am happy 🙂", "🙂 I", "happy");

        Map<String, Long> expectedMap = new HashMap();
        expectedMap.put("I",Integer.toUnsignedLong(2));
        expectedMap.put("am",Integer.toUnsignedLong(1));
        expectedMap.put("happy",Integer.toUnsignedLong(2));
        expectedMap.put("🙂",Integer.toUnsignedLong(2));


        Map<String, Long> actualMap = TweetWordsModel.findWordLevelStatistic(tweetsList);
        assertEquals(4, actualMap.size());
    }
}