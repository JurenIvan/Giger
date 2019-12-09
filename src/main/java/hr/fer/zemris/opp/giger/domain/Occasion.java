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
public class Occasion implements Comparable<LocalDate> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDate localDate;
    private String description;
    private boolean personalOccasion;

    @Override
    public int compareTo(LocalDate localDate) {
        return this.localDate.compareTo(localDate);
    }
}
