package services;

import mockClasses.PseudoTwitterHappy;
import mockClasses.PseudoTwitterNeutral;
import mockClasses.PseudoTwitterSad;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Pranav Bhatia
 */
public class TwitterObject {
    /**
     * Get a twitter instance after authentication
     *
     * @return a Twitter instance
     */
    public static boolean testCase = false;
    public static int emotion = 1;
    private static Twitter singletonTwitterInstance = null;

    public static Twitter getInstance() {

        if (!testCase) {
            if (singletonTwitterInstance == null) {
                ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true).setOAuthConsumerKey("uvZYPifCuMDmDLhGzryVaH9sA")
                        .setOAuthConsumerSecret("G01gHXhUerHAevCjhR0U1iOlm5VNmxC5cGRnldvHscVFcMfkvQ")
                        .setOAuthAccessToken("969353476450017280-r7gPo5QAT8svSDiCBoB0jSKu1f3Oa8P")
                        .setOAuthAccessTokenSecret("TmapsealCXor82pige4FwRZ16tqKollMbBb6AieVcKVrJ");
                TwitterFactory tf = new TwitterFactory(cb.build());
                singletonTwitterInstance = tf.getInstance();
            }
            return singletonTwitterInstance;
        } else {
            if (emotion == 1)
                return new PseudoTwitterHappy();
            else if (emotion == -1)
                return new PseudoTwitterSad();
            else
                return new PseudoTwitterNeutral();
        }
    }
}
