package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
@Data
public class Band {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String name;
    private String bio;
    @NotNull
    private LocalDate formedDate;

    //todo members
    @ManyToOne(fetch = LAZY)
    private Musician leader;

    @ManyToMany(fetch = LAZY)
    private List<Musician> members;

    @ManyToMany(fetch = LAZY)
    private List<Musician> backUpMembers;

    @ManyToMany(fetch = LAZY)
    private List<Musician> invited;

    @OneToMany
    private List<Gig> historyGigs;

    private String pictureUrl;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "fk_band")
    private List<Post> posts;

    @ElementCollection(targetClass = GigType.class)
    @CollectionTable(name = "gig_type", joinColumns = @JoinColumn(name = "gig"))
    @Column(name = "gig_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<GigType> acceptableGigTypes;
}
