package dto;

import domain.Kweet;
import domain.Trend;
import domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KweetDTO {
    private String id;
    private UserDTO author;
    private String message;
    private Date dateCreated;
    private Date dateUpdated;
    private List<String> mentions;
    private List<UserDTO> heartedBy;
    private List<TrendDTO> trends;

    public KweetDTO(){

    }

    public KweetDTO(Kweet kweet){
        this.id = kweet.getId().toString();
        this.author = new UserDTO(kweet.getAuthor());
        this.message = kweet.getMessage();
        this.dateCreated = kweet.getDateCreated();
        this.dateUpdated = kweet.getDateUpdated();
        this.mentions = new ArrayList<>();
        this.heartedBy = new ArrayList<>();
        this.trends = new ArrayList<>();
        for (User u : kweet.getMentions()){
            this.mentions.add(u.getUsername());
        }

        for(User u: kweet.getHeartedBy()){
            this.heartedBy.add(new UserDTO(u));
        }

        for (Trend t: kweet.getTrends()){
            this.trends.add(new TrendDTO(t));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public List<UserDTO> getHeartedBy() {
        return heartedBy;
    }

    public void setHeartedBy(List<UserDTO> heartedBy) {
        this.heartedBy = heartedBy;
    }

    public List<TrendDTO> getTrends() {
        return trends;
    }

    public void setTrends(List<TrendDTO> trends) {
        this.trends = trends;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
