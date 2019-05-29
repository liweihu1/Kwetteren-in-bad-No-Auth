package dao.memory;

import dao.database.MemoryDatabase;
import dao.interfaces.IKweetDAO;
import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
@Alternative
public class KweetDAOMEMImpl implements IKweetDAO {

    private MemoryDatabase database;

    public KweetDAOMEMImpl(){
        this.database = MemoryDatabase.getInstance();
    }

    @Override
    public Kweet add(Kweet kweet) {
        database.getKweets().add(kweet);
        return kweet;
    }

    @Override
    public void delete(Kweet kweet) {
        database.getKweets().stream().filter(k -> k.getId() == kweet.getId()).findAny().ifPresent(dbKweet -> database.getKweets().remove(dbKweet));
    }

    @Override
    public Kweet update(Kweet kweet) {
        return null;
    }

    @Override
    public Kweet findById(UUID id) {
        return database.getKweets().stream().filter(k -> k.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Kweet> getAllKweets() {
        return database.getKweets();
    }

    @Override
    public List<Kweet> getPaginatedKweets(int page, int count) {
        return database.getKweets();
    }

    @Override
    public List<Kweet> getLatestKweetsForUserId(UUID id, int page, int count) {
        return database.getKweets().stream().filter(k -> k.getAuthor().getId() == id).limit(10).collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetThatContainsSearch(String search, int page, int count) {
        return database.getKweets().stream().filter(k -> k.getMessage().contains(search)).collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweetsByUserId(UUID id, int page, int count) {
        return database.getKweets().stream().filter(k -> k.getAuthor().getId() == id).collect(Collectors.toList());

    }

    @Override
    public List<Kweet> getKweetForUserIdWithFollowing(UUID id, int page, int count) {
        List<Kweet> result = new ArrayList<>();
        result.addAll(database.getKweets().stream().filter(k -> k.getAuthor().getId() == id).collect(Collectors.toList()));
        for (User u : database.getUserById(id).getFollowing()) {
            result.addAll(database.getKweets().stream().filter(k -> k.getAuthor().getId() == u.getId()).collect(Collectors.toList()));
        }
        return result;
    }

    @Override
    public void clearData() {
        database.clearData();
    }

    @Override
    public void setEm(EntityManager em) {
        // Don't set because there is no em
    }
}
