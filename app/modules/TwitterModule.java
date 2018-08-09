package modules;

import com.google.inject.AbstractModule;
import services.TweetsService;
import services.TwitterApi;
import services.TwitterApiLiveImpl;
import services.TweetsService;
import twitter4j.Twitter;

public class TwitterModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(TwitterApi.class).to(TwitterApiLiveImpl.class);
	}

}
