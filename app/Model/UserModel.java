package Model;

import java.util.List;

import twitter4j.Status;
import twitter4j.User;

public class UserModel {
	private User user;
	private List<Status> tweets;
	
	public UserModel(User user, List<Status> tweets) {
		this.user = user;
		this.tweets = tweets;
	}
	
	public User getUser() {
		return user;
	}
	
	public List<Status> getTweets(){
		return tweets;
	}
	

}
