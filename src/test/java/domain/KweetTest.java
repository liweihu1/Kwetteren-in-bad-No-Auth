package domain;

import domain.Kweet;
import domain.Role;
import domain.Trend;
import domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

public class KweetTest {
    private Kweet testKweet1;
    private Kweet testKweet2;
    private Kweet testKweet3;
    private Kweet testKweet4;
    private Kweet testKweet5;
    private Kweet testKweet6;
    private Kweet testKweet7;
    private Kweet testKweet8;
    private Kweet testKweet9;
    private Kweet testKweet10;
    private Kweet testKweetEmpty;
    private User testUser1;
    private User testUser2;
    private User testUser3;
    private User testUser4;
    private User testUser5;
    private User testUser6;
    private User testUser7;
    private User testUser8;
    private User testUser9;
    private User testUser10;
    private UUID testId;
    private String testMessage;
    private Date testDate;
    private Date testDate2;

    @Before
    public void setup() {
        testId = UUID.randomUUID();
        testMessage = "This is a test lol";
        testDate = new Date();
        testDate2 = new Date();
        testKweetEmpty = new Kweet();

        ArrayList<Kweet> testKweets = new ArrayList();
        ArrayList<User> testUsers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (i > 0){
                final User follower = testUsers.get(i - 1);
                testUsers.add(new User(UUID.randomUUID(), "Test" + i, "Test" + i,"Test" + i, "Test", "Test bio", "geen site", "Skkrt", new HashSet(){{add(follower);}}, new HashSet<User>(), new ArrayList<Role>(), new ArrayList<Kweet>(), new ArrayList<Kweet>(), new ArrayList<Kweet>()));
            } else {
                testUsers.add(new User(UUID.randomUUID(), "Test" + i, "Test" + i,"Test" + i, "Test", "Test bio", "geen site", "Skkrt", new HashSet<User>(), new HashSet<User>(), new ArrayList<>(), new ArrayList<Kweet>(), new ArrayList<Kweet>(), new ArrayList<Kweet>()));
            }
            testKweets.add(new Kweet(testId, testUsers.get(i), testMessage, testDate, testDate2, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Trend>(), 0));
        }

        testKweet1 = testKweets.get(0);
        testKweet2 = testKweets.get(1);
        testKweet3 = testKweets.get(2);
        testKweet4 = testKweets.get(3);
        testKweet5 = testKweets.get(4);
        testKweet6 = testKweets.get(5);
        testKweet7 = testKweets.get(6);
        testKweet8 = testKweets.get(7);
        testKweet9 = testKweets.get(8);
        testKweet10 = testKweets.get(9);

