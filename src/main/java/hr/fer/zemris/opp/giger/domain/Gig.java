package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
@Data
public class Gig {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "organizer_id")
    @NotNull
    private Organizer organizer;
    @NotNull
    private LocalDateTime dateTime;
    @Embedded
    private Location location;
    private String description;
    private String expectedDuration;
    private Integer proposedPrice;
    private GigType gigType;
    private boolean finalDealAchieved;
    private boolean privateGig;

    @ManyToOne(fetch = LAZY)
    private Band finalBand;

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(name = "review_gig",
            joinColumns = {@JoinColumn(name = "fk_gig")},
            inverseJoinColumns = {@JoinColumn(name = "fk_review")})
    private List<Review> reviews;
}
