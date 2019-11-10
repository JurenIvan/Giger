package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.MusicianPreviewDto;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Musician {

    @Id
    private Long id;
    private String bio;
    private boolean publicCalendar;

    @ElementCollection
    @CollectionTable(name = "instruments", joinColumns = @JoinColumn(name = "musician"))
    @Column(name = "plays", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Instrument> instruments;

    @ManyToMany
    @JoinTable(name = "musician_gig_history",
            joinColumns = {@JoinColumn(name = "fk_musician")},
            inverseJoinColumns = {@JoinColumn(name = "fk_gig")})
    private List<Gig> pastGigs;

    @OneToMany
    @JoinColumn(name = "fk_user")
    private List<Post> posts;

    @ElementCollection
    @CollectionTable(name = "musician_occasions", joinColumns = @JoinColumn(name = "musician_id"))
    private List<Occasion> occasions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musician)) return false;
        Musician musician = (Musician) o;
        return Objects.equals(id, musician.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public MusicianPreviewDto toDto() {
        return new MusicianPreviewDto(id, bio, publicCalendar, instruments);
    }

}