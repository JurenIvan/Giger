package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.Review;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @PostMapping("/create")
    public void createReview(Review review) {
    }

    @GetMapping("/band/{bandId}")
    public ReviewsDto getReviewsForBand(Band band) {
        return null;
    }

    @GetMapping("/organizer/{organizerId}")
    public void getReviewsForOrganizer(Organizer organizer) {
    }

    @GetMapping("/gig/{gigId}")
    public void getReviewsForGig(Gig gig) {
    }
}
