package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianDto {

	public String bio;
	private List<Long> instrumentIdList = new ArrayList<>();
	@NotNull
	private Boolean publicCalendar;
}
