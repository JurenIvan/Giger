package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.InstrumentType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Instrument {

    @Id
    private Long id;
    private String name;
    private InstrumentType type;
}
