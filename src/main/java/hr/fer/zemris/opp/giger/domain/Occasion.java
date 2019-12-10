package hr.fer.zemris.opp.giger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Occasion {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDate localDate;
    private String description;
    private Boolean personalOccasion;

    public Occasion getOccasionWithoutDescription() {
        return new Occasion(id, localDate, null, personalOccasion);
    }
}
