package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.Review;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewsDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    public void createReview(Review review){

    }

    public ReviewsDto getReviewsForBand(Band band){
        return null;
    }

    public void getReviewsForOrganizer(Organizer organizer){

    }

    public void getReviewsForGig(Gig gig){

    }

}
