package Model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.primitives.Chars;

public class SentimentCompute {
	
	
	public static void smileyLevelStatistic(ArrayNode tweetList) {
		List<String> tweetHolder = new ArrayList<>();
		for (JsonNode tweets:tweetList)
		{
			tweetHolder.add(tweets.get("tweetsText").textValue());
		}
		
	
		Long happy=tweetHolder.parallelStream()
					.filter(t-> t.contains("\uD83D\uDE10"))
					.count();
		System.out.println(happy);
		
	}
}
