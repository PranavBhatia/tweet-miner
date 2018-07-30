package controllers;

import twitter4j.Status;
import twitter4j.User;

import java.util.List;

public class TwitterUser {

    List<Status> lastTenTweets;
    User userObject;

    public User getUserObject() {
        return userObject;
    }

    public List<Status> getLastTenTweets() {
        return lastTenTweets;
    }

    public void setLastTenTweets(List<Status> lastTenTweets) {
        this.lastTenTweets = lastTenTweets;
    }

    public void setUserObject(User userObject) {
        this.userObject = userObject;
    }
}