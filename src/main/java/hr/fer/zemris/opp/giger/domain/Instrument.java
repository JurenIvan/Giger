package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.InstrumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrument {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String name;
	private InstrumentType type;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Instrument)) return false;
		Instrument instrument = (Instrument) o;
		return Objects.equals(id, instrument.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
