package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Location;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandCreationDto {

	private String name;
	private String bio;
	private String pictureUrl;
	private List<GigType> acceptableGigTypes;
	private Location homeLocation;
}
