package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;
import hr.fer.zemris.opp.giger.web.rest.dto.BandCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.BandProfileDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Band {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;
    private String bio;
    @NotNull
    private LocalDate formedDate;
    private String pictureUrl;
    private Location home;

    //todo members
    @ManyToOne(fetch = LAZY)
    private Musician leader;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "musician_bands",
            joinColumns = {@JoinColumn(name = "fk_musician")},
            inverseJoinColumns = {@JoinColumn(name = "fk_band")})
    private List<Musician> members;

    @ManyToMany(fetch = LAZY)
    private List<Musician> backUpMembers;

    @ManyToMany(fetch = LAZY)
    private List<Musician> invited;

    @ManyToMany(fetch = LAZY)
    private List<Musician> invitedBackUpMembers;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "fk_band")
    private List<Post> posts;

    @ElementCollection(targetClass = GigType.class)
    @CollectionTable(name = "gig_type", joinColumns = @JoinColumn(name = "gig"))
    @Column(name = "gig_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<GigType> acceptableGigTypes;

    public static Band createBand(BandCreationDto bandCreationDto, Musician loggedMusician) {
        Band band = new Band();

        band.setName(bandCreationDto.getName());
        band.setAcceptableGigTypes(bandCreationDto.getAcceptableGigTypes());
        band.setBio(bandCreationDto.getBio());
        band.setFormedDate(now());
        band.setHome(bandCreationDto.getHomeLocation());
        band.setPictureUrl(bandCreationDto.getPictureUrl());
        band.setLeader(loggedMusician);
        band.setMembers(List.of(loggedMusician));
        return band;
    }

    public void addMember(Musician musician) {
        members.add(musician);
        invited.remove(musician);
        backUpMembers.remove(musician);
    }

    public void inviteMember(Musician musician) {
        invited.add(musician);
    }


    public void inviteBackupMember(Musician musician) {
        invitedBackUpMembers.add(musician);
    }

    public void addBackUpMember(Musician musician) {
        backUpMembers.add(musician);
        invitedBackUpMembers.remove(musician);
    }

    public void removeMember(Musician musician) {
        members.remove(musician);
    }

    public void removeBackUpMember(Musician loggedMusician) {
        backUpMembers.remove(loggedMusician);
    }

    public void editProfile(BandProfileDto bandProfileDto) {
        if (bandProfileDto.getBio() != null) {
            this.bio = bandProfileDto.getBio();
        }
        if (bandProfileDto.getPictureUrl() != null) {
            this.pictureUrl = bandProfileDto.getPictureUrl();
        }
        if (bandProfileDto.getLocation() != null) {
            this.home = bandProfileDto.getLocation();
        }
        bandProfileDto.getRemovePostIds().forEach(e -> posts.remove(e));
    }
}
