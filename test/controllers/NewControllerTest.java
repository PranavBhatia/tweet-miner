package controllers;

import akka.actor.ActorSystem;
import org.junit.*;
import play.mvc.WebSocket;
import services.TwitterService;
import services.*;
import static org.junit.Assert.*;

/**
 *This Controller test contains various tests for the HomeController Class which
 * handles the HTTP requests to the home page.
 */

public class NewControllerTest {

    public static NewController controller;
    public static ActorSystem system;

    /**
     * @author pranav
     * Creates the instance of HomeController class
     * which is used by other test methods
     * @throws Exception
     */

    @BeforeClass
    public static void setUp() throws Exception {
        system = ActorSystem.create();
        controller = new NewController(system);
        TwitterObject.testCase = true;
        TwitterObject.emotion = 1;
    }

    /**
     * @author pranav
     * Tears down the instance created for the Test Class
     * @throws Exception
     */

    @AfterClass
    public static void tearDown() throws Exception {
        controller = null;
        TwitterObject.testCase = false;
        TwitterObject.emotion = 0;
    }

    /**
     * @author pranav
     * Tests that the controller returns a HTML page as Result
     * and doesn't return a null
     */
    @Test
    public void index() {
        assertNotNull(controller.index());
    }

    /**
     * @author pranav
     * Tests an action that returns a HTML page with data
     * and does not returns null
     * @throws Exception
     */
   /* @Test
    public void search() throws Exception{
        assertNotNull(controller.search("dermicool"));
    }
*/
    /**
     * @author pranav
     * Tests an action that renders a HTML page with tweets for a hashtag query
     * and does not return null
     * @throws Exception
     */
    @Test
    public void getHashtags() throws Exception{
        assertNotNull(controller.getHashtags("dermicool"));
    }

    /**
     * @author pranav
     * Tests an action of controller which renders a HTML page
     * with tweets specific to the geoLocation
     * Asserts that the result in not null
     * @throws Exception
     */

    @Test
    public void getLocation() throws Exception{
        assertNotNull(controller.getLocation("45.5363999", "-73.5614825"));
    }

    /**
     * @author pranav
     * Tests an action of controller and asserts that the result is not
     * null but a HTML page with the profile of the tweet owner
     * @throws Exception
     */
    @Test
    public void getUserProfile() throws Exception{
        assertNotNull(controller.getUserProfile("Rodolfo"));
    }

    /**
     * @author pranav
     * Tests the method that returns the latest 10 tweets of user
     * and asserts that the result of the method is not null
     * @throws Exception
     */
    @Test
    public void getUserTweets() throws Exception{
        assertNotNull(TwitterService.getUser("dermicool"));
    }

    /**
     * @author pranav
     * Tests the method that returns the distinct tweet words for an individual query
     * and their corresponding count and asserts that the result of the method is not null
     * @throws Exception
     */
    @Test
    public void getTweetWords() throws Exception{
        assertNotNull(controller.getTweetWords("dermicool"));
    }

    @Test
    public void wsTest() throws Exception {

    }
}