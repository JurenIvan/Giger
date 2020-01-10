package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.ReviewService;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewsDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
	private ReviewService reviewService;

	@PostMapping("/create")
	public void createReview(@Valid @RequestBody ReviewCreationDto reviewDto) {
		reviewService.createReview(reviewDto);
	}

	@GetMapping("/band/{bandId}")
	public ReviewsDto getReviewsForBand(@PathVariable @Min(1) Long bandId) {
		return reviewService.getReviewsForBand(bandId);
	}

	@GetMapping("/organizer/{organizerId}")
	public ReviewsDto getReviewsForOrganizer(@PathVariable @Min(1) Long organizerId) {
		return reviewService.getReviewsForOrganizer(organizerId);
	}

	@GetMapping("/gig/{gigId}")
	public ReviewsDto getReviewsForGig(@PathVariable @Min(1) Long gigId) {
		return reviewService.getReviewsForGig(gigId);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
