package hr.fer.zemris.opp.giger.web.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KickDto {
	@Min(1)
	private Long musicianId;
	@Min(1)
	private Long bandId;
}
