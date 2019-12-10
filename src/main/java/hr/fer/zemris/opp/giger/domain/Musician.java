package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.MusicianPreviewDto;
import lombok.AllArgsConstructor;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Musician {

    @Id
    private Long id;
    private String bio;
    private boolean publicCalendar;

    @ManyToMany
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

    public List<Occasion> getOccasionsWithoutDescriptions() {
        return occasions.stream().map(Occasion::getOccasionWithoutDescription).collect(toList());
    }

    public List<Occasion> getPublicOccasios() {
        return occasions.stream().filter(occasion -> !occasion.getPersonalOccasion()).collect(toList());
    }

    public Musician update(MusicianProfileDto musicianProfileDto) {
        if (musicianProfileDto.getInstrumentList() != null) {
            this.instruments = musicianProfileDto.getInstrumentList();
        }
        return this;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}