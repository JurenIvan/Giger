package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    private LocalDateTime dateTime;
    @Embedded
    private Location location;
    private String description;
    private String expectedDuration;
    private Integer proposedPrice;
    private GigType gigType;
    private boolean finalDealAchieved;
    private boolean privateGig;

    @ManyToOne
    private Band finalBand;

    @ManyToMany
    @JoinTable(name = "review_gig",
            joinColumns = {@JoinColumn(name = "fk_gig")},
            inverseJoinColumns = {@JoinColumn(name = "fk_review")})
    private List<Review> reviews;

    public Gig() {
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(String expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public Integer getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(Integer proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public GigType getGigType() {
        return gigType;
    }

    public void setGigType(GigType gigType) {
        this.gigType = gigType;
    }

    public boolean isFinalDealAchieved() {
        return finalDealAchieved;
    }

    public void setFinalDealAchieved(boolean finalDealAchieved) {
        this.finalDealAchieved = finalDealAchieved;
    }

    public Band getFinalBand() {
        return finalBand;
    }

    public void setFinalBand(Band finalBand) {
        this.finalBand = finalBand;
    }

    public boolean isPrivateGig() {
        return privateGig;
    }

    public void setPrivateGig(boolean privateGig) {
        this.privateGig = privateGig;
    }
}
