package hr.fer.zemris.opp.giger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Occasion implements Comparable<LocalDate> {

    @Id
    private Long id;

    private LocalDate localDate;
    private String description;
    private Boolean personalOccasion;

    @Override
    public int compareTo(LocalDate localDate) {
        return this.localDate.compareTo(localDate);
    }

    public Occasion getOccasionWithoutDescripiton() {
        return new Occasion(id, localDate, null, personalOccasion);
    }
}
