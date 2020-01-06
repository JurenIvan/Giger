package hr.fer.zemris.opp.giger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

	private Double x;
	private Double y;
	private String address;
	private String extraDescription;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Location)) return false;
		Location location = (Location) o;
		return Objects.equals(x, location.x) &&
				Objects.equals(y, location.y) &&
				Objects.equals(address, location.address) &&
				Objects.equals(extraDescription, location.extraDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, address, extraDescription);
	}
}
