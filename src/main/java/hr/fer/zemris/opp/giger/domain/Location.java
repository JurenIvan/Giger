package hr.fer.zemris.opp.giger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private Double x;
    private Double y;
    private String address;
    private String extraDescription;
}
