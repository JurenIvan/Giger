package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class Musician {

    @Id
    private Long id;

    private String bio;
    private boolean publicCalendar;

    @ElementCollection(targetClass = Instrument.class)
    @CollectionTable(name = "instruments", joinColumns = @JoinColumn(name = "musician"))
    @Column(name = "plays", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Instrument> instruments;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "musician_bands",
            joinColumns = {@JoinColumn(name = "fk_musician")},
            inverseJoinColumns = {@JoinColumn(name = "fk_band")})
    private List<Band> bands;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "musician_gig_history",
            joinColumns = {@JoinColumn(name = "fk_musician")},
            inverseJoinColumns = {@JoinColumn(name = "fk_gig")})
    private List<Gig> pastGigs;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "fk_user")
    private List<Post> posts;

    @ElementCollection
    @CollectionTable(name = "musician_occasions", joinColumns = @JoinColumn(name = "musician_id"))
    private List<Occasion> occasions;

    @OneToOne(fetch = LAZY)
    private User user;
}
