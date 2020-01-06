package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandInvitation {

	public Long bandId;
	public Long gigId;
}
