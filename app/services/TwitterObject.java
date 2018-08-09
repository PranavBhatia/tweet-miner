package services;

import static play.inject.Bindings.bind;

import javax.inject.Inject;
import mockClasses.PseudoTwitterHappy;
import mockClasses.PseudoTwitterNeutral;
import mockClasses.PseudoTwitterSad;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * @author Pranav Bhatia
 *
 */
public class TwitterObject { 
  
	/**
	 * Get a twitter instance after authentication
	 * @return a Twitter instance
	 */
	
	static private Twitter twitterInstance;
	static public String injectedByClass;
	public static boolean testCase = false;
    public static int emotion = 0;
    static public Application testApp;
	
	 @Inject
	 public TwitterObject (TwitterApi twitterApiInstance) {

		 twitterInstance=twitterApiInstance.getTwitterInstance();
		 injectedByClass=twitterApiInstance.getClass().getName();
	 }	
      
      public Twitter getInstance() {

          if (!testCase) {
               return twitterInstance;
          } 
          else {
              if (emotion == 1)
              {
            	  		testApp= new GuiceApplicationBuilder()
              			.overrides(bind(TwitterApi.class).to(PseudoTwitterHappy.class))
            			.build();
            	  
            	  TwitterApi testTwitter = testApp.injector().instanceOf(TwitterApi.class);
            	  twitterInstance=testTwitter.getTwitterInstance();
				  injectedByClass=testTwitter.getClass().getName();
				  System.out.println(injectedByClass);
            	  
            	  
              }
                 
              else if (emotion == -1)
                  {
            	  
            	  			 testApp= new GuiceApplicationBuilder()
                			            .overrides(bind(TwitterApi.class).to(PseudoTwitterSad.class))
                			            .build();
              	  
            	  			TwitterApi testTwitter = testApp.injector().instanceOf(TwitterApi.class);
            	  			twitterInstance=testTwitter.getTwitterInstance();
					  		injectedByClass=testTwitter.getClass().getName();
					  		
            	  			
            	  				
            	  
            	   
            	   
                  }
              else if (emotion==2){
            	  
            	        testApp= new GuiceApplicationBuilder()
  			            .overrides(bind(TwitterApi.class).to(PseudoTwitterNeutral.class))
  			            .build();
	  
	  			TwitterApi testTwitter = testApp.injector().instanceOf(TwitterApi.class);
	  			twitterInstance=testTwitter.getTwitterInstance();
	  			injectedByClass=testTwitter.getClass().getName();
	  			
	  			
            	  
            	  
              }
              
              return twitterInstance;
                  
          }
      }
}
