package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;



/**
 * computes tweet sentiment based on 100 latest tweets and render to HTML page
 * @author simran
 * 
 */

public class SentimentCompute {

	/**
	 * @author Simran
	 * @param String tweet - a single tweet
	 * @return String holding Emoji of the tweet based on the computation-
	 * :-) : A Tweet that hold only happy and no sad emoticon 
	 * :-( : A tweet which has only sad and no happy emoticon 
	 * :-| : All tweets that have no Emoticons or the ones with mix expression ,both happy and sad emoticon.
	 */	
	public static String getTweetEmoji (String tweet)
	{
		if (( tweet.contains("\uD83D\uDE42") || tweet.contains("\uD83D\uDE0A") || tweet.contains("\u1F60A") || tweet.contains("\uD83D\uDE0C")) 
	    		&& !( tweet.contains("\uD83D\uDE1E")  || tweet.contains("\uD83D\uDE14") || tweet.contains("\uD83D\uDE41") || tweet.contains("\u2639\uFE0F")) )
			return ":-)";
		else if ( ( tweet.contains("\uD83D\uDE1E") || tweet.contains("\uD83D\uDE14") || tweet.contains("\uD83D\uDE41") || tweet.contains("\u2639\uFE0F"))
    		    &&  !( tweet.contains("\uD83D\uDE42") || tweet.contains("\uD83D\uDE0A") || tweet.contains("\u1F60A") || tweet.contains("\uD83D\uDE0C"))    )
			return ":-(";
		else
			return ":-|";
			
	}
	
	/**
	 * @author Simran
	 * @param sentiments - Map contains the emoji as key and number of tweets holding the respective emoji
	 * @param threshold - 70% of the total tweets
	 * @return String holding overall Sentiment analysis i.e :-) or :-( or :-|
	 */
	
	public static String computeEmoji(Map<String, Long> sentiments,int threshold)
	{
		  System.out.println(":-)"+" "+sentiments.get(":-)"));
		  System.out.println(":-(" +" "+sentiments.get(":-("));
		  System.out.println(":-|"+" "+sentiments.get(":-|"));
		  System.out.println("70% of tot:"+  threshold);
		
		return (  sentiments.get(":-)")!=null && sentiments.get(":-)") > threshold   ) ? ":-)" : 
			
			 (( sentiments.get(":-(")!=null &&	sentiments.get(":-(")  > threshold ) ? ":-(" : ":-|");	
	
	}
	
	
	/**
	 * return ArrayNode holding 100 tweets after computing the Sentiment attached to the first Json Object of the ArrayNode
	 * @author simran
	 * @param tweetsList
	 * @return
	 */
	public static ArrayNode smileyLevelStatistic(ArrayNode tweetsList){
		
		 // Building a list of the Json->
		List<String> tweetHolder = new ArrayList<>();
		
		for (JsonNode tweets:tweetsList)
		{
			tweetHolder.add(tweets.get("tweetsText").textValue());
		}
			
		
		Map<String, Long> sentimentCounter= tweetHolder.stream()
		  		.collect(Collectors.groupingBy(SentimentCompute::getTweetEmoji,Collectors.counting()));
	     
	    String emoji=SentimentCompute.computeEmoji(sentimentCounter, (  (int)(tweetHolder.size()  *  0.70)  ) );
	    System.out.println(emoji);	
	     ObjectNode tempTweetsObjectNode = Json.newObject();
	     tempTweetsObjectNode=(ObjectNode) tweetsList.get(0);
	     tempTweetsObjectNode.put("sentiments", emoji);
	     tweetsList.remove(0);
	     tweetsList.insert(0,tempTweetsObjectNode);

	     return tweetsList;		
	}

}
