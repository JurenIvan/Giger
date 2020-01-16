package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Instrument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowMusicianProfileDto {

	private String name;
	private List<Instrument> instruments;
	private String pictureUrl;
	private String contactNumber;

	public MusicianProfileDto toMusicianProfileDto() {
		return new MusicianProfileDto(name, instruments.stream().map(Instrument::getId).collect(toList()), pictureUrl, contactNumber);
	}
}
