package domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Trend {
    @Id
    @Column( columnDefinition = "BINARY(16)", length = 16 )
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Trend_Kweet",
            joinColumns = @JoinColumn(name = "trend_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kweet_id", referencedColumnName = "id")
    )
    @OrderBy(value = "dateUpdated DESC")
    private List<Kweet> kweets;

    public Trend() {

    }

    public Trend(UUID id, String name, List<Kweet> kweets) {
        this.id = id;
        this.name = name;
        this.kweets = kweets;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }
}
