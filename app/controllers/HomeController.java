package controllers;

import play.mvc.*;

import twitter4j.*;
import twitter4j.api.SearchResource;
import views.html.*;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() { return ok(index.render("PB Play framework")); }

   public Result welcome(String name) throws Exception{
      /* Twitter twitter = TwitterFactory.getSingleton();
       Query query = new Query("source:twitter4j yusukey");
       QueryResult result = twitter.search(query);
       for (Status status : result.getTweets()) {
           System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
       }*/

        return ok("PB's welcome page \nWelcome " + name);
    }

 /*   public CompletionStage<Result> message() {
        return ok("PB's welcome page ");
    }*/
}
