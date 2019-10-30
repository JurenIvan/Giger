package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Calendar {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "calendar_occasions", joinColumns = @JoinColumn(name = "calendar_id"))
    private List<Occasion> gigDates;

}
