package hr.fer.zemris.opp.giger.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Organizer {

    @Id
    private Long id;

    @OneToMany
    private List<Gig> history;

    @OneToMany
    private List<Review> reviews;

    public Organizer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Gig> getHistory() {
        return history;
    }

    public void setHistory(List<Gig> history) {
        this.history = history;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
