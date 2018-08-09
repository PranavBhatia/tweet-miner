package services;


import javax.inject.Inject;

import twitter4j.Twitter;


public class TwitterApiService { // A class that return the object

	 final public Twitter twitterInstance;
	 final public String ImplementorClassInfo;
	
	 @Inject
	 public TwitterApiService(TwitterApi twitterInstance) {
		 this.twitterInstance= twitterInstance.getTwitterInstance();
		 this.ImplementorClassInfo=twitterInstance.getClass().getName();
	 }

	
	 	 
}
