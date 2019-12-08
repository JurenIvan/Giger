package hr.fer.zemris.opp.giger.web.rest.dto;

import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreationDto {

    private String contentOfReviewForBand;
    private String contentOfReviewForOrganizer;
    private Integer gradeBand;
    private Integer gradeOrganizer;

    public Review createReview(Person person) {
        return new Review(null, contentOfReviewForBand, contentOfReviewForOrganizer, gradeBand, gradeOrganizer, LocalDateTime.now(), person);
    }
}
