package dao.database;

import domain.Kweet;
import domain.Trend;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemoryDatabase {
    private static MemoryDatabase instance;
    protected List<User> users;
    protected List<Kweet> kweets;
    protected List<Trend> trends;

    public MemoryDatabase(){
        clearData();
    }

    public void clearData(){
        this.users = new ArrayList<>();
        this.kweets = new ArrayList<>();
        this.trends = new ArrayList<>();
    }

    public List<User> getUsers() {
        return this.users;
    }

    public List<Kweet> getKweets() {
        return this.kweets;
    }

    public List<Trend> getTrends() {
        return this.trends;
    }

    public User getUserById(UUID id){
        return this.users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public User getUserByUsername(String username){
        return this.users.stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }

    public Kweet getKweetById(UUID id) {
        return this.kweets.stream().filter(kweets -> kweets.getId() == id).findAny().orElse(null);
    }

    public static MemoryDatabase getInstance(){
        if (instance == null){
            instance = new MemoryDatabase();
        }
        return instance;
    }
}
