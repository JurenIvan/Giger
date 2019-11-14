package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Occasion implements Comparable<LocalDate> {

    @Id
    private Long id;

    private LocalDate localDate;
    private String description;
    private boolean personalOccasion;

    @Override
    public int compareTo(LocalDate localDate) {
        return this.localDate.compareTo(localDate);
    }
}
