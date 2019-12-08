package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.Review;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.GigRepository;
import hr.fer.zemris.opp.giger.repository.OrganizerRepository;
import hr.fer.zemris.opp.giger.repository.ReviewRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewsDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.*;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private BandRepository bandRepository;
    private OrganizerRepository organizerRepository;
    private GigRepository gigRepository;
    private UserDetailsServiceImpl userDetailsService;

    public ReviewsDto getReviewsForBand(Long bandId) {
        List<ReviewPreviewDto> reviews = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND))
                .getGigs().stream().map(Gig::getReviews).flatMap(List::stream).map(Review::toDto).collect(toList());

        double average = reviews.stream().mapToDouble(ReviewPreviewDto::getGradeBand).average().orElse(0);
        return new ReviewsDto(reviews, average, reviews.size());
    }

    public ReviewsDto getReviewsForOrganizer(Long organizerId) {
        Organizer a = organizerRepository.findById(organizerId).orElseThrow(() -> new GigerException(NO_SUCH_ORGANIZER));
        List<ReviewPreviewDto> reviews = gigRepository.findAllByOrganizer(a)
                .stream().map(Gig::getReviews).flatMap(List::stream).map(Review::toDto).collect(toList());

        double average = reviews.stream().mapToDouble(ReviewPreviewDto::getGradeOrganizer).average().orElse(0);
        return new ReviewsDto(reviews, average, reviews.size());
    }

    public ReviewsDto getReviewsForGig(Long gigId) {
        List<ReviewPreviewDto> reviews = gigRepository.findById(gigId).orElseThrow(() -> new GigerException(NO_SUCH_GIG))
                .getReviews().stream().map(Review::toDto).collect(toList());

        double average = reviews.stream().mapToDouble(e -> e.getGradeBand() + e.getGradeOrganizer()).average().orElse(0);
        return new ReviewsDto(reviews, average / 2, reviews.size());

    public void createReview(ReviewCreationDto reviewCreationDto) {
        reviewRepository.save(reviewCreationDto.createReview(userDetailsService.getLoggedPerson()));
    }
}
