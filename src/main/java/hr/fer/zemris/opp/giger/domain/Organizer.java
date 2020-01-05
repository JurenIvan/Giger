package hr.fer.zemris.opp.giger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {

	@Id
	private Long id;
	private String managerName;

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof Organizer)) return false;
		Organizer organizer = (Organizer) o;
		return id.equals(organizer.getId());
	}

	@Override
	public int hashCode(){ return Objects.hash(id); }
}
