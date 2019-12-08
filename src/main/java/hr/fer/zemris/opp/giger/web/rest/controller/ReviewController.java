package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.ReviewService;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewsDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/create")
    public void createReview(ReviewCreationDto reviewDto) {
        reviewService.createReview(reviewDto);
    }

    @GetMapping("/band/{bandId}")
    public ReviewsDto getReviewsForBand(@PathVariable Long bandId) {
        return null;
    }

    @GetMapping("/organizer/{organizerId}")
    public void getReviewsForOrganizer(@PathVariable Long organizerId) {
    }

    @GetMapping("/gig/{gigId}")
    public void getReviewsForGig(@PathVariable Long gigId) {
    }
}
