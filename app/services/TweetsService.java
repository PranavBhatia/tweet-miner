package services;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import twitter4j.*;

import java.io.Console;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentSkipListMap;

public class TweetsService {

    public static CompletableFuture<ArrayNode> getTweets(String keyword, int limit){
        CompletableFuture<ArrayNode> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        Query query = new Query(keyword);
        query.setCount(limit);
        QueryResult result = null;
        try {
            result = twitter.search(query);
        }catch (TwitterException e){ }
        List<Status> tweets = result.getTweets();
        ArrayNode tweetsArrayNode = Json.newArray();

        tweets.forEach((tweet) -> {
            //System.out.println("lol");
            ObjectNode tempTweetsObjectNode = Json.newObject();
            tempTweetsObjectNode.put("tweetsText", tweet.getText());
            tempTweetsObjectNode.put("userName", tweet.getUser().getName());
            tempTweetsObjectNode.put("userScreenName", tweet.getUser().getScreenName());
            tempTweetsObjectNode.put("userLocation", tweet.getUser().getLocation());
            ArrayNode tempHashtagArrayNode = Json.newArray();
            StringBuilder s = new StringBuilder();
            for (HashtagEntity hashtagEntity: tweet.getHashtagEntities()) {
                s = s.append(hashtagEntity.getText() + ",");
            }
            tempTweetsObjectNode.put("getHashtags", s.toString());
            tweetsArrayNode.add(tempTweetsObjectNode);
            //System.out.println("lol2");
        });
       // System.out.println("lol3");
        System.out.println("TweetsText:"+tweetsArrayNode.get(0).get("tweetsText").asText());

        future.complete(tweetsArrayNode);
        return future;
    }

    public static CompletableFuture<List<Status>> getLocationTweets(String location){
        CompletableFuture<List<Status>> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        Query query = new Query(location);
        query.setCount(10);
        QueryResult result = null;
        try {
            result = twitter.search(query);
        }catch (TwitterException e){ }
        List<Status> tweets = result.getTweets();
        future.complete(tweets);
        return future;
    }

    public static CompletableFuture<ArrayNode> getUser(String username){
        CompletableFuture<ArrayNode> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        try {
            System.out.println(twitter.showUser(username));
        }catch (TwitterException e) {}
        return future;
    }
}