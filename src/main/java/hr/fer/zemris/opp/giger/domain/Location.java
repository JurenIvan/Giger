package hr.fer.zemris.opp.giger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
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
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof LocalDateTime)) return false;
		Location location = (Location) o;
		return x.equals(location.x)
				&& y.equals(location.y)
				&& address.equals(location.address)
				&& extraDescription.equals(location.extraDescription);
	}

	@Override
	public int hashCode(){ return Objects.hash(x, y, address, extraDescription); }
}
