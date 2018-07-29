package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import Model.TweetModel;
import akka.japi.Predicate;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterObject {
	
	public static Twitter getInstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true).setOAuthConsumerKey("uvZYPifCuMDmDLhGzryVaH9sA")
	    						.setOAuthConsumerSecret("G01gHXhUerHAevCjhR0U1iOlm5VNmxC5cGRnldvHscVFcMfkvQ")
	    						.setOAuthAccessToken("969353476450017280-r7gPo5QAT8svSDiCBoB0jSKu1f3Oa8P")
	    						.setOAuthAccessTokenSecret("TmapsealCXor82pige4FwRZ16tqKollMbBb6AieVcKVrJ");
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitterInstance = tf.getInstance();
	    return twitterInstance;
	}
}
