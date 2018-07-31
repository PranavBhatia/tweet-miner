package controllers;

import org.junit.*;

import static org.junit.Assert.*;

public class HomeControllerTest {

    public static HomeController controller;

    @BeforeClass
    public static void setUp() throws Exception {
        controller = new HomeController();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        controller = null;
    }

    @Test
    public void index() {
        assertNotNull(controller.index());
    }

    @Test
    public void search() throws Exception{
        assertNotNull(controller.search("dermicool"));
    }

    @Test
    public void getHashtags() throws Exception{
        assertNotNull(controller.getHashtags("dermicool"));
    }

    @Test
    public void getLocation() throws Exception{
        assertNotNull(controller.getLocation("45.5363999", "-73.5614825"));
    }

    @Test
    public void getUserProfile() throws Exception{
        assertNotNull(controller.getUserProfile("Rodolfo"));
    }

    @Test
    public void getUserTweets() throws Exception{
        assertNotNull(controller.getUserTweets("dermicool"));
    }

    @Test
    public void getTweetWords() throws Exception{
        assertNotNull(controller.getTweetWords("dermicool"));
    }

    @Test
    public void getSentiment() throws Exception{
        assertNotNull(controller.getTweetWords("dermicool"));
    }
}