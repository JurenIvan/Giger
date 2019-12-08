package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Review;
import hr.fer.zemris.opp.giger.service.ReviewService;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/create")
    public void createReview(Review review) {
    }

    @GetMapping("/band/{bandId}")
    public ReviewsDto getReviewsForBand(@PathVariable Long bandId) {
        return reviewService.getReviewsForBand(bandId);
    }

    @GetMapping("/organizer/{organizerId}")
    public ReviewsDto getReviewsForOrganizer(@PathVariable Long organizerId) {
        return reviewService.getReviewsForOrganizer(organizerId);
    }

    @GetMapping("/gig/{gigId}")
    public ReviewsDto getReviewsForGig(@PathVariable Long gigId) {
        return reviewService.getReviewsForGig(gigId);
    }
}
