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
public class BandDto {

	private long id;
	private String name;
	private String bio;
	private String pictureURl;
	private List<GigType> gigTypes;

	private List<Long> membersIds;
	private List<Long> backupMembersIds;
	private Long leaderId;

	private Location location;
}
