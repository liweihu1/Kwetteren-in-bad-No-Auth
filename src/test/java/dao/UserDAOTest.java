package dao;

import dao.interfaces.IUserDAO;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

public abstract class UserDAOTest {

    private static IUserDAO userDAO;

    private User testUser1;
    private User testUser2;
    private User testUser3;

    protected static void setUserDAO(IUserDAO userDAOImpl){
        userDAO = userDAOImpl;
    }

    @Before
    public void setup(){
        this.testUser1 = new User(UUID.randomUUID(), "test1", "test1", "test1", "test1", "test1", "test1", "test1", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser2 = new User(UUID.randomUUID(), "test2", "test2", "test2", "test2", "test2", "test2", "test2", new HashSet<User>() {{add(testUser1);}}, new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser3 = new User(UUID.randomUUID(), "test3", "test3",  "test3", "test3", "test3", "test3", "test3", new HashSet<>(), new HashSet<User>() {{add(testUser2);}}, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        this.testUser1 = userDAO.add(testUser1);
    }

    @After
    public void teardown(){
        if (userDAO.findById(testUser1.getId()) != null){
            userDAO.delete(testUser1);
        }

        if (userDAO.findById(testUser2.getId()) != null){
            userDAO.delete(testUser2);
        }

        if (userDAO.findById(testUser3.getId()) != null){
            userDAO.delete(testUser3);
        }
    }

    @Test
    public void add() {
        assertNotNull("The user was not added.", userDAO.add(testUser2));
    }

    @Test
    public void delete() {
        userDAO.delete(testUser1);
        assertNull("The user was not removed", userDAO.findById(testUser1.getId()));
    }

    @Test
    public void checkUsernameAvailable() {
        assertFalse("The username was not found!", userDAO.checkUsernameAvailable(testUser1.getUsername()));
    }

    @Test
    public void getAllUsers() {
        userDAO.add(testUser2);
        userDAO.add(testUser3);
        assertEquals("Not all users were retrieved.", 3, userDAO.getAllUsers().size());
    }

    @Test
    public void update() {
        String testUsername = "TestUsername";
        testUser1.setUsername(testUsername);
        userDAO.update(testUser1);
        assertEquals("The user was not udpated.", testUsername, userDAO.findById(testUser1.getId()).getUsername());
    }

    @Test
    public void findById() {
        assertEquals("The right user was not found", testUser1.getUsername(), userDAO.findById(testUser1.getId()).getUsername());
    }

    @Test
    public void findByUsername() {
        assertEquals("The right user was not found", testUser1.getId(), userDAO.findByUsername(testUser1.getUsername()).getId());
    }

    @Test
    public void getFollowing() {
        testUser3 = userDAO.add(testUser3);
        assertEquals("The user had no following.",1, userDAO.findById(testUser3.getId()).getFollowing().size());
    }

    @Test
    public void getFollowers() {
        testUser2 = userDAO.add(testUser2);
        assertEquals("The user had no followers.",1, userDAO.findById(testUser2.getId()).getFollowers().size());
    }

    @Test
    public void getUserMentions() {
        //TODO Implement mentions
    }

    @Test
    public void clearData() {
        userDAO.clearData();
    }
}