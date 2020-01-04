package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccasionDto {

	private Long id;
	private LocalDateTime localDate;
	private String description;
	private Boolean personalOccasion;
}
