package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.repository.ReviewRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewCreationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserDetailsServiceImpl userDetailsService;

    public void createReview(ReviewCreationDto reviewCreationDto) {
        reviewRepository.save(reviewCreationDto.createReview(userDetailsService.getLoggedPerson()));
    }
}
