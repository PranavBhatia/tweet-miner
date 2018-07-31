package services;

import mockClasses.PseudoTwitterHappy;
import mockClasses.PseudoTwitterSad;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterObject {
	public static boolean testCase = false;
    public static int emotion = 1;
	public static Twitter getInstance() {

        if (!testCase) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true).setOAuthConsumerKey("uvZYPifCuMDmDLhGzryVaH9sA")
                    .setOAuthConsumerSecret("G01gHXhUerHAevCjhR0U1iOlm5VNmxC5cGRnldvHscVFcMfkvQ")
                    .setOAuthAccessToken("969353476450017280-r7gPo5QAT8svSDiCBoB0jSKu1f3Oa8P")
                    .setOAuthAccessTokenSecret("TmapsealCXor82pige4FwRZ16tqKollMbBb6AieVcKVrJ");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitterInstance = tf.getInstance();
            return twitterInstance;
        }else{
            if(emotion == 1)
                return new PseudoTwitterHappy();
            else if (emotion == -1)
                return new PseudoTwitterSad();
            return null;
        }
	}
}
