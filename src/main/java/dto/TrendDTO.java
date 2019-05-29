package dto;

import domain.Kweet;
import domain.Trend;

import java.util.ArrayList;
import java.util.List;

public class TrendDTO {
    private String id;
    private String name;
    private List<KweetDTO> kweets;

    public TrendDTO(){

    }

    public TrendDTO(Trend trend){
        this.id = trend.getId().toString();
        this.name = trend.getName();
        this.kweets = new ArrayList<>();
        for (Kweet t : trend.getKweets()){
            this.kweets.add(new KweetDTO(t));
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<KweetDTO> getKweets() {
        return kweets;
    }

    public void setKweets(List<KweetDTO> Kweets) {
        this.kweets = Kweets;
    }
}
