package services;

public class TwitterModule extends com.google.inject.AbstractModule{

	@Override
	protected void configure() {
        bind(TwitterApi.class).to(TwitterApiLiveImpl.class);
    }
}
