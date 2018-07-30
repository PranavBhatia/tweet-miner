package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import twitter4j.Status;
public class SentimentCompute {
	
	public static Long computeHappy(List<String> tweetList)
	{
		Long happycount=tweetList.stream()
				            .filter(t-> {	
					                      return t.contains("\uD83D\uDE42") && !t.contains("\uD83D\uDE1E");
				                        }) // return a true (happy tweet) - if the tweet has only a happy emoji and no sad emoji 
				            .parallel()
				            .count(); // count the number of true
		      return happycount;		
	}
	
	public static Long computeSad(List<String> tweetList)
	{
		
		Long sadcount=tweetList.stream()
                .filter(t->{ 
                	        return t.contains("\uD83D\uDE1E") && !t.contains("\uD83D\uDE42");
                	       }) // return a true (sad tweet) - if the tweet has only a sad emoji and no happy emoji
                .parallel()
                .count(); // count the number of true
		return sadcount;
	}
	
	
	
	
	public static String smileyLevelStatistic(List<Status> tweetList) throws InterruptedException, ExecutionException {
		
	    // Building a list of the Json->
		List<String> tweetHolder = new ArrayList<>();
		for (Status tweets:tweetList)
		{
			tweetHolder.add(tweets.getText());
		}
		
		
		CompletableFuture<String> s=CompletableFuture.supplyAsync( () -> { return SentimentCompute.computeHappy(tweetHolder);} )
						.thenCombine(   CompletableFuture.supplyAsync( ()-> {return SentimentCompute.computeSad(tweetHolder);}   )
								        , (s1, s2) -> {
								   		 System.out.println("happy tweet:"+s1.intValue());
						        		 System.out.println("sad tweet:"+s2.intValue());
						        		 System.out.println("Total tweet:"+tweetHolder.size());
						        		 System.out.println("70% of tot:"+(int)(tweetHolder.size() * 0.70));
								        	 if ( s1.intValue() > (int)(tweetHolder.size() * 0.70)) {
								        		 
								        
								     	    	return ":-)"; // Happy face
								     	    }
								        	 else if (s2.intValue() > (int)(tweetHolder.size() * 0.70))
								        	 {
								        		 return ":-(";
								        	 }
								        	 else
								        		 return ":-|";//Neutral face
								        	
								        });//.exceptionally(ex -> ":-|" + ex.getMessage());
		
		System.out.println(s.get());
		return s.get();	
		
		
	}

}
