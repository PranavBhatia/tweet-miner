package services;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import twitter4j.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TweetsService {
    public static CompletableFuture<ArrayNode> getTweets(String keyword, int limit) throws Exception {
        CompletableFuture<ArrayNode> future = new CompletableFuture<>();
        Twitter twitter = TwitterObject.getInstance();
        Query query = new Query(keyword);
        query.setCount(limit);
        QueryResult result = twitter.search(query);
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
        future.complete(tweetsArrayNode);
        return future;
    }
}

/*
*



        future.complete(tweetJSON);*/