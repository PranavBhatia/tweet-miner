package services;

public class TwitterApiGuiceModel extends com.google.inject.AbstractModule{

	@Override
	protected void configure() {
		bind(TwitterApi.class).to(TwitterApiLiveImpl.class);
		
	}

}
