package hr.fer.zemris.opp.giger.web.rest.dto;

		import lombok.AllArgsConstructor;
		import lombok.Data;
		import lombok.NoArgsConstructor;

		import javax.validation.constraints.Min;
		import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandInvitation {

	@Min(1)
	public Long bandId;
	@Min(1)
	public Long gigId;
}
