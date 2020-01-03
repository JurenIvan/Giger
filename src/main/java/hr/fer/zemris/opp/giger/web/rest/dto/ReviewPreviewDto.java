package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPreviewDto {

	private Long id;
	private String contentOfReviewForBand;
	private String contentOfReviewForOrganizer;
	private Integer gradeBand;
	private Integer gradeOrganizer;
	private LocalDateTime created;
	private PersonPreviewDto author;
}
