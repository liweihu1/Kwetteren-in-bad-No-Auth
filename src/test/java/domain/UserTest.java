package domain;

import domain.Kweet;
import domain.Role;
import domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserTest {
    private User testUserEmpty;
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
    private String name;
    private String lastName;
    private String bio;
    private String website;
    private String location;

    @Before
    public void setup() {
        testId = UUID.randomUUID();
        name = "Test";
        lastName = "Test";
        bio = "This is a test bio";
        website = "www.test.com";
        location = "Test location 2";

        ArrayList<User> testUsers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (i > 0){
                final User follower = testUsers.get(i - 1);
                testUsers.add(new User(testId, name + i, name + i,name + i, lastName, bio, website, location, new HashSet<User>(){{add(follower);}}, new HashSet<User>(), new ArrayList<Role>(), new ArrayList<Kweet>(), new ArrayList<Kweet>(), new ArrayList<Kweet>()));
            } else {
                testUsers.add(new User(testId, name + i, name + i,name + i, lastName, bio, website, location, new HashSet<User>(), new HashSet<User>(), new ArrayList<>(), new ArrayList<Kweet>(), new ArrayList<Kweet>(), new ArrayList<Kweet>()));
            }
        }

        testUserEmpty = new User();
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
        assertEquals("Id was not right for testUser1", testId, testUser1.getId());
        assertEquals("Id was not right for testUser2", testId, testUser2.getId());
        assertEquals("Id was not right for testUser3", testId, testUser3.getId());
        assertEquals("Id was not right for testUser4", testId, testUser4.getId());
        assertEquals("Id was not right for testUser5", testId, testUser5.getId());
        assertEquals("Id was not right for testUser6", testId, testUser6.getId());
        assertEquals("Id was not right for testUser7", testId, testUser7.getId());
        assertEquals("Id was not right for testUser8", testId, testUser8.getId());
        assertEquals("Id was not right for testUser9", testId, testUser9.getId());
        assertEquals("Id was not right for testUser10", testId, testUser10.getId());
    }

    @Test
    public void getEmptyIdTest(){
        assertNull("The id was not null", testUserEmpty.getId());
    }

    @Test
    public void getUsernameTest() {
        assertEquals("The username was not right for testUser1.", name + 0, testUser1.getUsername());
        assertEquals("The username was not right for testUser2.", name + 1, testUser2.getUsername());
        assertEquals("The username was not right for testUser3.", name + 2, testUser3.getUsername());
        assertEquals("The username was not right for testUser4.", name + 3, testUser4.getUsername());
        assertEquals("The username was not right for testUser5.", name + 4, testUser5.getUsername());
        assertEquals("The username was not right for testUser6.", name + 5, testUser6.getUsername());
        assertEquals("The username was not right for testUser7.", name + 6, testUser7.getUsername());
        assertEquals("The username was not right for testUser8.", name + 7, testUser8.getUsername());
        assertEquals("The username was not right for testUser9.", name + 8, testUser9.getUsername());
        assertEquals("The username was not right for testUser10.", name + 9, testUser10.getUsername());
    }

    @Test
    public void getEmptyUsernameTest(){
        assertNull("The username was not null", testUserEmpty.getUsername());
    }


    @Test
    public void getFirstNameTest() {
        assertEquals("The firstname was not right for testUser1.", name + 0, testUser1.getFirstName());
        assertEquals("The firstname was not right for testUser2.", name + 1, testUser2.getFirstName());
        assertEquals("The firstname was not right for testUser3.", name + 2, testUser3.getFirstName());
        assertEquals("The firstname was not right for testUser4.", name + 3, testUser4.getFirstName());
        assertEquals("The firstname was not right for testUser5.", name + 4, testUser5.getFirstName());
        assertEquals("The firstname was not right for testUser6.", name + 5, testUser6.getFirstName());
        assertEquals("The firstname was not right for testUser7.", name + 6, testUser7.getFirstName());
        assertEquals("The firstname was not right for testUser8.", name + 7, testUser8.getFirstName());
        assertEquals("The firstname was not right for testUser9.", name + 8, testUser9.getFirstName());
        assertEquals("The firstname was not right for testUser10.", name + 9, testUser10.getFirstName());
    }

    @Test
    public void getEmptyFirstNameTest(){
        assertNull("The firstName was not null", testUserEmpty.getFirstName());
    }

    @Test
    public void changeUsernameTest(){
        String testName = "Johhnyyy";
        testUserEmpty.setUsername(testName);
        assertEquals("The test username was not set", testName, testUserEmpty.getUsername());
        testUserEmpty.setUsername(null);
    }

    @Test
    public void getLastNameTest() {
        assertEquals("The lastname was not right for testUser1", lastName, testUser1.getLastName());
        assertEquals("The lastname was not right for testUser2", lastName, testUser2.getLastName());
        assertEquals("The lastname was not right for testUser3", lastName, testUser3.getLastName());
        assertEquals("The lastname was not right for testUser4", lastName, testUser4.getLastName());
        assertEquals("The lastname was not right for testUser5", lastName, testUser5.getLastName());
        assertEquals("The lastname was not right for testUser6", lastName, testUser6.getLastName());
        assertEquals("The lastname was not right for testUser7", lastName, testUser7.getLastName());
        assertEquals("The lastname was not right for testUser8", lastName, testUser8.getLastName());
        assertEquals("The lastname was not right for testUser9", lastName, testUser9.getLastName());
        assertEquals("The lastname was not right for testUser10", lastName, testUser10.getLastName());
    }

    @Test
    public void getEmptyLastNameTest(){
        assertNull("The lastname was not null", testUserEmpty.getLastName());
    }

    @Test
    public void getBiographyTest() {
        assertEquals("The biography was not right for testUser1", bio, testUser1.getBiography());
        assertEquals("The biography was not right for testUser2", bio, testUser2.getBiography());
        assertEquals("The biography was not right for testUser3", bio, testUser3.getBiography());
        assertEquals("The biography was not right for testUser4", bio, testUser4.getBiography());
        assertEquals("The biography was not right for testUser5", bio, testUser5.getBiography());
        assertEquals("The biography was not right for testUser6", bio, testUser6.getBiography());
        assertEquals("The biography was not right for testUser7", bio, testUser7.getBiography());
        assertEquals("The biography was not right for testUser8", bio, testUser8.getBiography());
        assertEquals("The biography was not right for testUser9", bio, testUser9.getBiography());
        assertEquals("The biography was not right for testUser10", bio, testUser10.getBiography());
    }

    @Test
    public void getEmptyBiographyTest(){
        assertNull("The biography was not null", testUserEmpty.getBiography());
    }

    @Test
    public void getWebsiteTest() {
        assertEquals("The website was not right for testUser1.", website, testUser1.getWebsite());
        assertEquals("The website was not right for testUser2.", website, testUser2.getWebsite());
        assertEquals("The website was not right for testUser3.", website, testUser3.getWebsite());
        assertEquals("The website was not right for testUser4.", website, testUser4.getWebsite());
        assertEquals("The website was not right for testUser5.", website, testUser5.getWebsite());
        assertEquals("The website was not right for testUser6.", website, testUser6.getWebsite());
        assertEquals("The website was not right for testUser7.", website, testUser7.getWebsite());
        assertEquals("The website was not right for testUser8.", website, testUser8.getWebsite());
        assertEquals("The website was not right for testUser9.", website, testUser9.getWebsite());
        assertEquals("The website was not right for testUser10.", website, testUser10.getWebsite());
    }

    @Test
    public void getEmptyWebsiteTest(){
        assertNull("The website was not null", testUserEmpty.getWebsite());
    }

    @Test
    public void getLocationTest() {
        assertEquals("The location was not right for testUser1.", location, testUser1.getLocation());
        assertEquals("The location was not right for testUser2.", location, testUser2.getLocation());
        assertEquals("The location was not right for testUser3.", location, testUser3.getLocation());
        assertEquals("The location was not right for testUser4.", location, testUser4.getLocation());
        assertEquals("The location was not right for testUser5.", location, testUser5.getLocation());
        assertEquals("The location was not right for testUser6.", location, testUser6.getLocation());
        assertEquals("The location was not right for testUser7.", location, testUser7.getLocation());
        assertEquals("The location was not right for testUser8.", location, testUser8.getLocation());
        assertEquals("The location was not right for testUser9.", location, testUser9.getLocation());
        assertEquals("The location was not right for testUser10.", location, testUser10.getLocation());
    }

    @Test
    public void getEmptyLocationTest(){
        assertNull("The location was not null", testUserEmpty.getLocation());
    }

    @Test
    public void getFollowersTest() {
        assertNotNull("The followers were null for testUser1.", testUser1.getFollowers());
        assertNotNull("The followers were null for testUser2.", testUser2.getFollowers());
        assertNotNull("The followers were null for testUser3.", testUser3.getFollowers());
        assertNotNull("The followers were null for testUser4.", testUser4.getFollowers());
        assertNotNull("The followers were null for testUser5.", testUser5.getFollowers());
        assertNotNull("The followers were null for testUser6.", testUser6.getFollowers());
        assertNotNull("The followers were null for testUser7.", testUser7.getFollowers());
        assertNotNull("The followers were null for testUser8.", testUser8.getFollowers());
        assertNotNull("The followers were null for testUser9.", testUser9.getFollowers());
        assertNotNull("The followers were null for testUser10.", testUser10.getFollowers());
    }

    @Test
    public void getEmptyFollowersTest(){
        assertNull("The followers list was not null", testUserEmpty.getFollowers());
    }

    @Test
    public void getFollowingTest() {
        assertNotNull("The following list was null for testUser1.", testUser1.getFollowing());
        assertNotNull("The following list was null for testUser2.", testUser2.getFollowing());
        assertNotNull("The following list was null for testUser3.", testUser3.getFollowing());
        assertNotNull("The following list was null for testUser4.", testUser4.getFollowing());
        assertNotNull("The following list was null for testUser5.", testUser5.getFollowing());
        assertNotNull("The following list was null for testUser6.", testUser6.getFollowing());
        assertNotNull("The following list was null for testUser7.", testUser7.getFollowing());
        assertNotNull("The following list was null for testUser8.", testUser8.getFollowing());
        assertNotNull("The following list was null for testUser9.", testUser9.getFollowing());
        assertNotNull("The following list was null for testUser10.", testUser10.getFollowing());
    }

    @Test
    public void getEmptyFollowingTest(){
        assertNull("The following list was not null", testUserEmpty.getFollowing());
    }

    @Test
    public void getRolesTest() {
        assertNotNull("The roles list was null for testUser1.", testUser1.getRoles());
        assertNotNull("The roles list was null for testUser2.", testUser2.getRoles());
        assertNotNull("The roles list was null for testUser3.", testUser3.getRoles());
        assertNotNull("The roles list was null for testUser4.", testUser4.getRoles());
        assertNotNull("The roles list was null for testUser5.", testUser5.getRoles());
        assertNotNull("The roles list was null for testUser6.", testUser6.getRoles());
        assertNotNull("The roles list was null for testUser7.", testUser7.getRoles());
        assertNotNull("The roles list was null for testUser8.", testUser8.getRoles());
        assertNotNull("The roles list was null for testUser9.", testUser9.getRoles());
        assertNotNull("The roles list was null for testUser10.", testUser10.getRoles());
    }

    @Test
    public void getEmptyRolesTest(){
        assertNull("The roles list was not null", testUserEmpty.getRoles());
    }

    @Test
    public void getKweetsTest() {
        assertNotNull("The Kweet list was null for testUser1.", testUser1.getKweets());
        assertNotNull("The Kweet list was null for testUser2.", testUser2.getKweets());
        assertNotNull("The Kweet list was null for testUser3.", testUser3.getKweets());
        assertNotNull("The Kweet list was null for testUser4.", testUser4.getKweets());
        assertNotNull("The Kweet list was null for testUser5.", testUser5.getKweets());
        assertNotNull("The Kweet list was null for testUser6.", testUser6.getKweets());
        assertNotNull("The Kweet list was null for testUser7.", testUser7.getKweets());
        assertNotNull("The Kweet list was null for testUser8.", testUser8.getKweets());
        assertNotNull("The Kweet list was null for testUser9.", testUser9.getKweets());
        assertNotNull("The Kweet list was null for testUser10.", testUser10.getKweets());
    }

    @Test
    public void getEmptyKweetsTest(){
        assertNull("The Kweets list was not null", testUserEmpty.getKweets());
    }

    @Test
    public void getMentionsTest(){
        assertNotNull("The mentions list was null for testUser1.", testUser1.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser2.", testUser2.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser3.", testUser3.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser4.", testUser4.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser5.", testUser5.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser6.", testUser6.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser7.", testUser7.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser8.", testUser8.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser9.", testUser9.getMentionedKweets());
        assertNotNull("The mentions list was null for testUser10.", testUser10.getMentionedKweets());
    }

    @Test
    public void getEmptyMentionsTest(){
        assertNull("The mentions list was not null", testUserEmpty.getMentionedKweets());
    }

    @Test
    public void getHeartedKweetsTest(){
        assertNotNull("The hearted by list was null for testUser1.", testUser1.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser2.", testUser2.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser3.", testUser3.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser4.", testUser4.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser5.", testUser5.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser6.", testUser6.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser7.", testUser7.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser8.", testUser8.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser9.", testUser9.getHeartedKweets());
        assertNotNull("The hearted by list was null for testUser10.", testUser10.getHeartedKweets());
    }

    @Test
    public void getEmptyHeartedTest(){
        assertNull("The hearted by list was not null", testUserEmpty.getHeartedKweets());
    }
}