        testUser1 = testUsers.get(0);
        testUser2 = testUsers.get(1);
        testUser3 = testUsers.get(2);
        testUser4 = testUsers.get(3);
        testUser5 = testUsers.get(4);
        testUser6 = testUsers.get(5);
        testUser7 = testUsers.get(6);
        testUser8 = testUsers.get(7);
        testUser9 = testUsers.get(8);
        testUser10 = testUsers.get(9);
    }

    @Test
    public void getIdTest() {
        assertEquals("The id was not right for testKweet1.", this.testId, testKweet1.getId());
        assertEquals("The id was not right for testKweet2.", this.testId, testKweet2.getId());
        assertEquals("The id was not right for testKweet3.", this.testId, testKweet3.getId());
        assertEquals("The id was not right for testKweet4.", this.testId, testKweet4.getId());
        assertEquals("The id was not right for testKweet5.", this.testId, testKweet5.getId());
        assertEquals("The id was not right for testKweet6.", this.testId, testKweet6.getId());
        assertEquals("The id was not right for testKweet7.", this.testId, testKweet7.getId());
        assertEquals("The id was not right for testKweet8.", this.testId, testKweet8.getId());
        assertEquals("The id was not right for testKweet9.", this.testId, testKweet9.getId());
        assertEquals("The id was not right for testKweet10.", this.testId, testKweet10.getId());
    }

    @Test
    public void getEmptyIdTest() {
        assertNull("The id was not empty.", testKweetEmpty.getId());
    }

    @Test
    public void getMessageTest() {
        assertEquals("The message was not right for testKweet1", this.testMessage, testKweet1.getMessage());
        assertEquals("The message was not right for testKweet2", this.testMessage, testKweet2.getMessage());
        assertEquals("The message was not right for testKweet3", this.testMessage, testKweet3.getMessage());
        assertEquals("The message was not right for testKweet4", this.testMessage, testKweet4.getMessage());
        assertEquals("The message was not right for testKweet5", this.testMessage, testKweet5.getMessage());
        assertEquals("The message was not right for testKweet6", this.testMessage, testKweet6.getMessage());
        assertEquals("The message was not right for testKweet7", this.testMessage, testKweet7.getMessage());
        assertEquals("The message was not right for testKweet8", this.testMessage, testKweet8.getMessage());
        assertEquals("The message was not right for testKweet9", this.testMessage, testKweet9.getMessage());
        assertEquals("The message was not right for testKweet10", this.testMessage, testKweet10.getMessage());
    }

    @Test
    public void getEmptyMessageTest() {
        assertNull("The message was not right", testKweetEmpty.getMessage());
    }

    @Test
    public void getAuthorTest() {
        assertEquals("The author was not right for testKweet1.", this.testUser1, testKweet1.getAuthor());
        assertEquals("The author was not right for testKweet2.", this.testUser2, testKweet2.getAuthor());
        assertEquals("The author was not right for testKweet3.", this.testUser3, testKweet3.getAuthor());
        assertEquals("The author was not right for testKweet4.", this.testUser4, testKweet4.getAuthor());
        assertEquals("The author was not right for testKweet5.", this.testUser5, testKweet5.getAuthor());
        assertEquals("The author was not right for testKweet6.", this.testUser6, testKweet6.getAuthor());
        assertEquals("The author was not right for testKweet7.", this.testUser7, testKweet7.getAuthor());
        assertEquals("The author was not right for testKweet8.", this.testUser8, testKweet8.getAuthor());
        assertEquals("The author was not right for testKweet9.", this.testUser9, testKweet9.getAuthor());
        assertEquals("The author was not right for testKweet10.", this.testUser10, testKweet10.getAuthor());
    }

    @Test
    public void getEmptyAuthorTest() {
        assertNull("The author was not empty.", testKweetEmpty.getAuthor());
    }

    @Test
    public void getMentionsTest() {
        assertNotNull("The mentions was null testKweet1.", testKweet1.getMentions());
        assertNotNull("The mentions was null testKweet2.", testKweet2.getMentions());
        assertNotNull("The mentions was null testKweet3.", testKweet3.getMentions());
        assertNotNull("The mentions was null testKweet4.", testKweet4.getMentions());
        assertNotNull("The mentions was null testKweet5.", testKweet5.getMentions());
        assertNotNull("The mentions was null testKweet6.", testKweet6.getMentions());
        assertNotNull("The mentions was null testKweet7.", testKweet7.getMentions());
        assertNotNull("The mentions was null testKweet8.", testKweet8.getMentions());
        assertNotNull("The mentions was null testKweet9.", testKweet9.getMentions());
        assertNotNull("The mentions was null testKweet10.", testKweet10.getMentions());
    }

    @Test
    public void getEmptyMentionsTest() {
        assertNull("The mentions was not null.", testKweetEmpty.getMentions());
    }

    @Test
    public void getHeartedByTest() {
        assertNotNull("The hearted by list was null for testKweet1.", testKweet1.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet2.", testKweet2.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet3.", testKweet3.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet4.", testKweet4.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet5.", testKweet5.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet6.", testKweet6.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet7.", testKweet7.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet8.", testKweet8.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet9.", testKweet9.getHeartedBy());
        assertNotNull("The hearted by list was null for testKweet10.", testKweet10.getHeartedBy());
    }

    @Test
    public void getEmptyHeartedByTest() {
        assertNull("The hearted by list was not null.", testKweetEmpty.getHeartedBy());
    }

    @Test
    public void getDateCreatedTest() {
        assertEquals("The Date was not right for testKweet1.", testDate, testKweet1.getDateCreated());
        assertEquals("The Date was not right for testKweet2.", testDate, testKweet2.getDateCreated());
        assertEquals("The Date was not right for testKweet3.", testDate, testKweet3.getDateCreated());
        assertEquals("The Date was not right for testKweet4.", testDate, testKweet4.getDateCreated());
        assertEquals("The Date was not right for testKweet5.", testDate, testKweet5.getDateCreated());
        assertEquals("The Date was not right for testKweet6.", testDate, testKweet6.getDateCreated());
        assertEquals("The Date was not right for testKweet7.", testDate, testKweet7.getDateCreated());
        assertEquals("The Date was not right for testKweet8.", testDate, testKweet8.getDateCreated());
        assertEquals("The Date was not right for testKweet9.", testDate, testKweet9.getDateCreated());
        assertEquals("The Date was not right for testKweet10.", testDate, testKweet10.getDateCreated());
    }

    @Test
    public void getEmptyDateCreatedTest() {
        assertNull("The Date was not right.", testKweetEmpty.getDateCreated());
    }

    @Test
    public void getDateUpdatedTest() {
        assertEquals("The Date was not right for testKweet1.", testDate2, testKweet1.getDateUpdated());
        assertEquals("The Date was not right for testKweet2.", testDate2, testKweet2.getDateUpdated());
        assertEquals("The Date was not right for testKweet3.", testDate2, testKweet3.getDateUpdated());
        assertEquals("The Date was not right for testKweet4.", testDate2, testKweet4.getDateUpdated());
        assertEquals("The Date was not right for testKweet5.", testDate2, testKweet5.getDateUpdated());
        assertEquals("The Date was not right for testKweet6.", testDate2, testKweet6.getDateUpdated());
        assertEquals("The Date was not right for testKweet7.", testDate2, testKweet7.getDateUpdated());
        assertEquals("The Date was not right for testKweet8.", testDate2, testKweet8.getDateUpdated());
        assertEquals("The Date was not right for testKweet9.", testDate2, testKweet9.getDateUpdated());
        assertEquals("The Date was not right for testKweet10.", testDate2, testKweet10.getDateUpdated());
    }

    @Test
    public void getEmptyDateUpdatedTest() {
        assertNull("The Date was not right.", testKweetEmpty.getDateUpdated());
    }

    @Test
    public void getTrendsTest() {
        assertNotNull("The trends list was null for testKweet1.", testKweet1.getTrends());
        assertNotNull("The trends list was null for testKweet2.", testKweet2.getTrends());
        assertNotNull("The trends list was null for testKweet3.", testKweet3.getTrends());
        assertNotNull("The trends list was null for testKweet4.", testKweet4.getTrends());
        assertNotNull("The trends list was null for testKweet5.", testKweet5.getTrends());
        assertNotNull("The trends list was null for testKweet6.", testKweet6.getTrends());
        assertNotNull("The trends list was null for testKweet7.", testKweet7.getTrends());
        assertNotNull("The trends list was null for testKweet8.", testKweet8.getTrends());
        assertNotNull("The trends list was null for testKweet9.", testKweet9.getTrends());
        assertNotNull("The trends list was null for testKweet10.", testKweet10.getTrends());
    }

    @Test
    public void getEmptyTrendsTest() {
        assertNull("The trends list was not null.", testKweetEmpty.getTrends());
    }
}