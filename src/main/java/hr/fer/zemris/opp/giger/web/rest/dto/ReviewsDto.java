package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDto {

	private List<ReviewPreviewDto> reviews;
	private Double averageGrade;
	private Integer totalReviewsCount;
}
