package service;

import dao.interfaces.IKweetDAO;
import dao.interfaces.IUserDAO;
import domain.Kweet;
import domain.Trend;
import domain.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class KweetService {
    @EJB(beanName = "KweetDAOJPAImpl")
    private IKweetDAO IKweetDAO;

    @EJB(beanName = "UserDAOJPAImpl")
    private IUserDAO userDAO;

    public KweetService() {
        System.out.println(this.IKweetDAO);
        System.out.println(this.userDAO);
    }


    public List<Kweet> getKweetsForUserId(UUID userId, int page, int count){
        return IKweetDAO.getLatestKweetsForUserId(userId, page, count);
    }

    public Kweet getKweetById(UUID id){
        return IKweetDAO.findById(id);
    }

    @Transactional
    public Kweet createKweet(String message, String authorId){
        return IKweetDAO.add(new Kweet(UUID.randomUUID(), userDAO.findById(UUID.fromString(authorId)), message, new Date(), new Date(), getMentionsForKweet(message), new ArrayList<>(), new ArrayList<>(), 0));
    }

    public List<Kweet> getAllKweets(){
        return IKweetDAO.getAllKweets();
    }

    public List<Kweet> getPaginatedKweets(int page, int count){
        return IKweetDAO.getPaginatedKweets(page, count);
    }

    public List<Kweet> getKweetsBySearchString(String search, int page, int count){
        return IKweetDAO.getKweetThatContainsSearch(search, page, count);
    }

    public List<Kweet> getKweetsByUserIdWithFollowing(UUID id, int page, int count){
        return IKweetDAO.getKweetForUserIdWithFollowing(id, page, count);
    }

    public Kweet removeKweetById(UUID id) {
        Kweet kweetToDelete = IKweetDAO.findById(id);
        if (kweetToDelete != null) {
            IKweetDAO.delete(kweetToDelete);
            return kweetToDelete;
        }
        return null;
    }

    private List<User> getMentionsForKweet(String message) {
        Pattern pattern = Pattern.compile("(@(\\w*[0-9a-zA-Z]))");
        Matcher matcher = pattern.matcher(message);
        List<String> usernames = new ArrayList<>();
        while(matcher.find()){
            usernames.add(matcher.group(2));
        }
        List<User> result = new ArrayList<>();
        for(String username : usernames){
            result.add(userDAO.findByUsername(username));
        }
        return result;
    }

    private List<Trend> getTrendsForKweet(String message) {
        return null;
    }
}
