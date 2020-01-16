package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Location;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GigCreationDto {

	@NotNull
	private LocalDateTime dateTime;
	@NotNull
	private Location location;
	private String description;
	private String expectedDuration;
	@Min(0)
	private Integer proposedPrice;
	private GigType gigType;
	@NotNull
	private Boolean privateGig;
	@NotNull
	private String gigName;
}
