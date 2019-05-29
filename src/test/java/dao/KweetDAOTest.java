package dao;

import dao.interfaces.IKweetDAO;
import dao.interfaces.IUserDAO;
import domain.Kweet;
import domain.Trend;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public abstract class KweetDAOTest {

    private static IUserDAO userDAO;
    private static IKweetDAO kweetDAO;

    private UUID testKweetId;
    private String testMessage;
    private Date testDate;
    private Date testDate2;

    private Kweet testKweet1;
    private Kweet testKweet2;
    private Kweet testKweet3;

    private User testUser1;
    private User testUser2;
    private User testUser3;

    protected static void setKweetDAO(IKweetDAO kweetDAO) {
        KweetDAOTest.kweetDAO = kweetDAO;
    }

    protected static void setUserDAO(IUserDAO userDAO) {
        KweetDAOTest.userDAO = userDAO;
    }


    @Before
    public void setup(){
        testMessage = "This is a great test message.";
        testDate = new Date();
        testDate2 = new Date();

        this.testUser1 = new User(UUID.randomUUID(), "test1", "test1", "test1", "test1", "test1", "test1", "test1", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser1 = userDAO.add(testUser1);

        this.testUser2 = new User(UUID.randomUUID(), "test2", "test2", "test2", "test2", "test2", "test2", "test2", new HashSet<User>() {{add(testUser1);}}, new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser2 = userDAO.add(testUser2);

        this.testUser3 = new User(UUID.randomUUID(), "test3", "test3", "test3", "test3", "test3", "test3", "test3", new HashSet<User>() , new HashSet<User>(){{add(testUser2);}}, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser3 = userDAO.add(testUser3);

        this.testUser2.getFollowers().add(testUser3);
        userDAO.update(this.testUser2);

        this.testKweet1 = new Kweet(UUID.randomUUID(), testUser1, testMessage, testDate, testDate2, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Trend>(), 0);
        this.testKweet1 = kweetDAO.add(testKweet1);

        this.testKweet2 = new Kweet(UUID.randomUUID(), testUser2, testMessage + " test 2", testDate, testDate2, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Trend>(), 0);
        this.testKweet2 = kweetDAO.add(testKweet2);

        this.testKweet3 = new Kweet(UUID.randomUUID(), testUser3, testMessage + " test 3", testDate, testDate2, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Trend>(), 0);
        this.testKweet3 = kweetDAO.add(testKweet3);
    }

    @After
    public void teardown(){
        if (kweetDAO.findById(testKweet1.getId()) != null) {
            kweetDAO.delete(testKweet1);
        }

        if (kweetDAO.findById(testKweet2.getId()) != null) {
            kweetDAO.delete(testKweet2);
        }

        if (kweetDAO.findById(testKweet2.getId()) != null) {
            kweetDAO.delete(testKweet2);
        }

        if (userDAO.findById(testUser1.getId()) != null){
            userDAO.delete(testUser1);
        }

        if (userDAO.findById(testUser2.getId()) != null){
            userDAO.delete(testUser2);
        }

        if (userDAO.findById(testUser3.getId()) != null){
            userDAO.delete(testUser3);
        }

        userDAO.clearData();
        kweetDAO.clearData();
    }

    @Test
    public void add() {
        assertNotNull("Test kweet was not added.", kweetDAO.findById(testKweet2.getId()));
    }

    @Test
    public void delete() {
        kweetDAO.delete(testKweet3);
        assertNull("The test kweet was not removed.", kweetDAO.findById(testKweet3.getId()));
    }

    @Test
    public void update() {
        Date updatedDate = new Date();
        testKweet1.setDateUpdated(updatedDate);
        kweetDAO.update(testKweet1);
        assertEquals("The updated date was not updated.", updatedDate, kweetDAO.findById(testKweet1.getId()).getDateUpdated());
    }

    @Test
    public void findById() {
        assertEquals(testKweet1, kweetDAO.findById(testKweet1.getId()));
    }

    @Test
    public void getAllKweets() {
        assertEquals("Not all kweets were retrieved.", 3, kweetDAO.getAllKweets().size());
    }

    @Test
    public void getKweetLikesForUserId() {
        // TODO
    }

    @Test
    public void getAllKweetsByUserId() {
        assertEquals("Not all kweets were retrieved.", 1, kweetDAO.getLatestKweetsForUserId(testUser1.getId(),0 , 10).size());
    }

    @Test
    public void getKweetForUserIdWithFollowing() {
        assertEquals(2, kweetDAO.getKweetForUserIdWithFollowing(testUser3.getId(),0 , 10).size());
    }

    @Test
    public void getLatestKweetsForUserId() {
        assertEquals(1, kweetDAO.getLatestKweetsForUserId(testUser1.getId(),0 , 10).size());
    }

    @Test
    public void getKweetThatContainsSearch() {
        assertEquals("The first set of kweets were not found.",3, kweetDAO.getKweetThatContainsSearch("test",0 , 10).size());
    }

    @Test
    public void clearData() {
        kweetDAO.clearData();
    }
}