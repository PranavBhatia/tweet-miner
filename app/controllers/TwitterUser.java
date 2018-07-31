package controllers;

import twitter4j.Status;
import twitter4j.User;

import java.util.List;

/**
 * Class representing twitter user
 * @author kritika
 */
public class TwitterUser {

    List<Status> lastTenTweets;
    User userObject;

    /**
     * getter for user object
     * @return user object
     */
    public User getUserObject() {
        return userObject;
    }

    /**
     * getter for last 10 tweets
     * @author kritika
     * @return list of status objects
     */
    public List<Status> getLastTenTweets() {
        return lastTenTweets;
    }
    /**
     * setter for last ten treets
     * @author kritika
     * @param lastTenTweets
     */
    public void setLastTenTweets(List<Status> lastTenTweets) {
        this.lastTenTweets = lastTenTweets;
    }

    /**
     * setter for userObject
     * @author kritika
     * @param userObject 
     */
    public void setUserObject(User userObject) {
        this.userObject = userObject;
    }
}