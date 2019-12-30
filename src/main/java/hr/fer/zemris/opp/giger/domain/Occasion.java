package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.OccasionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Occasion {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private LocalDateTime localDateTime;
	private String description;
	private Boolean personalOccasion;

	public Occasion getOccasionWithoutDescription() {
		return new Occasion(id, localDateTime, null, personalOccasion);
	}

	public OccasionDto toDto() {
		return new OccasionDto(id, localDateTime, description, personalOccasion);
	}

	public static Occasion createOccasion(Gig gig,Boolean personalOccasion) {
		return new Occasion(null, gig.getDateTime(), gig.getDescription(), personalOccasion);
	}
}
