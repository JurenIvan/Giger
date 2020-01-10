package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Location;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandCreationDto {

    @NotBlank
	private String name;

    @NotBlank
	private String bio;

    @NotBlank
    private String pictureUrl;
	private List<GigType> acceptableGigTypes;
	private Location homeLocation;
}
