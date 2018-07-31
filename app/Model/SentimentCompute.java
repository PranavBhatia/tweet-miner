package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
	 * returns number of happy emojis based on 100 latest tweets
	 * @author simran
	 * @param tweetList list containg latest 100 tweets
	 * @return number of happy emojis
	 */
	
	public static Long computeHappy(List<String> tweetList)
	{
		Long happycount=tweetList.parallelStream()
				            .filter(t-> {	
					                     
				            	    return ( ( t.contains("\uD83D\uDE42") || t.contains("\uD83D\uDE0A") || t.contains("\u1F60A") || t.contains("\uD83D\uDE0C")) 
				            	    		&& !( t.contains("\uD83D\uDE1E")  || t.contains("\uD83D\uDE14") || t.contains("\uD83D\uDE41") || t.contains("\u2639\uFE0F")) );				            				
				            	
				                        }) // return a true (happy tweet) - if the tweet has only a happy emoji and no sad emoji 
				            
				            .count(); // count the number of true
		      return happycount;	
		      
	}
	
	/**
	 * returns number of sad emojis based on 100 latest tweets
	 * @author simran
	 * @param tweetList list containg latest 100 tweets
	 * @return  number of sad emojis
	 */
	
	public static Long computeSad(List<String> tweetList)
	{
		
		Long sadcount=tweetList.parallelStream()
                .filter(t->{ 
                	        
                	         return (  ( t.contains("\uD83D\uDE1E") || t.contains("\uD83D\uDE14") || t.contains("\uD83D\uDE41") || t.contains("\u2639\uFE0F"))
                	        		    &&  !( t.contains("\uD83D\uDE42") || t.contains("\uD83D\uDE0A") || t.contains("\u1F60A") || t.contains("\uD83D\uDE0C")) );
                	           
                	       }) // return a true (sad tweet) - if the tweet has only a sad emoji and no happy emoji
                 .count(); // count the number of true
		return sadcount;
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
		
		
	     
	     String emoji;
	    	     
    	/* System.out.println("happy:"+ SentimentCompute.computeHappy(tweetHolder));
    	 System.out.println("sad:"+ SentimentCompute.computeSad(tweetHolder));
    	 System.out.println("70 per tot:"+(int)(tweetHolder.size() * 0.70));
    	 System.out.println("tot:"+tweetHolder.size());*/
    	 
    	 
	     if(SentimentCompute.computeHappy(tweetHolder) > (int)(tweetHolder.size() * 0.70)) {
	       	 emoji= ":-)";
	     }
	     else if (SentimentCompute.computeSad(tweetHolder) > (int)(tweetHolder.size() * 0.70)) {
	    	 emoji=":-(";
	     }
	     else {
	    	 emoji=":-|";
	     }
	     
	
	     ObjectNode tempTweetsObjectNode = Json.newObject();
	     tempTweetsObjectNode=(ObjectNode) tweetsList.get(0);
	     tempTweetsObjectNode.put("sentiments", emoji);
	     tweetsList.remove(0);
	     tweetsList.insert(0,tempTweetsObjectNode);
		
/*	Commenting below implementation - as we cannot return a future back to the HomeController to avoid running .get() method     
	   CompletableFuture<ArrayNode> s=CompletableFuture.supplyAsync( () -> { return SentimentCompute.computeHappy(tweetHolder);} )
					.thenCombine(   CompletableFuture.supplyAsync( ()-> {return SentimentCompute.computeSad(tweetHolder);}   )
							        , (s1, s2) -> {
							   		 //System.out.println("happy tweet:"+s1.intValue());
					        		// System.out.println("sad tweet:"+s2.intValue());
					        		 //System.out.println("Total tweet:"+tweetHolder.size());
					        		// System.out.println("70% of tot:"+(int)(tweetHolder.size() * 0.70));
					        		
							        	 if ( s1.intValue() > (int)(tweetHolder.size() * 0.70)) {
							        		 
							        		
							     	    	return ":-)"; // Happy face
							     	    }
							        	 else if (s2.intValue() > (int)(tweetHolder.size() * 0.70))
							        	 {
							        		 
							        		 return ":-(";
							        	 }
							        	 else
							        	 { 
							        		
							        		  return ":-|";//Neutral face
							        	 }
							        	 
							        })
					.thenApply( (str) -> { 	
					
						ObjectNode tempTweetsObjectNode1 = Json.newObject();
						tempTweetsObjectNode1=(ObjectNode) tweetsList.get(0);
						tempTweetsObjectNode1.put("sentiments", emoji);
						 tweetsList.insert(0,tempTweetsObjectNode1);
					   //  System.out.println ( "test"+str );
						return tweetsList;
					
					});
	     
	     
	     
	     return s;
	*/     
	     return tweetsList;
		
		
	}

}
