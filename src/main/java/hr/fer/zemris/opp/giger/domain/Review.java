package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.ReviewPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String contentOfReviewForBand;
	private String contentOfReviewForOrganizer;
	private Integer gradeBand;
	private Integer gradeOrganizer;

	private LocalDateTime created;

	@ManyToOne(fetch = LAZY)
	private Person author;

	public ReviewPreviewDto toDto() {
		return new ReviewPreviewDto(id, contentOfReviewForBand, contentOfReviewForOrganizer, gradeBand, gradeOrganizer, created, author.toDto());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Review)) return false;
		Review review = (Review) o;
		return Objects.equals(id, review.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
