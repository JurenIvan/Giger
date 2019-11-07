package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class Organizer {

    @Id
    private Long id;

    private String managerName; //todo think about it

    @OneToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "organizer_id")
    private List<Gig> history;

    @OneToOne(fetch = LAZY)
    private User user;
}
