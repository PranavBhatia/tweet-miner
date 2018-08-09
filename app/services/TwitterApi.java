package services;
import com.google.inject.ImplementedBy;

import twitter4j.Twitter;

@ImplementedBy (TwitterApiLiveImpl.class)
public interface TwitterApi {

	Twitter getTwitterInstance();
}
