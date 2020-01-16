package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreationDto {

	@NotBlank
	private String contentOfReviewForBand;
	@NotBlank
	private String contentOfReviewForOrganizer;
	@Min(1) @Max(10)
	private Integer gradeBand;
	@Min(1) @Max(10)
	private Integer gradeOrganizer;

	public Review createReview(Person person) {
		return new Review(null, contentOfReviewForBand, contentOfReviewForOrganizer, gradeBand, gradeOrganizer, LocalDateTime.now(), person);
	}
}
