package hr.fer.zemris.opp.giger.web.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KickDto {

	private long musicianId;
	private long bandId;
}
