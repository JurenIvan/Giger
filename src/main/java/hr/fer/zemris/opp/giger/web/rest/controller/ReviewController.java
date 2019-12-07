package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Review;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewsDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @PostMapping("/create")
    public void createReview(Review review) {
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
