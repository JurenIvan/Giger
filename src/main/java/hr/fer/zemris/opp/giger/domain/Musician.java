package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.Instrument;

import javax.persistence.*;
import java.util.List;

@Entity
public class Musician {

    @Id
    private Long id;
    private String bio;

    @ElementCollection(targetClass = Instrument.class)
    @CollectionTable(name = "instruments", joinColumns = @JoinColumn(name = "musician"))
    @Column(name = "plays", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Instrument> instruments;

    @ManyToMany
    @JoinTable(name = "musician_bands",
            joinColumns = {@JoinColumn(name = "fk_musician")},
            inverseJoinColumns = {@JoinColumn(name = "fk_band")})
    private List<Band> bands;

    @ManyToMany
    @JoinTable(name = "musician_gig",
            joinColumns = {@JoinColumn(name = "fk_musician")},
            inverseJoinColumns = {@JoinColumn(name = "fk_gig")})
    private List<Gig> pastGigs;

    @ManyToMany
    @JoinTable(name = "review_musician",
            joinColumns = {@JoinColumn(name = "fk_musician")},
            inverseJoinColumns = {@JoinColumn(name = "fk_review")})
    private List<Review> reviews;

    @OneToMany
    @JoinColumn(name = "fk_user")
    private List<Post> posts;

    public Musician() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public List<Gig> getPastGigs() {
        return pastGigs;
    }

    public void setPastGigs(List<Gig> pastGigs) {
        this.pastGigs = pastGigs;
    }

}
