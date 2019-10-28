package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String bio;
    @NotNull
    private LocalDate formedDate;

    @ManyToOne(fetch = LAZY)
    private Musician leader;

    @ManyToMany(fetch = LAZY)
    private List<Musician> members;

    @OneToMany
    private List<Gig> gigs;

    private String pictureUrl;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "fk_band")
    private List<Post> posts;

    @ElementCollection(targetClass = GigType.class)
    @CollectionTable(name = "gig_type", joinColumns = @JoinColumn(name = "gig"))
    @Column(name = "gig_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<GigType> acceptableGigTypes;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "review_band",
            joinColumns = {@JoinColumn(name = "fk_band")},
            inverseJoinColumns = {@JoinColumn(name = "fk_review")})
    private List<Review> reviews;

    public Band() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getFormedDate() {
        return formedDate;
    }

    public void setFormedDate(LocalDate formedDate) {
        this.formedDate = formedDate;
    }

    public Musician getLeader() {
        return leader;
    }

    public void setLeader(Musician leader) {
        this.leader = leader;
    }

    public List<Musician> getMembers() {
        return members;
    }

    public void setMembers(List<Musician> members) {
        this.members = members;
    }

    public List<Gig> getGigs() {
        return gigs;
    }

    public void setGigs(List<Gig> gigs) {
        this.gigs = gigs;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<GigType> getAcceptableGigTypes() {
        return acceptableGigTypes;
    }

    public void setAcceptableGigTypes(List<GigType> acceptableGigTypes) {
        this.acceptableGigTypes = acceptableGigTypes;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
