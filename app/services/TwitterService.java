package services;

import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterService {
	
	private static Twitter getInstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true).setOAuthConsumerKey("uvZYPifCuMDmDLhGzryVaH9sA")
	    						.setOAuthConsumerSecret("G01gHXhUerHAevCjhR0U1iOlm5VNmxC5cGRnldvHscVFcMfkvQ")
	    						.setOAuthAccessToken("969353476450017280-r7gPo5QAT8svSDiCBoB0jSKu1f3Oa8P")
	    						.setOAuthAccessTokenSecret("TmapsealCXor82pige4FwRZ16tqKollMbBb6AieVcKVrJ");
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitterInstance = tf.getInstance();
	    return twitterInstance;
	}
	
	public static List<Status> getTweets(String keywords) {
		
		List<Status> tweets = null;
		
		Twitter twitter = getInstance();
		
		Query query = new Query(keywords);
		QueryResult result;
		
		try {
			result = twitter.search(query);
			tweets = result.getTweets();
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return tweets;
	}
}
