package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Instrument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicianPreviewPictureDto {

	private Long id;
	private String bio;
	private boolean publicCalendar;
	private List<Instrument> instruments;
	private String username;
	private String pictureUrl;
}
